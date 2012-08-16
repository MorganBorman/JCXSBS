package org.cxsbs.core.message;

import org.cxsbs.core.field.IFieldMap;

public interface IMessage extends IFieldMap {
	
	public MessageType getMessageType();
	
}
