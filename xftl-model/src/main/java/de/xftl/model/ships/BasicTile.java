package de.xftl.model.ships;

import de.xftl.spec.model.Direction;
import de.xftl.spec.model.Point;
import de.xftl.spec.model.crew.CrewMember;
import de.xftl.spec.model.ships.Positioned;
import de.xftl.spec.model.ships.Room;
import de.xftl.spec.model.ships.RoomConnector;
import de.xftl.spec.model.ships.Tile;
import de.xftl.spec.model.ships.TileOrRoomConnector;
import de.xftl.spec.model.ships.TileUnit;

public class BasicTile implements Tile {

	private Room _room;
	private Point<TileUnit> _leftUpperCornerPos;
	private CrewMember _crewMember;
	private CrewMember _enemyCrewMember;
	
	private TileOrRoomConnector _north;
	private TileOrRoomConnector _east;
	private TileOrRoomConnector _south;
	private TileOrRoomConnector _west;
	
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
	public int compareTo(Positioned<TileUnit> o) {
	    return _leftUpperCornerPos.compareTo(o.getLeftUpperCornerPos());
	}
	
	public void addNeighbor(Direction dir, TileOrRoomConnector neighbor) {
		switch (dir) {
		    case NORTH:
		        _north = neighbor;
		        break;
		    case EAST:
		        _east = neighbor;
		        break;
		    case SOUTH:
		        _south = neighbor;
		        break;
		    case WEST:
		        _west = neighbor;
		        break;
		    default:
		        throw new IllegalArgumentException("Unknown enum value " + dir);
		}
		
		if (neighbor instanceof RoomConnector)
		    _room.addRoomConnector((RoomConnector) neighbor);
	}
	
	@Override
	public String toString() {
	    return String.format("BasicTile %s", _leftUpperCornerPos);
	}

	public Room getRoom() {
		return _room;
	}

    @Override
    public TileOrRoomConnector getNorthNeighbor() {
        return _north;
    }

    @Override
    public TileOrRoomConnector getEastNeighbor() {
        return _east;
    }

    @Override
    public TileOrRoomConnector getSouthNeighbor() {
        return _south;
    }

    @Override
    public TileOrRoomConnector getWestNeighbor() {
        return _west;
    }
}
