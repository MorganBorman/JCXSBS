package nativeenet;

import com.sun.jna.Structure;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

public class ENetPeer extends Structure {
	public Pointer       dispatchList;
	public ENetHost      host;
	public short         outgoingPeerID;
	public short         incomingPeerID;
	public int           connectID;
	public byte          outgoingSessionID;
	public byte          incomingSessionID;
	public ENetAddress   address;            /**< Internet address of the peer */
	public NativeLong    data;               /**< Application private data, may be freely modified */
	public int           state;
	public Pointer       channels;
	public NativeLong    channelCount;       /**< Number of channels allocated for communication with peer */
	public int           incomingBandwidth;  /**< Downstream bandwidth of the client in bytes/second */
	public int           outgoingBandwidth;  /**< Upstream bandwidth of the client in bytes/second */
	public int           incomingBandwidthThrottleEpoch;
	public int           outgoingBandwidthThrottleEpoch;
	public int           incomingDataTotal;
	public int           outgoingDataTotal;
	public int           lastSendTime;
	public int           lastReceiveTime;
	public int           nextTimeout;
	public int           earliestTimeout;
	public int           packetLossEpoch;
	public int           packetsSent;
	public int           packetsLost;
	public int           packetLoss;          /**< mean packet loss of reliable packets as a ratio with respect to the constant ENET_PEER_PACKET_LOSS_SCALE */
	public int           packetLossVariance;
	public int           packetThrottle;
	public int           packetThrottleLimit;
	public int           packetThrottleCounter;
	public int           packetThrottleEpoch;
	public int           packetThrottleAcceleration;
	public int           packetThrottleDeceleration;
	public int           packetThrottleInterval;
	public int           pingInterval;
	public int           timeoutLimit;
	public int           timeoutMinimum;
	public int           timeoutMaximum;
	public int           lastRoundTripTime;
	public int           lowestRoundTripTime;
	public int           lastRoundTripTimeVariance;
	public int           highestRoundTripTimeVariance;
	public int           roundTripTime;            /**< mean round trip time (RTT), in milliseconds, between sending a reliable packet and receiving its acknowledgement */
	public int           roundTripTimeVariance;
	public int           mtu;
	public int           windowSize;
	public int           reliableDataInTransit;
	public short         outgoingReliableSequenceNumber;
	public Pointer       acknowledgements;
	public Pointer       sentReliableCommands;
	public Pointer       sentUnreliableCommands;
	public Pointer       outgoingReliableCommands;
	public Pointer       outgoingUnreliableCommands;
	public Pointer       dispatchedCommands;
	public int           needsDispatch;
	public short         incomingUnsequencedGroup;
	public short         outgoingUnsequencedGroup;
	public int[]         unsequencedWindow = new int[1024/32]; 
	public int           eventData;
	
	public ENetPeer() {
		super();
		data = new NativeLong(-1);
	}
	
	public void setData(int i) {
		data = new NativeLong(i);
	}
	
	public int getData() {
		return data.intValue();
	}
	
	protected ByReference newByReference() { return new ByReference(); }
	protected ByValue newByValue() { return new ByValue(); }
	protected ENetPeer newInstance() { return new ENetPeer(); }
	public static class ByReference extends ENetPeer implements Structure.ByReference {};
	public static class ByValue extends ENetPeer implements Structure.ByValue {};
}