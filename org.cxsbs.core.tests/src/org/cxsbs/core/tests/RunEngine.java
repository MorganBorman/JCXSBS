package org.cxsbs.core.tests;

import org.cxsbs.core.Engine;

public class RunEngine {

	private static Engine engineThread;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		engineThread = new Engine();
		engineThread.start();
		
		try {
			engineThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
			engineThread.shutdown();
		}
	}

}
