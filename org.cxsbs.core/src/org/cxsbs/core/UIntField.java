package org.cxsbs.core;

import java.nio.ByteBuffer;

public class UIntField extends Field implements IField {

	public UIntField(String name) {
		super(name, FieldType.UINT);
	}

	@Override
	public void parse(ByteBuffer buffer, IFieldMap fieldMap) {
		IFieldValue fieldValue = new UIntFieldValue(this, CubeByteBuffer.getuint(buffer, false));
		fieldMap.put(this, fieldValue);
	}

}
