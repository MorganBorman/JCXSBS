package org.cxsbs.core;

import java.nio.ByteBuffer;
import java.util.List;

import nativeenet.ENetAddress;
import nativeenet.ENetEvent;
import nativeenet.ENetEventType;
import nativeenet.ENetHost;
import nativeenet.ENetPacket;
import nativeenet.ENetPacketFlag;
import nativeenet.ENetPeer;
import nativeenet.NativeEnetLibrary;

import com.sun.jna.NativeLong;

public class Engine extends Thread {
	private boolean encounteredError = false;
	private volatile boolean stayAlive = true;
	private List<Integer> connectedClients;
	
	/**
	 * @param args
	 */
	public void run() {
		/*
		if(NativeEnetLibrary.enet_initialize() != 0) {
			System.out.println("Error initializing enet.");
			return;
		}
		try {
			ENetAddress address = new ENetAddress();
			NativeEnetLibrary.enet_address_set_host(address, "localhost");
			address.port = 28785;
			
			ENetHost host = NativeEnetLibrary.enet_host_create(address, new NativeLong(3), new NativeLong(3), 0, 0);
			
			ENetEvent event = new ENetEvent();
			
			while(true) {
				boolean serviced = false;
				while(!serviced)
				{
					if(NativeEnetLibrary.enet_host_check_events(host, event) <= 0)
					{
						if(NativeEnetLibrary.enet_host_service(host, event, 5) <= 0) break;
						serviced = true;
					}
					
					ByteBuffer buffer;
					
					if(event.type == ENetEventType.CONNECT.ordinal()) {
						System.out.println("client connected.");
						
						buffer = ByteBuffer.allocate(100);
						
						CubeByteBuffer.putint(buffer, 1); //N_SERVINFO = 1
						CubeByteBuffer.putint(buffer, 0);
						CubeByteBuffer.putint(buffer, 258); //PROTOCOL_VERSION = 258
						CubeByteBuffer.putint(buffer, 0);
						CubeByteBuffer.putint(buffer, 0);
						CubeByteBuffer.putstring(buffer, "Java CXSBS Test Server");
						
						long datalen = buffer.position();
						buffer.rewind();
						
						ENetPacket packet = NativeEnetLibrary.enet_packet_create(buffer, new NativeLong(datalen), ENetPacketFlag.ENET_PACKET_FLAG_RELIABLE.ordinal());
						
						NativeEnetLibrary.enet_peer_send(event.peer, (byte)1, packet);
						
					} else if (event.type == ENetEventType.RECEIVE.ordinal()) {
						buffer = event.packet.data.getByteBuffer(0, event.packet.dataLength);
						
						if (event.channelID == 1) {
							while(buffer.position() < buffer.limit()) {
								IMessage message = MessageType.parseMessage(buffer, MessageContext.CTS);
								
								if(message.getMessageType() == MessageType.CONNECT) {
									System.out.println("clientid: CONNECT");
									StringFieldValue sfv = (StringFieldValue)message.getField("name");
									System.out.println("\tname: " + sfv.value);
								} else if(message.getMessageType() == MessageType.PING) {
									
								} else {
									System.out.println("message: " + message.getMessageType().toString());
								}
							}
						}
					} else if (event.type == ENetEventType.DISCONNECT.ordinal()) {
						System.out.println("client disconnected.");
						
						//connectedClients.remove(new Integer(event.clientid));
					} else {
						
					}
				}
			}
		}
		finally {
			NativeEnetLibrary.enet_deinitialize();
		}
		*/
		System.out.println("Hello there.");
	}
	
	public void shutdown() {
		stayAlive = false;
	}

}
