package org.cxsbs.core;

public class MessageTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		byte[] myBuffer = new byte[10];
		
		IMessage message = MessageType.parseMessage(myBuffer);
	}

}
