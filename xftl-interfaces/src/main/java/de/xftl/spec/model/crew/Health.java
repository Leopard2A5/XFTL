package de.xftl.spec.model.crew;


/** Mutable! */
public final class Health {

	private static final int ZERO = 0;
	private static final int MAX = 100;
	
	private int _value;
	
	public Health() {
		_value = MAX;
	}
	
	public int getValue() {
		return _value;
	}

	public void setValue(int value) {
		if (value < ZERO) value = ZERO;
		if (value > MAX) value = MAX;
		
		_value = value;
	}

	public void decrease(int amount) {
		_value = Math.max(ZERO, _value - amount);
	}

	public void increase(int amount) {
		_value = Math.min(MAX, _value + amount);
	}
	
	@Override
	public int hashCode() {
		return Integer.valueOf(_value).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Health) {
			Health o = (Health) obj;
			
			return _value == o._value; 
		}
		
		return false;
	}

	@Override
	public String toString() {
		return String.format("%d health", _value);
	}
}
