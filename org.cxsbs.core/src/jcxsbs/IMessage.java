package jcxsbs;

import java.util.Map;
import java.util.Set;

public interface IMessage extends Map<Field, FieldValue> {

	public Set<String> keyNames();

	public FieldValue getField(String name);

//	public Set<Field> keySet();
		
//	public FieldValue getField(Field field);
	
//	public boolean put(Field field, FieldValue fieldValue);
	
//	public short getShortField(String key);
//	public void putShort(String key, short value);
//	
//	public int getIntField(String key);
//	public void putInt(String key, int value);
//	
//	public long getLongField(String key);
//	public void putLong(String key, long value);
//	
//	public float getFloatField(String key); // 4 byte
//	public void putFloat(String key, float value);
//	
//	public double getDoubleField(String key); // 8 byte
//	public void putDouble(String key, double value);
//	
//	public String getStringField(String key);
//	public void putString(String key, String value);

}
