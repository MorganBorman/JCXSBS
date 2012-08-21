package org.cxsbs.core;

import java.nio.ByteBuffer;

import nativeenet.ENetPacket;
import nativeenet.ENetPacketFlag;
import nativeenet.ENetPeer;
import nativeenet.NativeEnetLibrary;

public class Client implements IClient {
	private final int cn;
	private final ENetPeer peer;
	private final ClientState state;
	
	public Client(int cn, ENetPeer peer) {
		this.cn = cn;
		this.peer = peer;
		this.state = new ClientState();
	}

	@Override
	public int getCn() {
		return this.cn;
	}

	@Override
	public void send(int channel, ByteBuffer buffer, boolean reliable) {
		long datalen = buffer.position();
		buffer.rewind();
		ENetPacket packet = NativeEnetLibrary.enet_packet_create(buffer, datalen, reliable ? ENetPacketFlag.RELIABLE.ordinal() : 0);
		NativeEnetLibrary.enet_peer_send(peer, (byte)channel, packet);
	}

	@Override
	public void disconnect(int reason, String message) {
		//TODO: send a message and schedule a disconnect for this client
		NativeEnetLibrary.enet_peer_disconnect_now(peer, reason);
	}

	@Override
	public void disconnect(int reason) {
		NativeEnetLibrary.enet_peer_disconnect_now(peer, reason);
	}

	public ClientState getState() {
		return state;
	}

}
