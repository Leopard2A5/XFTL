package de.xftl.model.ships;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.xftl.spec.model.ships.Deck;
import de.xftl.spec.model.ships.Hitpoints;
import de.xftl.spec.model.ships.Ship;
import de.xftl.spec.model.systems.DoorSystem;
import de.xftl.spec.model.systems.EnergyConsumingSystem;
import de.xftl.spec.model.systems.EnergyProducingSystem;
import de.xftl.spec.model.systems.ShipSystem;

public class BasicShip implements Ship {

	private List<Deck> _decks = new ArrayList<>();
	private Hitpoints _hitpoints;
	private List<ShipSystem> _systems = new ArrayList<>();
	private List<EnergyProducingSystem> _energyProducingSystems = new ArrayList<>();
	private List<EnergyConsumingSystem> _energyConsumingSystems = new ArrayList<>();
	private DoorSystem _doorSystem;
	
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
    public List<ShipSystem> getAllSystems() {
        return Collections.unmodifiableList(_systems);
    }

    @Override
    public void addSystem(ShipSystem system) {
        _systems.add(system);
        
        if (system instanceof EnergyProducingSystem)
            _energyProducingSystems.add((EnergyProducingSystem) system);
        if (system instanceof EnergyConsumingSystem)
            _energyConsumingSystems.add((EnergyConsumingSystem) system);
        
        if (system instanceof DoorSystem)
            _doorSystem = (DoorSystem) system;
    }

    @Override
    public List<EnergyProducingSystem> getEnergyProducingSystems() {
        return _energyProducingSystems;
    }

    @Override
    public DoorSystem getDoorSystem() {
        return _doorSystem;
    }

}
