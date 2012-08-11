package jcxsbs;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Message implements IMessage {

	private final ByteBuffer buffer;
	
	private final MessageType messageType;
	
	private final Map<Field, FieldValue> fieldValues = new HashMap<Field, FieldValue>();

//	Fragment[] fragments = new Fragment[] { Player, Weapon, Time, Shots };

	public Message(ByteBuffer buffer) {
		this.buffer = buffer;
		this.messageType = null;
		decompose(buffer);
	}
	
	private IMessage decompose(ByteBuffer buffer) {
		int offset = 0;
		for (Field field : fields) {
			field.parse(buffer, this);
		}
	}

	@Override
	public void clear() {
		fieldValues.clear();
	}

	@Override
	public boolean containsKey(Object key) {
		return fieldValues.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return fieldValues.containsValue(value);
	}

	@Override
	public Set<java.util.Map.Entry<Field, FieldValue>> entrySet() {
		return fieldValues.entrySet();
	}

	@Override
	public FieldValue get(Object key) {
		return fieldValues.get(key);
	}

	@Override
	public boolean isEmpty() {
		return fieldValues.isEmpty();
	}

	@Override
	public Set<Field> keySet() {
		return fieldValues.keySet();
	}

	@Override
	public FieldValue put(Field key, FieldValue value) {
		return fieldValues.put(key, value);
	}

	@Override
	public void putAll(Map<? extends Field, ? extends FieldValue> map) {
		return fieldValues.putAll(map);
	}

	@Override
	public FieldValue remove(Object key) {
		return fieldValues.remove(key);
	}

	@Override
	public int size() {
		return fieldValues.size();
	}

	@Override
	public Collection<FieldValue> values() {
		return fieldValues.values();
	}

	@Override
	public Set<String> keyNames() {
		final Set<String> names = new HashSet<String>();
		for (Field field : fieldValues.keySet()) {
			names.add(field.getName());
		}
		return names;
	}

	@Override
	public FieldValue getField(String name) {
		for (Field field : fieldValues.keySet()) {
			if (field.getName().equals(name)) {
				return fieldValues.get(field);
			}
		}
		return null;
	}
	
}
