package nativeenet;

import com.ochafik.lang.jnaerator.runtime.Structure;
import com.sun.jna.Pointer;

public class ENetEvent extends Structure<ENetEvent, ENetEvent.ByValue, ENetEvent.ByReference > {
	public int                  type;      /**< type of the event */
	public ENetPeer             peer;      /**< peer that generated a connect, disconnect or receive event */
	public byte                 channelID; /**< channel on the peer that generated the event, if appropriate */
	public int                  data;      /**< data associated with the event, if appropriate */
	public ENetPacket           packet;    /**< packet associated with the event, if appropriate */
	
	public ENetEvent() {
		super();
	}
	
	protected ByReference newByReference() { return new ByReference(); }
	protected ByValue newByValue() { return new ByValue(); }
	protected ENetEvent newInstance() { return new ENetEvent(); }
	public static ENetEvent[] newArray(int arrayLength) {
		return Structure.newArray(ENetEvent.class, arrayLength);
	}
	public static class ByReference extends ENetEvent implements Structure.ByReference {};
	public static class ByValue extends ENetEvent implements Structure.ByValue {};
}