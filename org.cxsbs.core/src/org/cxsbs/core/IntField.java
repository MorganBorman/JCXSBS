package org.cxsbs.core;

import java.nio.ByteBuffer;

public class IntField extends Field implements IField {

	public IntField(String name) {
		super(name, FieldType.INT);
	}

	@Override
	public void parse(ByteBuffer buffer, Message message) {
		IFieldValue fieldValue = new IntFieldValue(this, CubeByteBuffer.getint(buffer, false));
		message.put(this, fieldValue);
	}

}
