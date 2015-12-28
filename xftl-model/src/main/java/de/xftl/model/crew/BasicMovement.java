package de.xftl.model.crew;

import java.util.LinkedList;
import java.util.Queue;

import de.xftl.spec.model.Point;
import de.xftl.spec.model.crew.Movement;
import de.xftl.spec.model.ships.Tile;

public class BasicMovement implements Movement {

	private final Tile _targetTile;
	private final Queue<Point<Float>> _waypoints = new LinkedList<>();
	
	public BasicMovement(final Tile targetTile) {
		super();
		_targetTile = targetTile;
	}
	
	public void addWaypoint(final Point<Float> waypoint) {
		_waypoints.add(waypoint);
	}
	
	@Override
	public Tile getTargetTile() {
		return _targetTile;
	}

	@Override
	public boolean isEmpty() {
		return _waypoints.isEmpty();
	}

	@Override
	public Point<Float> getCurrentWaypoint() {
		return _waypoints.peek();
	}

	@Override
	public void waypointReached() {
		_waypoints.remove();
	}

}
