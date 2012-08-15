package nativeenet;

import com.ochafik.lang.jnaerator.runtime.Structure;

public class ENetAddress extends Structure<ENetAddress, ENetAddress.ByValue, ENetAddress.ByReference > {
	public int host;
	public short port;
	
	public ENetAddress() {
		super();
	}
	
	protected ByReference newByReference() { return new ByReference(); }
	protected ByValue newByValue() { return new ByValue(); }
	protected ENetAddress newInstance() { return new ENetAddress(); }
	public static ENetAddress[] newArray(int arrayLength) {
		return Structure.newArray(ENetAddress.class, arrayLength);
	}
	public static class ByReference extends ENetAddress implements Structure.ByReference {};
	public static class ByValue extends ENetAddress implements Structure.ByValue {};
}
