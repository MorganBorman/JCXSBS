package org.cxsbs.core;

import java.nio.ByteBuffer;

public class HitField extends Field implements IField {

	public HitField(String name) {
		super(name, FieldType.HIT);
	}

	@Override
	public void parse(ByteBuffer buffer, Message message) {
		IFieldValue fieldValue = new HitFieldValue(this, 
				CubeByteBuffer.getint(buffer, false), //target
				CubeByteBuffer.getint(buffer, false), //lifesequence
				CubeByteBuffer.getint(buffer, false), //distance
				CubeByteBuffer.getint(buffer, false), //rays
				new Vector( //direction
						CubeByteBuffer.getint(buffer, false), 
						CubeByteBuffer.getint(buffer, false), 
						CubeByteBuffer.getint(buffer, false)));
		message.put(this, fieldValue);
	}

}
