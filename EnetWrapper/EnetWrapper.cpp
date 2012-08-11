 #include "EnetWrapper.h"
 #include <stdlib.h>
 #include <stdio.h>
 #include <string.h>
 #include <enet/enet.h>
 
 #include <map>
 
 int host_maxclients = 0;
ENetHost * host;

#define DISC_MAXCLIENTS 7

typedef struct {
	int clientid;
	ENetPeer *peer;
} Client;

std::map<int, Client*> clients;

Client* addclient(ENetEvent* event)
{
	int clientid = -1;
	for(int i = clients.size(); i < host_maxclients; i++) {
		if(!clients.count(i)) {
			clientid = i;
			break;
		}
	}
	if(clientid < 0) {
		fprintf(stderr, "Could not find a client number for the new client.\n");
		enet_peer_disconnect(event->peer, DISC_MAXCLIENTS);
		return NULL;
	}
	
	Client* client = new Client;
	client->clientid = clientid;
	client->peer = event->peer;
	event->peer->data = client;
	clients[clientid] = client;
	
	return client;
}

#ifdef __cplusplus
extern "C" {
#endif
 
bool ew_initialize(char* interface, int port, int maxclients, int channels, int maxdown, int maxup)
{
	if (enet_initialize () != 0) return false;
	
	host_maxclients = maxclients;
	
	ENetAddress address;
	address.port = port;
	enet_address_set_host (&address, interface);
	
	host  = enet_host_create (&address, maxclients, channels, maxdown, maxup);
	if (!host) return false;
	
	return true;
}

void clear_event(ew_Event* event)
{
	event->type = ew_NONE;
	event->clientid = -1;
	if(event->data) delete event->data;
	event->data = NULL;
	event->datalen = 0;
	event->channel = 0;
}

bool ew_service(int timeout, ew_Event* event)
{
	if(!host) return false;
	
	clear_event(event);
	
	ENetEvent enet_event;
	
	if(enet_host_service(host, &enet_event, timeout) <= 0) return true;
	
	Client* client;
	
	switch (enet_event.type) {
		case ENET_EVENT_TYPE_CONNECT:
			client = addclient(&enet_event);
			if(!client) return true;
			
			event->type = ew_CONNECT;
			event->clientid = client->clientid;
			break;
		
		case ENET_EVENT_TYPE_RECEIVE:
			event->type = ew_DATA;
			event->clientid = (int)((Client*)enet_event.peer->data)->clientid;
			event->channel = enet_event.channelID;
			event->data = new unsigned char[enet_event.packet->dataLength];
			memcpy(event->data, enet_event.packet->data, enet_event.packet->dataLength);
			event->datalen = enet_event.packet->dataLength;
		
			enet_packet_destroy (enet_event.packet);
			break;
		
		case ENET_EVENT_TYPE_DISCONNECT:
			if(!enet_event.peer->data) return true;
		
			event->type = ew_DISCONNECT;
			event->clientid = (int)((Client*)enet_event.peer->data)->clientid;
			
			enet_event.peer->data = NULL;
			clients.erase(event->clientid);
		
		case ENET_EVENT_TYPE_NONE:
			break;
	}
	
	return true;
}

bool ew_send(int clientid, int channel, bool reliable, unsigned char* data, long datalen)
{
	if(!host) return false;
	if(!clients.count(clientid)) return false;
	
	Client* client = clients[clientid];
	
	ENetPacket * packet = enet_packet_create(data, datalen, reliable ? ENET_PACKET_FLAG_RELIABLE : 0);
	
	enet_peer_send(client->peer, channel, packet);
	enet_host_flush(host);
}

bool ew_disconnect(int clientid, int reason)
{
	if(!host) return false;
	if(!clients.count(clientid)) return false;
	
	Client* client = clients[clientid];
	enet_peer_disconnect(client->peer, reason);
	enet_host_flush(host);
}

void ew_deinitialize()
{
	if(host) enet_host_destroy(host);
	enet_deinitialize();
}

#ifdef __cplusplus
} /* closing brace for extern "C" */
#endif