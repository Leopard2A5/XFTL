package de.xftl.fixture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.xftl.model.ships.BasicDeck;
import de.xftl.model.ships.BasicDoor;
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
import de.xftl.spec.model.ships.TileUnit;

public class ShipBuilder {
	
	private List<BasicDeck> _decks = new ArrayList<>();
	private BasicDeck _currentDeck;
	
	private Map<Deck, TileUnitMatrix<BasicTile>> _matrices = new HashMap<>();
	
	
	public BasicShip buildShip() {
		BasicShip ship = new BasicShip();
		
		for (BasicDeck deck : _decks)
			ship.addDeck(deck);
		
		return ship;
	}
	
	public ShipBuilder addDeck() {
		_currentDeck = new BasicDeck(new DeckNumber(_decks.size() + 1));
		_decks.add(_currentDeck);
		return this;
	}
	
	public ShipBuilder addRoom(int width, int height, int x, int y) {
		if (_matrices.containsKey(_currentDeck))
			throw new RuntimeException("You should first create all the rooms before adding doors or lifts!");
		
		_currentDeck.addRoom(new BasicRoom(width, height, x, y));
		
		return this;
	}
	
	public ShipBuilder addDoor(Point<Integer> src, Point<Integer> dest) {
		if (!_matrices.containsKey(_currentDeck))
			_matrices.put(_currentDeck, createDeckMatrix(_currentDeck));
		
		TileUnitMatrix<BasicTile> matrix = _matrices.get(_currentDeck);
		BasicTile srcTile = matrix.get(src.getX(), src.getY());
		BasicTile destTile = matrix.get(dest.getX(), dest.getY());
		
		if (srcTile == null)
			throw new RuntimeException(String.format("There is no tile at %s", src));

		Direction dir = Direction.getDirection(src, dest);
		
		List<Room> rooms = new ArrayList<>(2);
		rooms.add(srcTile.getRoom());
		
		if (destTile != null) {
			if (!adjacent(srcTile, destTile))
				throw new RuntimeException(String.format("Cannot add door because %s and %s are not adjacent!", src, dest));
			rooms.add(destTile.getRoom());
		}
		
		BasicDoor door = new BasicDoor(rooms);
		srcTile.addNeighbor(dir, door);
		if (destTile != null)
			destTile.addNeighbor(dir.getOpposite(), door);
		
		return this;
	}
	
	private boolean adjacent(Tile tile1, Tile tile2) {
		Point<TileUnit> pos1 = tile1.getLeftUpperCornerPos();
		Point<TileUnit> pos2 = tile2.getLeftUpperCornerPos();
		
		if (pos1.getX().equals(pos2.getX())) {
			return Math.abs(pos1.getY().getValue() - pos2.getY().getValue()) == 1;
		}
		else if (pos1.getY().equals(pos2.getY())) {
			return Math.abs(pos1.getX().getValue() - pos2.getX().getValue()) == 1;
		}
		
		return false;
	}

	private TileUnitMatrix<BasicTile> createDeckMatrix(BasicDeck deck) {
		Set<Point<TileUnit>> usedCoords = new HashSet<>();
		List<BasicTile> tiles = new ArrayList<>();
		
		for (Room room : deck.getRooms()) {
			for (Tile tile : room.getTiles()) {
				Point<TileUnit> pos = tile.getLeftUpperCornerPos();
				if (usedCoords.contains(pos))
					throw new RuntimeException(String.format("Position %s is used more than once on deck %s", pos, deck));
				usedCoords.add(pos);
				tiles.add((BasicTile) tile);
			}
		}
		
		return new TileUnitMatrix<>(tiles);
	}
}
