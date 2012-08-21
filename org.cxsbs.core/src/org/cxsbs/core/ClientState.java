package org.cxsbs.core;

public class ClientState {
	private int state;
	private int lifesequence;
	private int frags;
	private int deaths;
	private int flags;
	private int maxhealth;
	private int health;
	private int armour;
	private int armourType;
	private int gunselect;
	private final ClientAmmo ammo;
	
	public ClientState() {
		this.ammo = new ClientAmmo();
	}
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getLifesequence() {
		return lifesequence;
	}
	public void advanceLifesequence() {
		this.lifesequence = (lifesequence+1) & 0x7F;
	}
	public int getFrags() {
		return frags;
	}
	public void setFrags(int frags) {
		this.frags = frags;
	}
	public int getDeaths() {
		return deaths;
	}
	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}
	public int getFlags() {
		return flags;
	}
	public void setFlags(int flags) {
		this.flags = flags;
	}
	public int getMaxhealth() {
		return maxhealth;
	}
	public void setMaxhealth(int maxhealth) {
		this.maxhealth = maxhealth;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getArmour() {
		return armour;
	}
	public void setArmour(int armour) {
		this.armour = armour;
	}
	public int getArmourType() {
		return armourType;
	}
	public void setArmourType(int armourType) {
		this.armourType = armourType;
	}
	public int getGunselect() {
		return gunselect;
	}
	public void setGunselect(int gunselect) {
		this.gunselect = gunselect;
	}
	public ClientAmmo getAmmo() {
		return ammo;
	}
}
