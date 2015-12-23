package de.xftl.model.util;

import java.util.Iterator;

import de.xftl.spec.model.Direction;
import de.xftl.spec.model.ships.Positioned;

public class TileUnitMatrixIterator<T extends Positioned<Integer>> implements Iterator<T> {
    private TileUnitMatrix<T> _matrix;
    private Iterator<T> _itemIt;
    private T _currItem;
    private int _currX;
    private int _currY;
    
    public TileUnitMatrixIterator(final TileUnitMatrix<T> matrix) {
        super();
        
        _matrix = matrix;
        _itemIt = _matrix.iterator();
    }
    
    @Override
    public boolean hasNext() {
        return _itemIt.hasNext();
    }

    @Override
    public T next() {
        _currItem = _itemIt.next();
        _currX = _currItem.getLeftUpperCornerPos().getX();
        _currY = _currItem.getLeftUpperCornerPos().getY();
        
        return _currItem;
    }

    @Override
    public void remove() {
        throw new RuntimeException("Not supported");
    }
    
    public T getNeighbor(final Direction dir) {
        switch (dir) {
            case NORTH:
                return getNorthNeighbor();
            case EAST:
                return getEastNeighbor();
            case SOUTH:
                return getSouthNeighbor();
            case WEST:
                return getWestNeighbor();
            default:
                throw new IllegalArgumentException("Unknown enum value " + dir);
        }
    }
    
    @SuppressWarnings("unchecked")
    public T getWestNeighbor() {
        if (_currX > 0) {
            return (T) _matrix.getMatrix()[_currY][_currX - 1];
        }
        
        return null;
    }
    
    @SuppressWarnings("unchecked")
    public T getEastNeighbor() {
        if (_currX < _matrix.getMaxX()) {
            return (T) _matrix.getMatrix()[_currY][_currX + 1];
        }
        
        return null;
    }
    
    @SuppressWarnings("unchecked")
    public T getNorthNeighbor() {
        if (_currY > 0) {
            return (T) _matrix.getMatrix()[_currY - 1][_currX];
        }
        
        return null;
    }
    
    @SuppressWarnings("unchecked")
    public T getSouthNeighbor() {
        if (_currY < _matrix.getMaxY()) {
            return (T) _matrix.getMatrix()[_currY + 1][_currX];
        }
        
        return null;
    }
}
