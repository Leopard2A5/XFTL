/*
 * Copyright 2005-2012 IT Service Omikron.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
