package org.cxsbs.core.field;

public class AmmoListFieldValue extends FieldValue implements IFieldValue {
	public final int[] ammo;
	
	public AmmoListFieldValue(IField field, int[] ammo) {
		super(field);
		this.ammo = ammo;
	}
}
