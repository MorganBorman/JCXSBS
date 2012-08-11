package org.cxsbs.core;

public class Vector {
	private int x;
	private int y;
	private int z;
	
	public Vector(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public static Vector from_yaw_pitch(int yaw, int pitch) {
		int x = (int) (-Math.sin(yaw)*Math.cos(pitch));
		int y = (int) (Math.cos(yaw)*Math.cos(pitch));
		int z = (int) (Math.sin(pitch));
		return new Vector(x, y, z);
	}
	
	public int getX() { return x;}
	public int getY() { return y;}
	public int getZ() { return z;}
	
	public int magnitude() {
		return (int) Math.sqrt(x*x + y*y + z*z);
	}
	
	public boolean isZero() {
		return (x == 0 && y == 0 && z == 0);
	}
	
	public void scale(int factor) {
		this.x /= factor;
		this.y /= factor;
		this.z /= factor;
	}
	
	public void magnify(int factor) {
		this.x *= factor;
		this.y *= factor;
		this.z *= factor;
	}
	
	public Vector div(int factor) {
		return new Vector(this.x / factor, this.y / factor, this.z / factor);
	}
	
	public Vector mul(int factor) {
		return new Vector(this.x * factor, this.y * factor, this.z * factor);
	}
	
	public Vector sub(int value) {
		return new Vector(this.x - value, this.y - value, this.z - value);
	}
	
	public Vector add(int value) {
		return new Vector(this.x + value, this.y + value, this.z + value);
	}
	
	public Vector sub(Vector value) {
		return new Vector(this.x - value.x, this.y - value.y, this.z - value.z);
	}
	
	public Vector add(Vector value) {
		return new Vector(this.x + value.x, this.y + value.y, this.z + value.z);
	}
}
