package de.xftl.model.ships;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.xftl.spec.model.ships.Deck;
import de.xftl.spec.model.ships.Hitpoints;
import de.xftl.spec.model.ships.Ship;

public class BasicShip implements Ship {

	private List<Deck> _decks = new ArrayList<>();
	private Hitpoints _hitpoints;
	
	@Override
	public void update(float elapsedTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Deck> getDecks() {
		return Collections.unmodifiableList(_decks);
	}

	@Override
	public Hitpoints getHitpoints() {
		return _hitpoints;
	}

}
