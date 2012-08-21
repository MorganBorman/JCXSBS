package org.cxsbs.core;

import java.nio.ByteBuffer;

public interface IClient {
	public int getCn();
	public void send(int channel, ByteBuffer buffer, boolean reliable);
	public void disconnect(int reason, String message);
	public void disconnect(int reason);
	public ClientState getState();
}
