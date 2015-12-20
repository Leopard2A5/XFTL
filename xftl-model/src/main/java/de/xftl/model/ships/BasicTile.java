package de.xftl.model.ships;

import static de.xftl.spec.model.Direction.EAST;
import static de.xftl.spec.model.Direction.NORTH;
import static de.xftl.spec.model.Direction.SOUTH;
import static de.xftl.spec.model.Direction.WEST;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import de.xftl.spec.model.Direction;
import de.xftl.spec.model.Point;
import de.xftl.spec.model.crew.CrewMember;
import de.xftl.spec.model.ships.Room;
import de.xftl.spec.model.ships.RoomConnector;
import de.xftl.spec.model.ships.Tile;
import de.xftl.spec.model.ships.TileOrRoomConnector;

public class BasicTile implements Tile {

	private static final float HULL_BREACH_AIR_VENT_FACTOR = 0.1f;
	private static final float FIRE_OXYGEN_CONSUMPTION = 0.2f;
	private static final float FIRE_SPREAD_PROBABILITY = 0.2f;
	private static final float FIRE_SPREAD_OXYGEN_REQUIREMENT = 0.5f;
	private static final float FIRE_DIE_THRESHOLD = 0.15f;
	
	private static final Random RANDOM = new Random(1);
	
	private Room _room;
	private Point<Integer> _leftUpperCornerPos;
	private CrewMember _crewMember;
	private CrewMember _enemyCrewMember;
	
	private final Map<Direction, TileOrRoomConnector> _neighbors = new EnumMap<>(Direction.class);
	private final List<Tile> _neightboringTiles = new ArrayList<>(4);
	
	private float _hullBreachLevel;
	private boolean _onFire;
	
	public BasicTile(final Room room, final Point<Integer> leftUpperCornerPos) {
		super();
		
		_room = room;
		_leftUpperCornerPos = leftUpperCornerPos;
	}
	
	@Override
	public void update(final float elapsedTime) {
		updateHullBreach(elapsedTime);
		updateFire(elapsedTime);
	}

	@Override
	public Point<Integer> getLeftUpperCornerPos() {
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

	public void addNeighbor(final Direction dir, final TileOrRoomConnector neighbor) {
		_neighbors.put(dir, neighbor);
		
		if (neighbor instanceof RoomConnector)
		    _room.addRoomConnector((RoomConnector) neighbor);
		else if (neighbor instanceof Tile)
		    _neightboringTiles.add((Tile) neighbor);
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
    	return _neighbors.get(NORTH);
    }

    @Override
    public TileOrRoomConnector getEastNeighbor() {
    	return _neighbors.get(EAST);
    }

    @Override
    public TileOrRoomConnector getSouthNeighbor() {
    	return _neighbors.get(SOUTH);
    }

    @Override
    public TileOrRoomConnector getWestNeighbor() {
    	return _neighbors.get(WEST);
    }

	@Override
	public float getHullBreachLevel() {
		return _hullBreachLevel;
	}

	@Override
	public boolean hasHullBreach() {
		return _hullBreachLevel > NO_HULL_BREACH;
	}

	@Override
	public void createHullBreach(final float initialBreachValue) {
		_hullBreachLevel = Math.min(MAX_HULL_BREACH, _hullBreachLevel + initialBreachValue);
		_onFire = false;
	}

	@Override
	public void repairHullBreach(final float repairValue) {
		_hullBreachLevel = Math.max(NO_HULL_BREACH, _hullBreachLevel - repairValue);
	}
	
	private void updateHullBreach(final float elapsedTime) {
        _room.consumeOxygen((_hullBreachLevel * HULL_BREACH_AIR_VENT_FACTOR * elapsedTime) / _room.getTiles().size());
    }

	@Override
	public boolean isOnFire() {
		return _onFire;
	}

	@Override
	public void ignite() {
		if (!hasHullBreach()) {
			_onFire = true;
		}
	}
	
	private void updateFire(final float elapsedTime) {
	    if (!isOnFire())
	        return;
	    
	    if (_room.getOxygenLevel() < FIRE_DIE_THRESHOLD) {
            _onFire = false;
            return;
        }
	    
	    float consumedOxygen = FIRE_OXYGEN_CONSUMPTION * elapsedTime;
	    _room.consumeOxygen(consumedOxygen / _room.getTiles().size());
	    
	    if (_room.getOxygenLevel() >= FIRE_SPREAD_OXYGEN_REQUIREMENT) {
	        float spreadProbability = FIRE_SPREAD_PROBABILITY * elapsedTime;
	        if (spreadProbability >= RANDOM.nextFloat()) {
	            Tile tile = getNeighboringTileNotOnFire();
	            if (tile != null) {
	                tile.ignite();
	            }
	        }
	    }
	}
	
	private Tile getNeighboringTileNotOnFire() {
	    for (Tile tile : _neightboringTiles)
	        if (!tile.isOnFire())
	            return tile;
	    return null;
	}
}
