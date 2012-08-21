package org.cxsbs.core;

import java.util.ArrayList;
import java.util.List;

public class TemporaryIdentifierPool {
	
	private List<Integer> availableIdentifiers;
	private List<Integer> inUseIdentifiers;
	
	public TemporaryIdentifierPool(int size) {
		availableIdentifiers = new ArrayList<Integer>();
		inUseIdentifiers = new ArrayList<Integer>();
		
		for(int i=0; i < size; i++) {
			availableIdentifiers.add(i);
		}
	}
	
	public int get() throws PoolEmptyException {
		if(availableIdentifiers.size() <= 0) throw new PoolEmptyException();
		
		int i = availableIdentifiers.get(0);
		availableIdentifiers.remove(0);
		inUseIdentifiers.add(i);
		return i;
	}
	
	public void free(int i) throws InvalidFreeException {
		if (!inUseIdentifiers.contains(i)) throw new InvalidFreeException();
		
		inUseIdentifiers.remove(i);
		availableIdentifiers.add(i);
	}
}
