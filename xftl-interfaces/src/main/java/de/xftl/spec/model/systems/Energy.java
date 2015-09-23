package de.xftl.spec.model.systems;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/** Immutable! */
public final class Energy implements Comparable<Energy> {

	private static final int ZERO = 0;
	private static final Map<Integer, Energy> _cache = new HashMap<>();
	
	private Integer _value;
	
	private Energy(Integer value) {
		_value = value;
	}
	
	/** Not thread-safe! */
	public static Energy valueOf(int pValue) {
		if (pValue < ZERO) pValue = ZERO;
		
		Integer value = Integer.valueOf(pValue);
		
		Energy ret = _cache.get(value);
		if (ret == null) {
			ret = new Energy(value);
			_cache.put(value, ret);
		}
		
		return ret;
	}
	
	public int intValue() {
		return _value.intValue();
	}
	
	public Energy plus(Energy other) {
		return valueOf(Integer.valueOf(_value.intValue() + other._value.intValue()));
	}
	
	public Energy minus(Energy other) {
		return valueOf(Integer.valueOf(_value.intValue() - other._value.intValue()));
	}
	
	@Override
	public int compareTo(Energy other) {
		return _value.compareTo(other._value);
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null) return false;
		if (this == obj) return true;
		if (!getClass().equals(obj)) return false;
		
		final Energy e = (Energy) obj;
		return _value.equals(e._value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getClass(), _value);
	}

	@Override
	public String toString() {
		return String.format("%d energy", _value);
	}
}
