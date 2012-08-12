package org.cxsbs.core;

import java.nio.ByteBuffer;

public class StringField extends Field implements IField {

	public StringField(String name) {
		super(name, FieldType.STRING);
	}

	@Override
	public void parse(ByteBuffer buffer, IFieldMap fieldMap) {
		IFieldValue fieldValue = new StringFieldValue(this, CubeByteBuffer.getstring(buffer, false));
		fieldMap.put(this, fieldValue);
	}

}
