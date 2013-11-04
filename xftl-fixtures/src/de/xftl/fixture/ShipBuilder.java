package de.xftl.fixture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.xftl.model.ships.BasicDeck;
import de.xftl.model.ships.BasicDoor;
import de.xftl.model.ships.BasicLift;
import de.xftl.model.ships.BasicRoom;
import de.xftl.model.ships.BasicShip;
import de.xftl.model.ships.BasicTile;
import de.xftl.model.util.TileUnitMatrix;
import de.xftl.spec.model.Direction;
import de.xftl.spec.model.Point;
import de.xftl.spec.model.ships.Deck;
import de.xftl.spec.model.ships.DeckNumber;
import de.xftl.spec.model.ships.Room;
import de.xftl.spec.model.ships.Tile;
import de.xftl.spec.model.systems.DoorSystem;
import de.xftl.spec.model.systems.LifeSupport;
import de.xftl.spec.model.systems.ShipSystem;

public class ShipBuilder {
	
    private BasicShip _ship = new BasicShip();
    private DoorSystem _doorSystem = _ship.getDoorSystem();
    private boolean _doorSystemAdded;
    private LifeSupport _lifeSupport = _ship.getLifeSupport();
    private boolean _lifeSupportAdded;
    
	private List<BasicDeck> _decks = new ArrayList<>();
	private BasicDeck _currentDeck;
	private BasicRoom _currentRoom;
	
	private Map<Deck, TileUnitMatrix<BasicTile>> _matrices = new HashMap<>();
	private Map<String, List<DeckLiftDescription>> _deckLiftDescriptions = new HashMap<>();
	
	
	public BasicShip buildShip() {
		for (BasicDeck deck : _decks)
			_ship.addDeck(deck);
		
		createLifts();
		
		if (!_doorSystemAdded)
		    throw new RuntimeException("You need to position the door system!");
		if (!_lifeSupportAdded)
		    throw new RuntimeException("You need to position the life support system!");
		
		return _ship;
	}
	
	private void createLifts() {
		Map<Deck, Set<Point<Integer>>> liftPositions = new HashMap<>();
		
		for (String liftId : _deckLiftDescriptions.keySet()) {
			for (DeckLiftDescription dld : _deckLiftDescriptions.get(liftId)) {
				Set<Point<Integer>> usedPositions = liftPositions.get(dld._deck);
				if (usedPositions == null) {
					usedPositions = new HashSet<>();
					liftPositions.put(dld._deck, usedPositions);
				}
				else {
					if (usedPositions.contains(dld._liftPos))
						throw new RuntimeException(String.format("Position %s is occupied by two lifts!", dld._liftPos));
				}
				usedPositions.add(dld._liftPos);
			}
		}
		
		for (String liftId : _deckLiftDescriptions.keySet()) {
			BasicLift lift = new BasicLift();
			
			for (DeckLiftDescription dld : _deckLiftDescriptions.get(liftId)) {
				dld._tile.addNeighbor(dld._tileToLiftDirection, lift);
			}
		}
	}
	
	public ShipBuilder addDeck() {
		_currentDeck = new BasicDeck(_ship, new DeckNumber(_decks.size() + 1));
		_decks.add(_currentDeck);
		_currentRoom = null;
		return this;
	}
	
	public ShipBuilder addRoom(int width, int height, int x, int y) {
		if (_matrices.containsKey(_currentDeck))
			throw new RuntimeException("You should first create all the rooms before adding doors or lifts!");
		
		_matrices.remove(_currentDeck);
		
		_currentRoom = new BasicRoom(width, height, x, y);
		_currentDeck.addRoom(_currentRoom);
		
		return this;
	}
	
	public ShipBuilder addSystem(ShipSystem system) {
	    if (_currentRoom == null)
	        throw new RuntimeException("You have to add a room before adding a system!");
	    
	    _currentRoom.setSystem(system);
	    
	    return this;
	}
	
	public ShipBuilder addDoorSystem() {
	    if (_doorSystemAdded)
	        throw new RuntimeException("Already added the door system!");
	    
	    _currentRoom.setSystem(_doorSystem);
	    _doorSystemAdded = true;
	    
	    return this;
	}
	
