package org.cxsbs.core;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import nativeenet.ENetAddress;
import nativeenet.ENetEvent;
import nativeenet.ENetEventType;
import nativeenet.ENetHost;
import nativeenet.NativeEnetLibrary;

import org.cxsbs.core.field.StringFieldValue;
import org.cxsbs.core.message.IMessage;
import org.cxsbs.core.message.MessageContext;
import org.cxsbs.core.message.MessageType;
import org.cxsbs.core.message.MessageWriter;

public class Engine extends Thread {
	private boolean encounteredError = false;
	private volatile boolean stayAlive = true;
	private Map<Integer, IClient> connectedClients;
	
	static final int maxClients = 5;
	
	TemporaryIdentifierPool clientNumberPool;
	
	/**
	 * @param args
	 */
	public void run() {
		clientNumberPool = new TemporaryIdentifierPool(maxClients);
		connectedClients = new HashMap<Integer, IClient>();
		
		if(NativeEnetLibrary.enet_initialize() != 0) {
			System.out.println("Error initializing enet.");
			return;
		}
		try {
			ENetAddress address = new ENetAddress();
			NativeEnetLibrary.enet_address_set_host(address, "localhost");
			address.port = 28785;

			ENetHost host = NativeEnetLibrary.enet_host_create(address, maxClients, 3, 0, 0);
			
			ENetEvent event = new ENetEvent();
			
			while(stayAlive) {
				boolean serviced = false;
				while(!serviced)
				{
					if(NativeEnetLibrary.enet_host_check_events(host, event) <= 0)
					{
						if(NativeEnetLibrary.enet_host_service(host, event, 5) <= 0) break;
						serviced = true;
					}
					
					int cn;
					IClient client;
					ByteBuffer buffer;
					
					if(event.type == ENetEventType.CONNECT.ordinal()) {
						try {
							cn = clientNumberPool.get();
							client = new Client(cn, event.peer);
							event.peer.setData(cn);
							connectedClients.put(cn, client);
							System.out.println("client connected: " + cn);
							
							buffer = ByteBuffer.allocate(100);
							MessageWriter.PutServerInfo(buffer, client, false, "Java CXSBS Test Server");
							client.send(1, buffer, true);
							
						} catch (PoolEmptyException e) {
							NativeEnetLibrary.enet_peer_disconnect(event.peer, 7); // DISC_MAXCLIENTS = 7
						}
						
					} else if (event.type == ENetEventType.RECEIVE.ordinal()) {
						cn = event.peer.getData();
						client = connectedClients.get(cn);
						if (client != null) {
							buffer = event.packet.GetByteBuffer();
							
							System.out.println("data received: " + cn + " buffer size: " + buffer.capacity());
							
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
						}
					} else if (event.type == ENetEventType.DISCONNECT.ordinal()) {
						cn = event.peer.getData();
						client = connectedClients.get(cn);
						if (client != null) {
							connectedClients.remove(new Integer(cn));
							
							System.out.println("client disconnected: " + client.getCn());
						}
					} else {
						
					}
				}
			}
		}
		finally {
			NativeEnetLibrary.enet_deinitialize();
		}
	}
	
	public void shutdown() {
		stayAlive = false;
	}

}
