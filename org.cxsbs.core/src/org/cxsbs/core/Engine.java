package org.cxsbs.core;

import java.nio.ByteBuffer;
import java.util.List;

import enetwrapper.ew_Event;
import enetwrapper.EnetWrapperLibrary;

public class Engine extends Thread {
	private boolean encounteredError = false;
	private volatile boolean stayAlive = true;
	private List<Integer> connectedClients;
	
	public static void send(int clientid, int channel, ByteBuffer buffer, boolean reliable) {
	    long datalen = buffer.position();
	    buffer.rewind();
	    EnetWrapperLibrary.send(clientid, 1, true, buffer, datalen);
	}
	
	/**
	 * @param args
	 */
	public void run() {
		
		if(EnetWrapperLibrary.ew_initialize("127.0.0.1", 28785, 10, 3, 0, 0) == 0) {
			System.out.println("Could not initialize networking.");
			return;
		} else {
			System.out.println("CXSBS started listening on port 28785...");
		}
		
		while(!encounteredError && stayAlive) service();
		
		if (connectedClients.size() > 0 && !encounteredError) {
			
			ByteBuffer buffer = ByteBuffer.allocate(100);
			CubeByteBuffer.putint(buffer, 35); //N_SERVMSG = 35
			CubeByteBuffer.putstring(buffer, "Server shutting down.");
			
			for (int clientid : connectedClients) {
				EnetWrapperLibrary.ew_disconnect(clientid, 0);
			}
			
			service();
			
			for (int clientid : connectedClients) {
				EnetWrapperLibrary.ew_disconnect(clientid, 0);
			}
		}
		
		EnetWrapperLibrary.ew_deinitialize();
	}
	
	private void service() {
		
		ew_Event event = new ew_Event();
		if(EnetWrapperLibrary.ew_service(5, event) == 0) {
			encounteredError = true;
			return;
		}
		
		if(event.type == EnetWrapperLibrary.ew_CONNECT) {
			System.out.println("clientid: " + event.clientid + " connected.");
			
			connectedClients.add(new Integer(event.clientid));
			
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
			
			if (event.channel == 1) {
				while(buffer.position() < buffer.limit()) {
					IMessage message = MessageType.parseMessage(buffer, MessageContext.CTS);
					
					if(message.getMessageType() == MessageType.CONNECT) {
						System.out.println("clientid: " + event.clientid + " CONNECT");
						StringFieldValue sfv = (StringFieldValue)message.getField("name");
						System.out.println("\tname: " + sfv.value);
					} else if(message.getMessageType() == MessageType.PING) {
						
					} else {
						System.out.println("clientid: " + event.clientid + " message: " + 
								message.getMessageType().toString());
					}
				}
			}
		}
		else if (event.type == EnetWrapperLibrary.ew_DISCONNECT) {
			System.out.println("clientid: " + event.clientid + " disconnected.");
			
			connectedClients.remove(new Integer(event.clientid));
		}
	}
	
	public void shutdown() {
		stayAlive = false;
	}

}
