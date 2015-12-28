package de.xftl.spec.model.crew;

import de.xftl.spec.model.Point;
import de.xftl.spec.model.ships.Tile;

public interface Movement {
	public Tile getTargetTile();
	public boolean isEmpty();
	public Point<Float> getCurrentWaypoint();
	public void waypointReached();
}
