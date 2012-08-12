package org.cxsbs.core;

public class IntFieldValue extends FieldValue implements IFieldValue {
	public final int value;
	
	public IntFieldValue(IField field, int value) {
		super(field);
		this.value = value;
	}
}
