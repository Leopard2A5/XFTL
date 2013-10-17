package de.xftl.spec.model.ships;

public class TileUnit implements Comparable<TileUnit> {
	
	private int _value;

	public TileUnit(int value) {
		super();
		
		this._value = value;
	}
	
	@Override
	public String toString() {
		return String.format("%dtu", Integer.valueOf(_value));
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TileUnit)
		{
			TileUnit o = (TileUnit) obj;
			return _value == o._value;
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return Integer.valueOf(_value).hashCode();
	}

	public int getValue() {
		return _value;
	}

	public void setValue(int value) {
		this._value = value;
	}

    @Override
    public int compareTo(TileUnit other) {
        return Integer.compare(_value, other._value);
    }
}
