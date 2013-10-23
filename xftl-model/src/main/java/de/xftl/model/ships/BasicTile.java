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

	private static final float HULL_BREACH_AIR_VENT_FACTOR = 0.1f;
	private static final float FIRE_AIR_CONSUMPTION_FACTOR = 0.1f;
	
	private float _elapsedTime;
	
	private Room _room;
	private Point<TileUnit> _leftUpperCornerPos;
	private CrewMember _crewMember;
	private CrewMember _enemyCrewMember;
	
	private TileOrRoomConnector _north;
	private TileOrRoomConnector _east;
	private TileOrRoomConnector _south;
	private TileOrRoomConnector _west;
	
	private float _hullBreachLevel;
	private float _fireLevel;
	
	public BasicTile(Room room, Point<TileUnit> leftUpperCornerPos) {
		super();
		
		_room = room;
		_leftUpperCornerPos = leftUpperCornerPos;
	}
	
	@Override
	public void update(float elapsedTime) {
		_elapsedTime += elapsedTime;
		
		if (_elapsedTime >= 1) {
			updateHullBreach();
			updateFire();
			
			_elapsedTime = 0;
		}
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

	@Override
	public float getHullBreachLevel() {
		return _hullBreachLevel;
	}

	@Override
	public boolean hasHullBreach() {
		return _hullBreachLevel > MIN_HULL_BREACH;
	}

	@Override
	public void createHullBreach(float initialBreachValue) {
		_hullBreachLevel = Math.min(MAX_HULL_BREACH, initialBreachValue);
		_fireLevel = MIN_FIRE;
	}

	@Override
	public void repairHullBreach(float repairValue) {
		_hullBreachLevel = Math.min(MIN_HULL_BREACH, _hullBreachLevel - repairValue);
	}

	@Override
	public float getFireLevel() {
		return _fireLevel;
	}

	@Override
	public boolean isOnFire() {
		return _fireLevel > MIN_FIRE;
	}

	@Override
	public void ignite(float initialFireLevel) {
		if (!hasHullBreach()) {
			_fireLevel = Math.min(MAX_FIRE, initialFireLevel);
		}
	}

	@Override
	public void extinguishFire(float extinguishingLevel) {
		_fireLevel = Math.min(MIN_FIRE, _fireLevel - extinguishingLevel); 
	}
	
	private void updateHullBreach() {
		_room.consumeOxygen(_hullBreachLevel * HULL_BREACH_AIR_VENT_FACTOR);
	}
	
	private void updateFire() {
		_room.consumeOxygen(_fireLevel * FIRE_AIR_CONSUMPTION_FACTOR);
	}
}
