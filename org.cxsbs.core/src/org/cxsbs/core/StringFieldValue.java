package org.cxsbs.core;

public class StringFieldValue extends FieldValue implements IFieldValue {
	public final String value;
	
	public StringFieldValue(IField field, String value) {
		super(field);
		this.value = value;
	}
}
