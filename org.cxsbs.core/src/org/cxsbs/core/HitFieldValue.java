package org.cxsbs.core;

public class HitFieldValue extends FieldValue implements IFieldValue {
	public final int target;
	public final int lifesequence;
	public final int distance;
	public final int rays;
	public final Vector direction;
	
	public HitFieldValue(IField field, int target, int lifesequence, int distance, int rays, Vector direction) {
		super(field);
		this.target = target;
		this.lifesequence = lifesequence;
		this.distance = distance;
		this.rays = rays;
		this.direction = direction;
	}
}
