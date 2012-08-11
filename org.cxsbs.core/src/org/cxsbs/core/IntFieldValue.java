package org.cxsbs.core;

public class IntFieldValue extends FieldValue implements IFieldValue {
	private int value;
	
	public IntFieldValue(IField field, int value) {
		super(field);
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
