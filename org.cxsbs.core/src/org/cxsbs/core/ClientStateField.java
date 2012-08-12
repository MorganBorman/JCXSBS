package org.cxsbs.core;

import java.nio.ByteBuffer;

public class ClientStateField extends Field implements IField {

	public ClientStateField(String name) {
		super(name, FieldType.CLIENTSTATE);
	}

	@Override
	public void parse(ByteBuffer buffer, IFieldMap fieldMap) {
		IFieldValue fieldValue = new ClientStateFieldValue(this, 
				CubeByteBuffer.getint(buffer, false), //lifesequence
				CubeByteBuffer.getint(buffer, false), //health
				CubeByteBuffer.getint(buffer, false), //maxhealth
				CubeByteBuffer.getint(buffer, false), //armour
				CubeByteBuffer.getint(buffer, false), //armourtype
				CubeByteBuffer.getint(buffer, false)  //gunselect
				);
		fieldMap.put(this, fieldValue);
	}

}
