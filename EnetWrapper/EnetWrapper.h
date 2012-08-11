#ifndef ENET_WRAPPER_H
#define ENET_WRAPPER_H

#ifdef __cplusplus
extern "C" {
#endif

enum {ew_NONE, ew_CONNECT, ew_DISCONNECT, ew_DATA};

typedef struct {
	int clientid;
	int type;
	int channel;
	unsigned char* data;
	int datalen;
} ew_Event;

bool ew_initialize(char* interface, int port, int maxclients, int channels, int maxdown, int maxup);
bool ew_service(int timeout, ew_Event* event);
bool ew_send(int clientid, int channel, bool reliable, unsigned char* data, long datalen);
bool ew_disconnect(int clientid, int reason);
void ew_deinitialize();

#ifdef __cplusplus
} /* closing brace for extern "C" */
#endif

#endif