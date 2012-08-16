package org.cxsbs.core.field;

import java.util.List;

public class TerminatedFieldValue extends FieldValue implements IFieldValue {
	public final FieldMap[] fieldMaps;
	
	public TerminatedFieldValue(IField field, List<FieldMap> fieldMaps) {
		super(field);
		this.fieldMaps = (FieldMap[]) fieldMaps.toArray();
	}
}
