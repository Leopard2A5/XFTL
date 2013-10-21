package de.xftl.model.ships;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import de.xftl.spec.model.Direction;
import de.xftl.spec.model.Point;
import de.xftl.spec.model.crew.CrewMember;
import de.xftl.spec.model.ships.Room;
import de.xftl.spec.model.ships.Tile;
import de.xftl.spec.model.ships.TileOrRoomConnector;
import de.xftl.spec.model.ships.TileUnit;
import de.xftl.spec.model.ships.Positioned;

public class BasicTile implements Tile {

	private Room _room;
	private Point<TileUnit> _leftUpperCornerPos;
	private CrewMember _crewMember;
	private CrewMember _enemyCrewMember;
	private Map<Direction, TileOrRoomConnector> _neighbors = new HashMap<>();
	
	public BasicTile(Room room, Point<TileUnit> leftUpperCornerPos) {
		super();
		
		_room = room;
		_leftUpperCornerPos = leftUpperCornerPos;
	}
	
	@Override
	public void update(float elapsedTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public Point<TileUnit> getLeftUpperCornerPos() {
		return _leftUpperCornerPos;
	}

	@Override
	public CrewMember getCrewMember() {
		return _crewMember;
	}

	@Override
	public CrewMember getEnemyCrewMember() {
		return _enemyCrewMember;
	}

	@Override
	public Map<Direction, TileOrRoomConnector> getNeighbors() {
		return Collections.unmodifiableMap(_neighbors);
	}
	
	@Override
	public int compareTo(Positioned<TileUnit> o) {
	    return _leftUpperCornerPos.compareTo(o.getLeftUpperCornerPos());
	}
	
	public void addNeighbor(Direction dir, TileOrRoomConnector neighbor) {
		_neighbors.put(dir, neighbor);
	}
	
	@Override
	public String toString() {
	    return String.format("BasicTile %s", _leftUpperCornerPos);
	}

	public Room getRoom() {
		return _room;
	}
}
