package org.cxsbs.core;

import java.nio.ByteBuffer;

public class CubeByteBuffer {
	public static void putint(ByteBuffer buffer, int i) {
	    if(i<128 && i>-127) buffer.put((byte) i);
	    else if(i<0x8000 && i>=-0x8000) { buffer.put((byte) 0x80); buffer.put((byte) i); buffer.put((byte) (i>>8)); }
	    else { buffer.put((byte) 0x81); buffer.put((byte) i); buffer.put((byte) (i>>8)); buffer.put((byte) (i>>16)); buffer.put((byte) (i>>24)); }
	}
	
	public static int getint(ByteBuffer buffer, boolean peek) {
		int pos = buffer.position();
		try
		{
			return 0;
		}
		finally
		{
			if(peek) buffer.position(pos);
		}
	}
	
	public static void putuint(ByteBuffer buffer, long i) {
		
	}
	
	public static long getuint(ByteBuffer buffer, boolean peek) {
		int pos = buffer.position();
		try
		{
			return 0;
		}
		finally
		{
			if(peek) buffer.position(pos);
		}
	}
	
	public static void putchar(ByteBuffer buffer, char c) {
		
	}
	
	public static char getchar(ByteBuffer buffer, boolean peek) {
		int pos = buffer.position();
		try
		{
			return 0;
		}
		finally
		{
			if(peek) buffer.position(pos);
		}
	}
	
	public static void putuchar(ByteBuffer buffer, char c) {
		
	}
	
	public static char getuchar(ByteBuffer buffer, boolean peek) {
		int pos = buffer.position();
		try
		{
			return 0;
		}
		finally
		{
			if(peek) buffer.position(pos);
		}
	}
	
	public static void putfloat(ByteBuffer buffer, float f) {
		
	}
	
	public static float getfloat(ByteBuffer buffer, boolean peek) {
		int pos = buffer.position();
		try
		{
			return (float)0;
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
			return "";
		}
		finally
		{
			if(peek) buffer.position(pos);
		}
	}
}
