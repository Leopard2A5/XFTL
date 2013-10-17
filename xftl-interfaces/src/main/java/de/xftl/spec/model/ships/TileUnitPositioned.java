package de.xftl.spec.model.ships;

import de.xftl.spec.model.Point;

public interface TileUnitPositioned extends Comparable<TileUnitPositioned> {
	public Point<TileUnit> getLeftUpperCornerPos();
}
