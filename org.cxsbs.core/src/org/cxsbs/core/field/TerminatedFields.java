package org.cxsbs.core.field;

import java.util.ArrayList;
import java.util.List;
import java.nio.ByteBuffer;

import org.cxsbs.core.CubeByteBuffer;

public class TerminatedFields extends Field implements IField {
	
	private final Field[] fields;
	
	public TerminatedFields(String name, Field[] fields) {
		super(name, FieldType.TERMINATEDFIELDS);
		this.fields = fields;
	}

	@Override
	public void parse(ByteBuffer buffer, IFieldMap fieldMap) {
		
		List<FieldMap> subFieldMaps = new ArrayList<FieldMap>();
		
		while(CubeByteBuffer.getint(buffer, true) >= 0) {
			FieldMap subFieldMap = new FieldMap();
			subFieldMaps.add(subFieldMap);
			for(Field field :this.fields) {
				field.parse(buffer, subFieldMap);
			}
		}
		
		IFieldValue fieldValue = new TerminatedFieldValue(this, subFieldMaps);
		fieldMap.put(this, fieldValue);
	}

}
