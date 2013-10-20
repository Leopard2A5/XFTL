package de.xftl.fixture;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.xftl.model.ships.BasicDeck;
import de.xftl.model.ships.BasicRoom;
import de.xftl.model.ships.BasicShip;
import de.xftl.model.ships.BasicTile;
import de.xftl.model.util.TileUnitMatrix;
import de.xftl.model.util.TileUnitMatrixIterator;
import de.xftl.spec.model.Direction;
import de.xftl.spec.model.Point;
import de.xftl.spec.model.ships.DeckNumber;
import de.xftl.spec.model.ships.Tile;
import de.xftl.spec.model.ships.TileUnit;

public class ShipBuilder {
	
	private List<BasicDeck> _decks = new ArrayList<>();
	private BasicDeck _currentDeck;
	
//	private Map<Deck, TileUnitMatrix<BasicTile>> _matrices = new HashMap<>();
	
	
	public BasicShip buildShip() {
		BasicShip ship = new BasicShip();
		
		for (BasicDeck deck : _decks)
			ship.addDeck(deck);
		
		return ship;
	}
	
	public ShipBuilder addDeck() {
		if (_currentDeck != null) {
			// create matrix
		}
		
		_currentDeck = new BasicDeck(new DeckNumber(_decks.size() + 1));
		_decks.add(_currentDeck);
		return this;
	}
	
	public ShipBuilder addRoom(int width, int height, int x, int y) {
		List<BasicTile> tiles = buildTiles(width, height, x, y);
	    connectTiles(tiles);
		BasicRoom room = new BasicRoom(new ArrayList<Tile>(tiles));
		
		_currentDeck.addRoom(room);
		
		return this;
	}
	
	private static List<BasicTile> buildTiles(int width, int height, int pX, int pY) {
		List<BasicTile> tiles = new ArrayList<>(width * height);
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				TileUnit tux = new TileUnit(x + pX);
				TileUnit tuy = new TileUnit(y + pY);
				BasicTile tile = new BasicTile(new Point<TileUnit>(tux, tuy));
				tiles.add(tile);
			}
		}
		
		return tiles;
	}
	
	private static void connectTiles(Collection<BasicTile> tiles) {
	    TileUnitMatrix<BasicTile> matrix = new TileUnitMatrix<>(tiles);
        
        for (TileUnitMatrixIterator<BasicTile> it = matrix.matrixIterator(); it.hasNext();) {
            BasicTile t = it.next();
            
            for (Direction dir : Direction.values()) {
                BasicTile other = it.getNeighbor(dir);
                if (other != null) {
                    t.addNeighbor(dir, other);
                    other.addNeighbor(dir.getOpposite(), t);
                }
            }
        }
	}
}
