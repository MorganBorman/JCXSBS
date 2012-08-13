package org.cxsbs.core;

import java.nio.ByteBuffer;

public class ClientPositionFieldValue extends FieldValue implements IFieldValue {
	public final int clientnum;
	public final ByteBuffer fullPositionBuffer;
	
	public ClientPositionFieldValue(IField field, int clientnum, ByteBuffer fullPositionBuffer) {
		super(field);
		this.clientnum = clientnum;
		this.fullPositionBuffer = fullPositionBuffer;
	}
}
