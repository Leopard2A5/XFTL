package de.xftl.spec.model;

import java.util.Objects;

public class Point<T extends Comparable<T>> implements Comparable<Point<T>> {
	private T _x;
	private T _y;
	
	public Point(T x, T y) {
		super();
		
		this._x = x;
		this._y = y;
	}
	
	public T getX() {
		return _x;
	}
	public void setX(T x) {
		this._x = x;
	}
	public T getY() {
		return _y;
	}
	public void setY(T y) {
		this._y = y;
	}

    @Override
    public int compareTo(Point<T> o) {
        int diffX = _x.compareTo(o._x);
        int diffY = _y.compareTo(o._y);
        
        // 0/0, 1/-1, -1/1
        if (diffX + diffY == 0)
            return 0;
        
        if (diffX == 0)
            return diffY;
        if (diffY == 0)
            return diffX;
        
        // both are equal
        return diffX;
    }
    
    @Override
    public boolean equals(final Object obj) {
    	if (obj == null) return false;
		if (this == obj) return true;
		if (!getClass().equals(obj)) return false;
    	
        final Point<?> other = (Point<?>) obj;
        return _x.equals(other._x) && _y.equals(other._y);
    }
    
    @Override
    public int hashCode() {
    	return Objects.hash(getClass(), _x, _y);
    }
    
    @Override
    public String toString() {
        return String.format("(%s/%s)", _x, _y);
    }
}
