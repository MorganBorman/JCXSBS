package jcxsbs;

import java.nio.ByteBuffer;

public class Field implements IField {

	private final String name;
	private final FieldType type;
	
	public Field(String name, FieldType type) {
		this.name = name;
		this.type = type;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public FieldType getType() {
		return this.type;
	}
	
	@Override
	public void parse(ByteBuffer buffer, Message message) {
		
	}
	
//	
//	public short getShort();
//	
//	public int getInt();
//	
//	public long getLong();
//	
//	public float getFloat(); // 4 byte
//	
//	public double getDouble(); // 8 byte
//	
//	public String getString();

}
