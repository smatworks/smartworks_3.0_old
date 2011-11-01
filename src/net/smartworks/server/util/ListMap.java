package net.smartworks.server.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ListMap extends HashMap {
	private static final long serialVersionUID = 1L;
	private List keyList = new ArrayList();
	private List valueList = new ArrayList();
	
	public ListMap() {
		super();
	}

	public ListMap(int size) {
		super(size);
		keyList = new ArrayList();
		valueList = new ArrayList();
	}

	public ListMap(int size, float arg1) {
		super(size, arg1);
		keyList = new ArrayList();
		valueList = new ArrayList();
	}

	public ListMap(Map map) {
		super(map);
		keyList = new ArrayList();
		valueList = new ArrayList();
		this.addList(map);
	}
	
	public Object put(Object key, Object value) {
		this.addList(key, value);
		return super.put(key, value);
	}
	
	public void putAll(Map map) {
		this.addList(map);
		super.putAll(map);
	}
	
	public Object remove(Object key) {
		if (keyList.contains(key)) {
			keyList.remove(key);
			valueList.remove(super.get(key));
		}
		return super.remove(key);
	}
	
	public List valueList() {
		return valueList;
	}
	
	public Collection values() {
		return this.valueList();
	}
	
	public List keyList() {
		return keyList;
	}
	
	private void addList(Map map) {
		for (Iterator keyItr = map.keySet().iterator(); keyItr.hasNext();) {
			Object key = keyItr.next();
			addList(key, map.get(key));
		}
	}
	
	private void addList(Object key, Object value) {
		if (key == null)
			return;
		if (value == null) {
			if (super.containsKey(key))
				this.remove(key);
			return;
		}
		if (keyList.contains(key)) {
			valueList.remove(super.get(key));
			keyList.remove(key);
		}
		keyList.add(key);
		valueList.add(value);
	}
	
	public void clear() {
		super.clear();
		keyList.clear();
		valueList.clear();
	}
	
	public Enumeration keys() {
		return new Enumerator(keyList.iterator());
	}
	
	private class Enumerator implements Enumeration {
		private Iterator itr = null;
		
		Enumerator(Iterator itr) {
			this.itr = itr;
		}
		
		public boolean hasMoreElements() {
			return itr.hasNext();
		}

		public Object nextElement() {
			return itr.next();
		}
		
	}

}
