package org.cxsbs.core;

import java.nio.ByteBuffer;

public class IteratedFields extends Field implements IField {
	
	private final Field[] fields;
	
	public IteratedFields(String name, Field[] fields) {
		super(name, FieldType.ITERATEDFIELDS);
		this.fields = fields;
	}

	@Override
	public void parse(ByteBuffer buffer, IFieldMap fieldMap) {
		
		int count = CubeByteBuffer.getint(buffer, false);
		
		FieldMap[] subFieldMaps = new FieldMap[count];
		
		for(int i = 0; i < count; i++) {
			subFieldMaps[i] = new FieldMap();
			for(Field field :this.fields) {
				field.parse(buffer, subFieldMaps[i]);
			}
		}
		
		IFieldValue fieldValue = new IteratedFieldValue(this, subFieldMaps);
		fieldMap.put(this, fieldValue);
	}

}
