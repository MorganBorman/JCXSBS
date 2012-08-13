package org.cxsbs.core;

import java.nio.ByteBuffer;

public class ClientPositionField extends Field implements IField {

	public ClientPositionField(String name) {
		super(name, FieldType.CLIENTPOSITION);
	}

	@Override
	public void parse(ByteBuffer buffer, IFieldMap fieldMap) {
		
		// Rewind to include the MessageType
		// Note this only works because we know that the POS is represented by a single byte using
		//  the cube integer compression scheme
		buffer.position(buffer.position() - 1);
		
		ByteBuffer fullPositionBuffer = buffer.slice();
		
		// Eat up the MessageType integer again
		buffer.position(buffer.position() + 1);
		
		FieldValue fieldValue = new ClientPositionFieldValue(this, CubeByteBuffer.getint(buffer, false), fullPositionBuffer);
		fieldMap.put(this, fieldValue);
		
		buffer.position(buffer.limit());
	}

}
