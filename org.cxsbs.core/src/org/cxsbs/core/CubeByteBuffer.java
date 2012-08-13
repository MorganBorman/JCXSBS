package org.cxsbs.core;

import java.nio.ByteBuffer;

/** A collection of methods for reading and writing low level Cube data streams.
 *  These methods are for the most part adapted from the original Cube 2 source code. */
public class CubeByteBuffer {
	public static void putint(ByteBuffer buffer, int i) {
		if (i < 128 && i > -127) {
			buffer.put((byte) ( i 		& 0xFF));
			
		} else if (i < 0x8000 && i >= -0x8000) {
			buffer.put((byte) ( 0x80 	& 0xFF));
			buffer.put((byte) ( i 		& 0xFF));
			buffer.put((byte) ((i>>8) 	& 0xFF));
			
		} else { 
			buffer.put((byte) ( 0x81 	& 0xFF));
			buffer.put((byte) ( i		& 0xFF));
			buffer.put((byte) ((i>>8)	& 0xFF));
			buffer.put((byte) ((i>>16)	& 0xFF));
			buffer.put((byte) ((i>>24)	& 0xFF));
		}
	}
	
	public static int getint(ByteBuffer buffer, boolean peek) {
		int pos = buffer.position();
		try
		{
			byte c = buffer.get();
			int n;
			if(c == -128) {
				n =  (buffer.get() & 0xFF);
				n |= (buffer.get()<<8);
				return n;
				
			} else if(c == -127) {
				n =  (buffer.get() & 0xFF);
				n |= ((buffer.get()<<8) & 0xFF00);
				n |= ((buffer.get()<<16) & 0xFF0000);
				n |= ((buffer.get()<<24) & 0xFF000000);
				return n;
				
			} else {
				return c;
			}
		}
		finally
		{
			if(peek) buffer.position(pos);
		}
	}
	
	public static void putuint(ByteBuffer buffer, long i) {
		assert(i >= 0);
		if(i < 0 || i >= (1<<21))
		{
			buffer.put((byte) (0x80 | (i & 0x7F)));
			buffer.put((byte) (0x80 | ((i >> 7) & 0x7F)));
			buffer.put((byte) (0x80 | ((i >> 14) & 0x7F)));
			buffer.put((byte) (i >> 21));
		}
		else if(i < (1<<7)) buffer.put((byte) i);
		else if(i < (1<<14))
		{
			buffer.put((byte) (0x80 | (i & 0x7F)));
			buffer.put((byte) (i >> 7));
		}
		else 
		{ 
			buffer.put((byte) (0x80 | (i & 0x7F))); 
			buffer.put((byte) (0x80 | ((i >> 7) & 0x7F)));
			buffer.put((byte) (i >> 14)); 
		}
	}
	
	public static long getuint(ByteBuffer buffer, boolean peek) {
		int pos = buffer.position();
		try
		{
			long n = buffer.get();
			if((n & 0x80) != 0)
			{
				n += (buffer.get() << 7) - 0x80;
				if((n & (1<<14)) != 0) 
					n += (buffer.get() << 14) - (1<<14);
				if((n & (1<<21)) != 0) 
					n += (buffer.get() << 21) - (1<<21);
				if((n & (1<<28)) != 0) 
					n |= -1<<28;
			}
			assert(n >= 0);
			return n;
		}
		finally
		{
			if(peek) buffer.position(pos);
		}
	}
	
	public static void putchar(ByteBuffer buffer, byte c) {
		buffer.put(c);
	}
	
	public static byte getchar(ByteBuffer buffer, boolean peek) {
		int pos = buffer.position();
		try
		{
			return buffer.get();
		}
		finally
		{
			if(peek) buffer.position(pos);
		}
	}
	
	public static void putfloat(ByteBuffer buffer, float f) {
		buffer.putFloat(f);
	}
	
	public static float getfloat(ByteBuffer buffer, boolean peek) {
		int pos = buffer.position();
		try
		{
			return buffer.getFloat();
		}
		finally
		{
			if(peek) buffer.position(pos);
		}
	}
	
	public static void putstring(ByteBuffer buffer, String s) {
		for(int i = 0; i < s.length(); i++)
		{
		   char c = s.charAt(i);
		   buffer.put((byte) c);
		}
		buffer.put((byte) 0);
	}
	
	public static String getstring(ByteBuffer buffer, boolean peek) {
		int pos = buffer.position();
		try
		{
			StringBuilder stringBuilder = new StringBuilder(buffer.limit());
			while (buffer.remaining() > 0)
			{
				char c = (char)buffer.get();
				if (c == '\0') break;
				stringBuilder.append(c);
			}
			return stringBuilder.toString();
		}
		finally
		{
			if(peek) buffer.position(pos);
		}
	}
}
