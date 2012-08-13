package org.cxsbs.core;

import java.nio.ByteBuffer;

import com.sun.jna.NativeLong;

import enetwrapper.ew_Event;
import enetwrapper.EnetWrapperLibrary;

public class Engine {
	
	public static void send(int clientid, int channel, ByteBuffer buffer, boolean reliable) {
	    long datalen = buffer.position();
	    buffer.rewind();
	    EnetWrapperLibrary.send(clientid, 1, true, buffer, datalen);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		if(EnetWrapperLibrary.ew_initialize("127.0.0.1", 28785, 10, 3, 0, 0) == 0) {
			System.out.println("Could not initialize networking.");
			return;
		}
		
		ew_Event event = new ew_Event();
		while(EnetWrapperLibrary.ew_service(5, event) != 0) {
			if(event.type == EnetWrapperLibrary.ew_CONNECT) {
				System.out.println("clientid: " + event.clientid + " connected.");
				ByteBuffer buffer = ByteBuffer.allocate(100);
				
				CubeByteBuffer.putint(buffer, 1); //N_SERVINFO = 1
				CubeByteBuffer.putint(buffer, event.clientid);
				CubeByteBuffer.putint(buffer, 258); //PROTOCOL_VERSION = 258
				CubeByteBuffer.putint(buffer, event.clientid);
				CubeByteBuffer.putint(buffer, 0);
				CubeByteBuffer.putstring(buffer, "Java CXSBS Test Server");
			    
			    send(event.clientid, 1, buffer, true);
			}
			else if (event.type == EnetWrapperLibrary.ew_DATA) {
				ByteBuffer buffer = event.getDataBuffer();
				
				while(buffer.position() < buffer.limit()) {
					IMessage message = MessageType.parseMessage(buffer, MessageContext.STC);
					System.out.println("clientid: " + event.clientid + " message: " + message.getMessageType().toString());
				}
				
				//System.out.println("clientid: " + event.clientid + " data: " + event.getDataBuffer().toString());
			}
			else if (event.type == EnetWrapperLibrary.ew_DISCONNECT) {
				System.out.println("clientid: " + event.clientid + " disconnected.");
			}
		}
		
		EnetWrapperLibrary.ew_deinitialize();
	}

}
