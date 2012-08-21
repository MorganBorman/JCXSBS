package org.cxsbs.core.room;

import org.cxsbs.core.IClient;

public interface IRoom {
	int getRn();
	String getName();
	void setName();
	void addClient(IClient client);
	void update();
	int clientCount();
	void broadcast();
}
