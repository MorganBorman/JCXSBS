package org.cxsbs.core;

public class Message extends FieldMap implements IMessage {

	private final MessageType messageType;

	public Message(MessageType messageType) {
		this.messageType = messageType;
	}
	
	public MessageType getMessageType() {
		return messageType;
	}
	
}