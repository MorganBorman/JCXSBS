package org.cxsbs.core.tests;

import java.nio.ByteBuffer;

import org.cxsbs.core.IMessage;
import org.cxsbs.core.MessageContext;
import org.cxsbs.core.MessageType;

public class MessageTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		byte[] myBuffer = new byte[10];
		
		ByteBuffer buffer = ByteBuffer.wrap(myBuffer);
		
		IMessage message = MessageType.parseMessage(buffer, MessageContext.STC);
	}

}