	public ShipBuilder addLifeSupport() {
	    if (_lifeSupportAdded)
	        throw new RuntimeException("Already added the life support system!");
	    
	    _currentRoom.setSystem(_lifeSupport);
	    _lifeSupportAdded = true;
	    
	    return this;
	}
	
	public ShipBuilder addDoor(Point<Integer> src, Point<Integer> dest) {
		ensureDeckMatrixPresent();
		
		TileUnitMatrix<BasicTile> matrix = _matrices.get(_currentDeck);
		BasicTile srcTile = matrix.get(src.getX(), src.getY());
		BasicTile destTile = matrix.get(dest.getX(), dest.getY());
		
		if (srcTile == null)
			throw new RuntimeException(String.format("There is no tile at %s", src));

		Direction dir = Direction.getDirection(src, dest);
		
		if (destTile != null && !adjacent(srcTile, destTile))
			throw new RuntimeException(String.format("Cannot add door because %s and %s are not adjacent!", src, dest));
		
		BasicDoor door = new BasicDoor();
		
		srcTile.addNeighbor(dir, door);
		if (destTile != null)
			destTile.addNeighbor(dir.getOpposite(), door);
		
		return this;
	}

	public ShipBuilder addAirlock(Point<Integer> src, Point<Integer> dest) {
		return addDoor(src, dest);
	}
	
	public ShipBuilder addLift(Point<Integer> tilePos, Point<Integer> liftPos, String liftId) {
		ensureDeckMatrixPresent();
		TileUnitMatrix<BasicTile> matrix = _matrices.get(_currentDeck);
		
		BasicTile roomTile = matrix.get(tilePos.getX(), tilePos.getY());
		BasicTile liftTile = matrix.get(liftPos.getX(), liftPos.getY());
		
		if (roomTile == null)
			throw new RuntimeException(String.format("There is no tile to connect to the lift at %s", tilePos));
		if (liftTile != null)
			throw new RuntimeException(String.format("The position %s is occupied by a room tile", liftPos));
		
		List<DeckLiftDescription> liftDesc = _deckLiftDescriptions.get(liftId);
		if (liftDesc == null) {
			liftDesc = new ArrayList<DeckLiftDescription>();
			_deckLiftDescriptions.put(liftId, liftDesc);
		}
		
		liftDesc.add(new DeckLiftDescription(_currentDeck, roomTile, Direction.getDirection(tilePos, liftPos), liftPos));
		
		return this;
	}
	
	private boolean adjacent(Tile tile1, Tile tile2) {
		Point<Integer> pos1 = tile1.getLeftUpperCornerPos();
		Point<Integer> pos2 = tile2.getLeftUpperCornerPos();
		
		if (pos1.getX().equals(pos2.getX())) {
			return Math.abs(pos1.getY() - pos2.getY()) == 1;
		}
		else if (pos1.getY().equals(pos2.getY())) {
			return Math.abs(pos1.getX() - pos2.getX()) == 1;
		}
		
		return false;
	}
	
	private void ensureDeckMatrixPresent() {
		if (!_matrices.containsKey(_currentDeck))
			_matrices.put(_currentDeck, createDeckMatrix(_currentDeck));
	}

	private TileUnitMatrix<BasicTile> createDeckMatrix(BasicDeck deck) {
		Set<Point<Integer>> usedCoords = new HashSet<>();
		List<BasicTile> tiles = new ArrayList<>();
		
		for (Room room : deck.getRooms()) {
			for (Tile tile : room.getTiles()) {
				Point<Integer> pos = tile.getLeftUpperCornerPos();
				if (usedCoords.contains(pos))
					throw new RuntimeException(String.format("Position %s is used more than once on deck %s", pos, deck));
				usedCoords.add(pos);
				tiles.add((BasicTile) tile);
			}
		}
		
		return new TileUnitMatrix<>(tiles);
	}
	
	private class DeckLiftDescription {
		public BasicDeck _deck;
		public BasicTile _tile;
		public Direction _tileToLiftDirection;
		public Point<Integer> _liftPos;
		
		public DeckLiftDescription(BasicDeck deck, BasicTile tile, Direction tileToLiftDirection, Point<Integer> liftPos) {
			super();
			
			_deck = deck;
			_tile = tile;
			_tileToLiftDirection = tileToLiftDirection;
			_liftPos = liftPos;
		}
	}
}
