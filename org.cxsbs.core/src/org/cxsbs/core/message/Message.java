package org.cxsbs.core.message;

import org.cxsbs.core.field.FieldMap;

public class Message extends FieldMap implements IMessage {

	private final MessageType messageType;

	public Message(MessageType messageType) {
		this.messageType = messageType;
	}
	
	public MessageType getMessageType() {
		return messageType;
	}
	
}