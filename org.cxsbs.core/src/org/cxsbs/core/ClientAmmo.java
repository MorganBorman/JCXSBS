package org.cxsbs.core;

public class ClientAmmo {
	private int[] ammo = new int[WeaponType.NUMGUNS.ordinal()];

	public int[] getAsArray() {
		return ammo;
	}

	public void setAsArray(int[] ammo) {
		for (int i = 0; i < this.ammo.length && i < ammo.length; i++)
		{
			this.ammo[i] = ammo[i];
		}
	}
	
	public void setAsArray(int[] ammo, int offset) {
		for (int i = 0; (i+offset) < this.ammo.length && i < ammo.length; i++)
		{
			this.ammo[(i+offset)] = ammo[i];
		}
	}
	
	public int getEntry(int gun) {
		return ammo[gun];
	}
	
	public void setEntry(int gun, int value) {
		this.ammo[gun] = value;
	}
	
	public void incrementEntry(int gun, int amount) {
		this.ammo[gun] += amount;
	}
}
