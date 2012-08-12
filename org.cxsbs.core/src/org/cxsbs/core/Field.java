package org.cxsbs.core;

import java.nio.ByteBuffer;

public abstract class Field implements IField {

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
	public final FieldType getType() {
		return this.type;
	}
	
	@Override
	public abstract void parse(ByteBuffer buffer, IFieldMap fieldMap);
}
