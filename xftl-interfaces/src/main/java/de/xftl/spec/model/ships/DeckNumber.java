package de.xftl.spec.model.ships;

public class DeckNumber implements Comparable<DeckNumber> {
	private int _value;
	
	@Override
	public String toString() {
		return String.format("deck %d", _value);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof DeckNumber) {
			DeckNumber o = (DeckNumber) obj;
			return _value == o._value;
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return Integer.valueOf(_value).hashCode();
	}

	@Override
	public int compareTo(DeckNumber other) {
		return Integer.compare(Integer.valueOf(_value), Integer.valueOf(other._value));
	}
}
