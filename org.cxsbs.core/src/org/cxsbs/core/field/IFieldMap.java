package org.cxsbs.core.field;

import java.util.Map;
import java.util.Set;

public interface IFieldMap extends Map<Field, IFieldValue> {

	public Set<String> keyNames();

	public IFieldValue getField(String name);

}
