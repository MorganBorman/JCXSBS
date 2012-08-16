package org.cxsbs.core.field;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FieldMap implements IFieldMap {
	
	private final Map<Field, IFieldValue> fieldValues = new HashMap<Field, IFieldValue>();

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
	public Set<java.util.Map.Entry<Field, IFieldValue>> entrySet() {
		return fieldValues.entrySet();
	}

	@Override
	public IFieldValue get(Object key) {
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
	public IFieldValue put(Field key, IFieldValue value) {
		return fieldValues.put(key, value);
	}

	@Override
	public void putAll(Map<? extends Field, ? extends IFieldValue> map) {
		fieldValues.putAll(map);
	}

	@Override
	public IFieldValue remove(Object key) {
		return fieldValues.remove(key);
	}

	@Override
	public int size() {
		return fieldValues.size();
	}

	@Override
	public Collection<IFieldValue> values() {
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
	public IFieldValue getField(String name) {
		for (Field field : fieldValues.keySet()) {
			if (field.getName().equals(name)) {
				return fieldValues.get(field);
			}
		}
		return null;
	}
	
}
