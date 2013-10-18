package de.xftl.spec.model;

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
    public boolean equals(Object obj) {
        if (obj instanceof Point<?>) {
            Point<?> other = (Point<?>) obj;
            
            return _x.equals(other._x) && _y.equals(other._y);
        }
        
        return false;
    }
    
    @Override
    public int hashCode() {
        return String.format("%s/%s", _x.toString(), _y.toString()).hashCode();
    }
}
