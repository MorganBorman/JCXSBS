package org.cxsbs.core.field;

public class UIntFieldValue extends FieldValue implements IFieldValue {
	public final long value;
	
	public UIntFieldValue(IField field, long value) {
		super(field);
		this.value = value;
	}
}
