package org.cxsbs.core;

public class IteratedFieldValue extends FieldValue implements IFieldValue {
	public final FieldMap[] fieldMap;
	
	public IteratedFieldValue(IField field, FieldMap[] fieldMaps) {
		super(field);
		this.fieldMap = fieldMaps;
	}
}
