package oop.ex2.misc;

import java.util.*;

import oop.ex2.compiler.Member;

/**
 * this class implements a map that unite two maps
 * the result map contains both the maps that construct it
 * operations that done only on one map are done only on the second one
 * @author Ohad Cohen <ohadcn@cs.huji.ac.il>
 */
public class DualMap implements Map<String, Member> {

	//the two maps constructing this map
	private Map<String,Member> first,second;
	
	/**
	 * construct a new DualMap object
	 * operations that done only on one map are done only on m2
	 * @param m1 the first map
	 * @param m2 the second map
	 */
	public DualMap(Map<String,Member> m1,Map<String,Member> m2) {
		this.first=m1;
		this.second=m2;
	}
	
	//this is an overridden method, so it inherits the javadoc of the parent
	@Override
	public boolean containsKey(Object key) {
		return first.containsKey(key)||second.containsKey(key);
	}

	//this is an overridden method, so it inherits the javadoc of the parent
	@Override
	public boolean containsValue(Object value) {
		return first.containsValue(value)||second.containsValue(value);
	}

	//this is an overridden method, so it inherits the javadoc of the parent
	@Override
	public Set<Entry<String, Member>> entrySet() {
		Set<Entry<String, Member>> result = first.entrySet();
		result.addAll(second.entrySet());
		return result;
	}

	/**
	 * Returns the value to which the specified key is mapped,
	 * or null if this map contains no mapping for the key.
	 * if both the maps has mapping for that key, the return value is the mapping in the second map,
	 * the one who got as m2 in the constructor
	 * @param key the key whose associated value is to be returned 
	 * @return      the value to which the specified key is mapped,
	 * or null if this map contains no mapping for the key.
	 */
	@Override
	public Member get(Object key) {
		Member result=second.get(key);
		if(result==null)
			result=first.get(key);
		return result;
	}

	//this is an overridden method, so it inherits the javadoc of the parent
	@Override
	public boolean isEmpty() {
		return first.isEmpty() && second.isEmpty();
	}

	//this is an overridden method, so it inherits the javadoc of the parent
	@Override
	public Set<String> keySet() {
		Set<String> result = first.keySet();
		result.addAll(second.keySet());
		return result;
	}

	//this is an overridden method, so it inherits the javadoc of the parent
	@Override
	public int size() {
		return first.size()+second.size();
	}

	
	//this is an overridden method, so it inherits the javadoc of the parent
	@Override
	public Collection<Member> values() {
		Collection<Member> result = first.values();
		result.addAll(second.values());
		return result;
	}
	
	/**
	 * Associates the specified value with the specified key in this map.
	 * If the map previously contained a mapping for the key, the old value is replaced by the specified value.
	 * the value is associated in the second map (the one that passed as m2 to the constructor) 
	 * @param key key with which the specified value is to be associated
	 * @param value value to be associated with the specified key
	 * @return the previous value associated with key,
	 * or null if there was no mapping for key.
	 */
	@Override
	public Member put(String key, Member value) {
		Member old=get(key);
		second.put(key, value);
		return old;
	}

	/**
	 * Copies all of the mappings from the specified map to the second map (the one that passed as m2 parameter).
	 * The effect of this call is equivalent to that of calling put(k, v) on this map
	 * once for each mapping from key k to value v in the specified map.
	 * @param m mappings to be stored in this map
	 */
	@Override
	public void putAll(Map<? extends String, ? extends Member> m) {
		second.putAll(m);
	}

	//this is an overridden method, so it inherits the javadoc of the parent
	@Override
	public Member remove(Object key) {
		Member result = get(key);
		if(second.containsKey(key))
			second.remove(key);
		if(first.containsKey(key))
			first.remove(key);
		return result;
	}

	//this is an overridden method, so it inherits the javadoc of the parent
	@Override
	public void clear() {
		first.clear();
		second.clear();
	}

}
