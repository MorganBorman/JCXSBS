package org.cxsbs.core;

public enum WeaponType {
	FIST(Sound.PUNCH1, 250, 50, 0, 0, 14, "fist"), 
	SG(Sound.SG, 1400, 10, 0, 20, 1024, "shotgun"), 
	CG(Sound.CG, 100, 30, 0, 7, 1024, "chaingun"), 
	RL(Sound.RLFIRE, 800, 120, 80, 10, 1024, "rocketlauncher"), 
	RIFLE(Sound.RIFLE, 1500, 100, 0, 30, 2048, "rifle"), 
	GL(Sound.FLAUNCH, 500, 75, 80, 10, 1024, "grenadelauncher"), 
	PISTOL(Sound.PISTOL, 500, 25, 0, 7, 1024, "pistol"), 
	FIREBALL(Sound.FLAUNCH, 200, 20, 50, 1, 1024, "fireball"), 
	ICEBALL(Sound.ICEBALL, 200, 40, 30, 1, 1024, "iceball"), 
	SLIMEBALL(Sound.SLIMEBALL, 200, 30, 160, 1, 1024, "slimeball"), 
	BITE(Sound.PIGR1, 250, 50, 0, 1, 12, "bite"), 
	BARREL(Sound.NONE, 0, 120, 0, 0, 0, "barrel"), 
	NUMGUNS(Sound.NONE, 0, 0, 0, 0, 0, "");
	
	public final Sound sound;
	public final int attackdelay;
	public final int damage;
	public final int projspeed;
	public final int kickamount;
	public final int range;
	public final String name;
	
	private WeaponType(Sound sound, int attackdelay, int damage, int projspeed, int kickamount, int range, String name) {
		this.sound = sound;
		this.attackdelay = attackdelay;
		this.damage = damage;
		this.projspeed = projspeed;
		this.kickamount = kickamount;
		this.range = range;
		this.name = name;
	}
	
}
