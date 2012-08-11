package org.cxsbs.core;

import java.nio.ByteBuffer;

public interface IField {
	
	public String getName();
	
	public FieldType getType();
	
	public void parse(ByteBuffer buffer, Message message);

}
