package org.cxsbs.core.field;

public class ClientStateFieldValue extends FieldValue implements IFieldValue {
	public final int lifesequence;
	public final int health;
	public final int maxhealth;
	public final int armour;
	public final int armourtype;
	public final int gunselect;
	
	public ClientStateFieldValue(IField field, int lifesequence, int health, int maxhealth, int armour, int armourtype, int gunselect) {
		super(field);
		this.lifesequence = lifesequence;
		this.health = health;
		this.maxhealth = maxhealth;
		this.armour = armour;
		this.armourtype = armourtype;
		this.gunselect = gunselect;
	}
}
