package org.cxsbs.core;

import com.sun.jna.NativeLong;

import enetwrapper.ew_Event;
import enetwrapper.EnetWrapperLibrary;

import org.cxsbs.cube.common.CubeBuffer;

public class Engine {
	
	public static void send(int clientid, int channel, CubeBuffer cb, boolean reliable) {
	    long datalen = cb.data.position();
	    cb.data.rewind();
	    EnetWrapperLibrary.send(clientid, 1, true, cb.data, datalen);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("jna.library.path", "/home/morgan/workspace/jsbserver2/libs");
		
		if(EnetWrapperLibrary.ew_initialize("127.0.0.1", 28785, 10, 3, 0, 0) == 0) {
			System.out.println("Could not initialize networking.");
			return;
		}
		
		ew_Event event = new ew_Event();
		while(EnetWrapperLibrary.ew_service(5, event) != 0) {
			if(event.type == EnetWrapperLibrary.ew_CONNECT) {
				System.out.println("clientid: " + event.clientid + " connected.");
				org.cxsbs.cube.common.CubeBuffer cb = new org.cxsbs.cube.common.CubeBuffer();
				
				cb.putint(1);
			    cb.putint(event.clientid);
			    cb.putint(258);
			    cb.putint(event.clientid);
			    cb.putint(0);
			    cb.putstring("Test Java Server");
			    
			    send(event.clientid, 1, cb, true);
			}
			else if (event.type == EnetWrapperLibrary.ew_DATA) {
				System.out.println("clientid: " + event.clientid + " data: " + event.getDataBuffer().toString());
			}
			else if (event.type == EnetWrapperLibrary.ew_DISCONNECT) {
				System.out.println("clientid: " + event.clientid + " disconnected.");
			}
		}
		
		EnetWrapperLibrary.ew_deinitialize();
	}

}
