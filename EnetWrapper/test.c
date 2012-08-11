#include <stdlib.h>
#include "EnetWrapper.h"

int main(int argc, char* argv[])
{
	if(!ew_initialize((char*)"127.0.0.1", 28785, 10, 3, 0, 0)) {
		return -1;
	}
	
	ew_Event* event = (ew_Event*)malloc(sizeof(ew_Event));
	
	event->type = ew_NONE;
	event->clientid = -1;
	event->data = NULL;
	event->channel = 0;
	
	while(!ew_service(5, event)) {
		
	}
	
	ew_deinitialize();
	return 0;
}