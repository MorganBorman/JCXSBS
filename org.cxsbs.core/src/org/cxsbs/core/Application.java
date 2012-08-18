package org.cxsbs.core;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

public class Application implements IApplication {
	
	private Engine engineThread;
	
	@Override
	public Object start(IApplicationContext context) throws Exception {
		engineThread = new Engine();
		engineThread.start();
		return null;
	}

	@Override
	public void stop() {
		engineThread.shutdown();
	}

}
