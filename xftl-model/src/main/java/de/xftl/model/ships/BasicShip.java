package de.xftl.model.ships;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.xftl.spec.model.ships.Deck;
import de.xftl.spec.model.ships.Hitpoints;
import de.xftl.spec.model.ships.Ship;
import de.xftl.spec.model.systems.ShipSystem;

public class BasicShip implements Ship {

	private List<Deck> _decks = new ArrayList<>();
	private Hitpoints _hitpoints;
	private List<ShipSystem> _systems = new ArrayList<>();
	
	@Override
	public void update(float elapsedTime) {
		for (Deck deck : _decks)
		    deck.update(elapsedTime);
	}

	@Override
	public List<Deck> getDecks() {
		return Collections.unmodifiableList(_decks);
	}

	@Override
	public Hitpoints getHitpoints() {
		return _hitpoints;
	}
	
	public void addDeck(Deck deck) {
		_decks.add(deck);
	}

    @Override
    public List<ShipSystem> getSystems() {
        return Collections.unmodifiableList(_systems);
    }

    @Override
    public void addSystem(ShipSystem system) {
        _systems.add(system);
    }

}
