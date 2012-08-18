package nativeenet;

import java.nio.ByteBuffer;

import com.ochafik.lang.jnaerator.runtime.LibraryExtractor;
import com.ochafik.lang.jnaerator.runtime.MangledFunctionMapper;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.NativeLong;

public class NativeEnetLibrary implements Library {
	public static final String JNA_LIBRARY_NAME = LibraryExtractor.getLibraryPath("enet", true, NativeEnetLibrary.class);
	public static final NativeLibrary JNA_NATIVE_LIB = NativeLibrary.getInstance(NativeEnetLibrary.JNA_LIBRARY_NAME, MangledFunctionMapper.DEFAULT_OPTIONS);
	static {
		Native.register(NativeEnetLibrary.JNA_LIBRARY_NAME);
	}
	
	public static native int enet_initialize();
	public static native void enet_deinitialize();
	
	public static native ENetHost enet_host_create(ENetAddress address, NativeLong maxclients, NativeLong channels, int maxdown, int maxup);
	public static ENetHost enet_host_create(ENetAddress address, long maxclients, long channels, int maxdown, int maxup) {
		return enet_host_create(address, new NativeLong(maxclients), new NativeLong(channels), maxdown, maxup);
	}
	
	public static native int enet_host_service(ENetHost host, ENetEvent event, int timeout);
	public static native ENetPeer enet_host_connect(ENetHost host, ENetAddress address, NativeLong channels, int data);
	public static ENetPeer enet_host_connect(ENetHost host, ENetAddress address, long channels, int data) {
		return enet_host_connect(host, address, new NativeLong(channels), data);
	}
	
	public static native int enet_host_check_events(ENetHost host, ENetEvent event);
	public static native void enet_host_flush(ENetHost host);
	public static native void enet_host_destroy(ENetHost host);
	public static native void enet_host_bandwidth_limit (ENetHost host, int maxdown, int maxup);
	public static native void enet_host_bandwidth_throttle (ENetHost host);
	
	public static native int enet_peer_send(ENetPeer peer, byte channel, ENetPacket packet);
	public static native void enet_peer_disconnect(ENetPeer peer, int reason);
	public static native void enet_peer_disconnect_now(ENetPeer peer, int reason);
	public static native void enet_peer_disconnect_later(ENetPeer peer, int reason);
	public static native void enet_peer_throttle_configure(ENetPeer peer, int interval, int acceleration, int deceleration);
	
	public static native ENetPacket enet_packet_create(ByteBuffer data, NativeLong datalen, int flags);
	public static ENetPacket enet_packet_create(ByteBuffer data, long datalen, int flags) {
		return enet_packet_create(data, new NativeLong(datalen), flags);
	}
	
	public static native void enet_packet_destroy(ENetPacket packet);
	
	public static native int enet_address_set_host (ENetAddress address, String hostName);
	
	
}
