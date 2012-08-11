package org.cxsbs.core;

public class StringFieldValue extends FieldValue implements IFieldValue {
	private String value;
	
	public StringFieldValue(IField field, String value) {
		super(field);
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
