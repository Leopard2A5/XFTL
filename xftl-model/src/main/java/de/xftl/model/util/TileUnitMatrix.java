package de.xftl.model.util;

import java.util.Collection;
import java.util.Iterator;

import de.xftl.spec.model.ships.Positioned;

public class TileUnitMatrix<T extends Positioned<Integer>> implements Iterable<T> {
    
    private Collection<T> _items;
    private Object[][] _matrix;
    private int _maxX;
    private int _maxY;

    public TileUnitMatrix(final Collection<T> items) {
        super();
        
        _items = items;
        determineMaxDimensions();
        _matrix = new Object[_maxY + 1][_maxX + 1];
        insertItems();
    }

    private void determineMaxDimensions() {
        for (T t : _items) {
            _maxX = Math.max(_maxX, t.getLeftUpperCornerPos().getX());
            _maxY = Math.max(_maxY, t.getLeftUpperCornerPos().getY());
        }
    }

    private void insertItems() {
        for (T t : _items) {
        	final int x = t.getLeftUpperCornerPos().getX();
        	final int y = t.getLeftUpperCornerPos().getY();
            
            _matrix[y][x] = t;
        }
    }
    
    public Object[][] getMatrix() {
        return _matrix;
    }
    
    public TileUnitMatrixIterator<T> matrixIterator() {
        return new TileUnitMatrixIterator<T>(this);
    }

    @Override
    public Iterator<T> iterator() {
        return _items.iterator();
    }

    public int getMaxX() {
        return _maxX;
    }

    public int getMaxY() {
        return _maxY;
    }
    
    @SuppressWarnings("unchecked")
	public T get(int x, int y) {
    	if (x < 0 || x > _maxX)
    		return null;
    	if (y < 0 || y > _maxY)
    		return null;
    	
    	return (T) _matrix[y][x];
    }
}
