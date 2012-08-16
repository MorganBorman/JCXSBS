package org.cxsbs.core.field;

import org.cxsbs.core.Vector;

public class VectorFieldValue extends FieldValue implements IFieldValue {
	public final Vector value;
	
	public VectorFieldValue(IField field, Vector value) {
		super(field);
		this.value = value;
	}
}
