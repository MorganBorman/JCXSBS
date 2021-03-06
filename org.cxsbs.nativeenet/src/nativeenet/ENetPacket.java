package nativeenet;

import java.nio.ByteBuffer;

import com.sun.jna.Structure;
import com.sun.jna.Pointer;

public class ENetPacket extends Structure {
	public long                     referenceCount;  /**< internal use only */
	public int                      flags;           /**< bitwise-or of ENetPacketFlag constants */
	public Pointer                  data;            /**< allocated data for packet */
	public long                     dataLength;      /**< length of data */
	public Pointer                  freeCallback;    /**< function to be called when the packet is no longer in use */
	
	public ENetPacket() {
		super();
	}
	
	protected ByReference newByReference() { return new ByReference(); }
	protected ByValue newByValue() { return new ByValue(); }
	protected ENetPacket newInstance() { return new ENetPacket(); }
	public static class ByReference extends ENetPacket implements Structure.ByReference {};
	public static class ByValue extends ENetPacket implements Structure.ByValue {};
	
	public ByteBuffer GetByteBuffer() {
		return this.data.getByteBuffer(0, this.dataLength);
	}
}