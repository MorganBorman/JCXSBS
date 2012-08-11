package org.cxsbs.core;

public class VectorFieldValue extends FieldValue implements IFieldValue {
	private Vector value;
	
	public VectorFieldValue(IField field, Vector value) {
		super(field);
		this.value = value;
	}
	
	public Vector getValue() {
		return value;
	}
}
