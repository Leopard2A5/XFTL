package de.xftl.spec.model.ships;

import de.xftl.spec.model.Point;

public interface Positioned<T extends Comparable<T>> extends Comparable<Positioned<T>> {
	public Point<T> getLeftUpperCornerPos();
	
	default int compareTo(final Positioned<T> other) {
		return getLeftUpperCornerPos().compareTo(other.getLeftUpperCornerPos());
	}
}
