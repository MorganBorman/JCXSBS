package jcxsbs;


public enum MessageType {
	SERVER_INFO(1, new Field[] { },  new ServerInfoMessageParser()),
	CONNECT(2, new Field[] { }, null);
	
	private final int id;
	private final Field[] fields;
	private final IMessageParser parser;
		
	private MessageType(int id, Field[] fields, IMessageParser parser) {
		this.id = id;
		this.fields = fields;
		this.parser = parser;
	}
	
	public int getId() {
		return this.id;
	}
	
	public IMessage parseMessage(byte[] buffer) {
		return parser.parse(buffer);
	}
	
	public static final MessageType getMessageType(byte[] buffer) {
		int id = extractMessageType(buffer);
		switch(id) {
		case 1:
			return null;
		case 2:
			return null;
		default:
			throw new IllegalArgumentException("Unknown message type");
		}		
	}
	
	public static final IMessage s_parseMessage(byte[] buffer) {
		return getMessageType(buffer).parseMessage(buffer);
	}
	
	public static final int extractMessageType(byte[] buffer) {
		return 1;
	}
	
	private interface IMessageParser {
		public IMessage parse(byte[] buffer);
	}
	
	private static class ServerInfoMessageParser implements IMessageParser {
				
		@Override
		public IMessage parse(byte[] buffer) {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
}
