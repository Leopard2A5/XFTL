package de.xftl.model.game;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import de.xftl.model.crew.BasicMovement;
import de.xftl.spec.model.Direction;
import de.xftl.spec.model.Point;
import de.xftl.spec.model.crew.Movement;
import de.xftl.spec.model.ships.Lift;
import de.xftl.spec.model.ships.Room;
import de.xftl.spec.model.ships.RoomConnector;
import de.xftl.spec.model.ships.Tile;

public class WaypointPlanner {

	public Movement planMovement(final Tile startTile,
								 final Tile targetTile) {
		final BasicMovement ret = new BasicMovement(targetTile);

		final Room startRoom = startTile.getRoom();
		final Room targetRoom = targetTile.getRoom();

		final Queue<Room> pathByRooms = getPathByRooms(startRoom, targetRoom);
		final List<Point<Float>> waypoints = getWaypoints(pathByRooms, startTile, targetTile);
		
		for (final Point<Float> wp : waypoints) {
			ret.addWaypoint(wp);
		}

		return ret;
	}

	private Queue<Room> getPathByRooms(final Room start,
									   final Room target) {
		final LinkedHashSet<Room> path = new LinkedHashSet<>();
		final Queue<Room> queue = new LinkedList<>();

		path.add(start);
		queue.add(start);

		while (!queue.isEmpty()) {
			final Room cur = queue.remove();
			if (target.equals(cur)) {
				path.add(target);
				break;
			}

			for (final Room neighbor : cur.getAdjacentRooms()) {
				if (!path.contains(neighbor)) {
					queue.add(neighbor);
					path.add(cur);
				}
			}
		}
		
		final LinkedList<Room> ret = new LinkedList<>(path);
		if (ret.isEmpty() || !target.equals(ret.getLast())) {
			throw new RuntimeException("No route to target!");
		}

		return ret;
	}

	private List<Point<Float>> getWaypoints(final Queue<Room> pathByRooms,
							                final Tile startTile,
							                final Tile targetTile) {
		final List<Point<Float>> waypoints = new ArrayList<>();
		
		if (pathByRooms.size() == 1) {
			waypoints.add(getPointForTile(targetTile));
		}
		else if (pathByRooms.size() > 1) {
			while (pathByRooms.size() > 1) {
				final Room curRoom = pathByRooms.remove();
				final Room nextRoom = pathByRooms.element();
				
				final RoomConnector door = getRoomConnectorForRoom(curRoom, nextRoom);
				addRoomConnectorWaypoints(waypoints, door, curRoom, nextRoom, startTile, targetTile);
			}
		}
		else {
			throw new RuntimeException("Cannot handle this case!");
		}
		
		return waypoints;
	}
	
	private RoomConnector getRoomConnectorForRoom(final Room startRoom,
												  final Room targetRoom) {
		for (final RoomConnector door : startRoom.getRoomConnectors()) {
			if (door.getConnectedRooms(startRoom).contains(targetRoom)) {
				return door;
			}
		}
		
		throw new RuntimeException("No roomConnector found!");
	}
	

	private void addRoomConnectorWaypoints(final List<Point<Float>> tilePath,
									       final RoomConnector connector,
									       final Room curRoom,
									       final Room nextRoom,
									       final Tile startTile,
									       final Tile targetTile) {
		final Pair<Tile, Direction> tile1 = getTileForRoomConnectorAndRoom(curRoom, connector);
		final Tile tile2 = getTileForRoomConnectorAndRoom(nextRoom, connector).getLeft();

		tilePath.add(getPointForTile(tile1.getLeft()));
		if (connector instanceof Lift) {
			tilePath.add(getPointForLift(tile1.getLeft(), tile1.getRight()));
		}
		tilePath.add(getPointForTile(tile2));
	}
	
	private Point<Float> getPointForLift(final Tile tile,
										 final Direction dir) {
		final float x;
		final float y;
		
		switch (dir) {
			case NORTH:
				x = tile.getLeftUpperCornerPos().getX() + 0.5f;
				y = tile.getLeftUpperCornerPos().getY() - 0.5f;
				break;
			case SOUTH:
				x = tile.getLeftUpperCornerPos().getX() + 0.5f;
				y = tile.getLeftUpperCornerPos().getY() + 0.5f;
				break;
			case EAST:
				x = tile.getLeftUpperCornerPos().getX() + 1.5f;
				y = tile.getLeftUpperCornerPos().getY() + 0.5f;
				break;
			case WEST:
				x = tile.getLeftUpperCornerPos().getX() - 0.5f;
				y = tile.getLeftUpperCornerPos().getY() + 0.5f;
				break;
			default:
				throw new RuntimeException("Unknown direction " + dir);
		}
		
		return new Point<>(x, y);
	}

	private Point<Float> getPointForTile(final Tile tile) {
		final Point<Integer> pos = tile.getLeftUpperCornerPos();
		return new Point<>(pos.getX() + 0.5f,
				           pos.getY() + 0.5f);
	}
	
	private Pair<Tile, Direction> getTileForRoomConnectorAndRoom(final Room room,
			                                                     final RoomConnector connector) {
		for (final Tile tile : room.getTiles()) {
			for (final Direction dir : Direction.values()) {
				if (connector.equals(tile.getNeighbor(dir))) {
					return new ImmutablePair<>(tile, dir);
				}
			}
		}
		
		throw new RuntimeException("No tile found for roomConnector");
	}

}
