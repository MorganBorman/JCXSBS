package nativeenet;

import com.sun.jna.Structure;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;

public class ENetHost extends Structure {
	public Pointer              socket;
	public ENetAddress          address;                     /**< Internet address of the host */
	public int                  incomingBandwidth;           /**< downstream bandwidth of the host */
	public int                  outgoingBandwidth;           /**< upstream bandwidth of the host */
	public int                  bandwidthThrottleEpoch;
	public int                  mtu;
	public int                  randomSeed;
	public int                  recalculateBandwidthLimits;
	public Pointer              peers;                       /**< array of peers allocated for this host */
	public NativeLong           peerCount;                   /**< number of peers allocated for this host */
	public NativeLong           channelLimit;                /**< maximum number of channels allowed for connected peers */
	public int                  serviceTime;
	public Pointer              dispatchQueue;
	public int                  continueSending;
	public NativeLong           packetSize;
	public short                headerFlags;
	public Pointer              commands;
	public NativeLong           commandCount;
	public Pointer              buffers;
	public NativeLong           bufferCount;
	public Pointer              checksum;                    /**< callback the user can set to enable packet checksums for this host */
	public Pointer              compressor;
	public Pointer              packetData;
	public ENetAddress          receivedAddress;
	public Pointer              receivedData;
	public NativeLong           receivedDataLength;
	public int                  totalSentData;               /**< total data sent, user should reset to 0 as needed to prevent overflow */
	public int                  totalSentPackets;            /**< total UDP packets sent, user should reset to 0 as needed to prevent overflow */
	public int                  totalReceivedData;           /**< total data received, user should reset to 0 as needed to prevent overflow */
	public int                  totalReceivedPackets;        /**< total UDP packets received, user should reset to 0 as needed to prevent overflow */
	
	public ENetHost() {
		super();
	}
	
	protected ByReference newByReference() { return new ByReference(); }
	protected ByValue newByValue() { return new ByValue(); }
	protected ENetHost newInstance() { return new ENetHost(); }
	public static class ByReference extends ENetHost implements Structure.ByReference {};
	public static class ByValue extends ENetHost implements Structure.ByValue {};
}