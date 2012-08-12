package org.cxsbs.core;

import java.nio.ByteBuffer;

public class AmmoListField extends Field implements IField {

	public AmmoListField(String name) {
		super(name, FieldType.AMMOLIST);
	}

	@Override
	public void parse(ByteBuffer buffer, IFieldMap fieldMap) {
		int[] ammo  = new int[WeaponType.NUMGUNS.ordinal()];
		for(int i = WeaponType.SG.ordinal(); i < WeaponType.NUMGUNS.ordinal(); i++) {
			ammo[i] = CubeByteBuffer.getint(buffer, false);
		}
		
		IFieldValue fieldValue = new AmmoListFieldValue(this, ammo);
		fieldMap.put(this, fieldValue);
	}

}
