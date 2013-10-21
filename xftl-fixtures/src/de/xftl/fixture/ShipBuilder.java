package de.xftl.fixture;

import java.util.ArrayList;
import java.util.List;

import de.xftl.model.ships.BasicDeck;
import de.xftl.model.ships.BasicRoom;
import de.xftl.model.ships.BasicShip;
import de.xftl.spec.model.ships.DeckNumber;

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
		_currentDeck.addRoom(new BasicRoom(width, height, x, y));
		
		return this;
	}
}
