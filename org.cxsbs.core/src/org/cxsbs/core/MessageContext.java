package org.cxsbs.core;

public enum MessageContext {
	STC, // Server to Client specific
	CTS, // Client to Server specific
	COM, // Common message format
	NONE;// Message with no valid context
}
