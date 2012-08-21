package nativeenet;

import com.sun.jna.Structure;

public class ENetAddress extends Structure {
	public int host;
	public short port;
	
	public ENetAddress() {
		super();
	}
	
	protected ByReference newByReference() { return new ByReference(); }
	protected ByValue newByValue() { return new ByValue(); }
	protected ENetAddress newInstance() { return new ENetAddress(); }
	public static class ByReference extends ENetAddress implements Structure.ByReference {};
	public static class ByValue extends ENetAddress implements Structure.ByValue {};
}
