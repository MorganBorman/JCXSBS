package org.cxsbs.core.field;

public class FieldValue implements IFieldValue {
	IField field;
	
	public FieldValue(IField field) {
		this.field = field;
	}
	
	@Override
	public IField getField() {
		return field;
	}

}
