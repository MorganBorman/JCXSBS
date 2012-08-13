package org.cxsbs.core.tests;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;

import org.cxsbs.core.CubeByteBuffer;
import org.junit.Test;

public class CubeByteBufferTest {
	
	@Test
	public void testInts() {
		ByteBuffer buffer = ByteBuffer.allocate(5000);
			int[] testInts = new int[] {-500000, -5000, -326, -123, 0, 31, 88, 133, 255, 1007, 5000, 123456};
			
			for(int i : testInts) {
				buffer.rewind();
				CubeByteBuffer.putint(buffer, i);
				buffer.rewind();
				assertEquals(i, CubeByteBuffer.getint(buffer, false));
			}
	}
	
	@Test
	public void testUInts() {
		ByteBuffer buffer = ByteBuffer.allocate(5000);
			int[] testInts = new int[] {0, 31, 88, 133, 255, 1007, 5000, 123456};
			
			for(int u : testInts) {
				buffer.rewind();
				CubeByteBuffer.putint(buffer, u);
				buffer.rewind();
				assertEquals(u, CubeByteBuffer.getint(buffer, false));
			}
	}

}
