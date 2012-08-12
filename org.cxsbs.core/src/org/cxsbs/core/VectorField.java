package org.cxsbs.core;

import java.nio.ByteBuffer;

public class VectorField extends Field implements IField {

	public VectorField(String name) {
		super(name, FieldType.VECTOR);
	}

	@Override
	public void parse(ByteBuffer buffer, IFieldMap fieldMap) {
		Vector vector = new Vector(CubeByteBuffer.getint(buffer, false), CubeByteBuffer.getint(buffer, false), CubeByteBuffer.getint(buffer, false));
		IFieldValue fieldValue = new VectorFieldValue(this, vector);
		fieldMap.put(this, fieldValue);
	}

}
