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

import java.util.Collection;
import java.util.Iterator;

import de.xftl.spec.model.ships.Positioned;
import de.xftl.spec.model.ships.TileUnit;

public class TileUnitMatrix<T extends Positioned<TileUnit>> implements Iterable<T> {
    
    private Collection<T> _items;
    private Object[][] _matrix;
    private int _maxX;
    private int _maxY;

    public TileUnitMatrix(Collection<T> items) {
        super();
        
        _items = items;
        determineMaxDimensions();
        _matrix = new Object[_maxY + 1][_maxX + 1];
        insertItems();
    }

    private void determineMaxDimensions() {
        for (T t : _items) {
            _maxX = Math.max(_maxX, t.getLeftUpperCornerPos().getX().getValue());
            _maxY = Math.max(_maxY, t.getLeftUpperCornerPos().getY().getValue());
        }
    }

    private void insertItems() {
        for (T t : _items) {
            int x = t.getLeftUpperCornerPos().getX().getValue();
            int y = t.getLeftUpperCornerPos().getY().getValue();
            
            _matrix[y][x] = t;
        }
    }
    
    public Object[][] getMatrix() {
        return _matrix;
    }
    
    public TileUnitMatrixIterator<T> matrixIterator() {
        return new TileUnitMatrixIterator<T>(this);
    }
    
    @SuppressWarnings("unchecked")
	public T get(int x, int y) {
    	return (T) _matrix[y][x];
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
}
