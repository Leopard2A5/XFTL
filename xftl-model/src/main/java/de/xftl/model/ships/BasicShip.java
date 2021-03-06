package de.xftl.model.ships;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.xftl.model.systems.BasicDoorSystem;
import de.xftl.model.systems.BasicEnergyManager;
import de.xftl.model.systems.BasicLifeSupport;
import de.xftl.spec.model.EnergyConsumer;
import de.xftl.spec.model.crew.CrewMember;
import de.xftl.spec.model.ships.Deck;
import de.xftl.spec.model.ships.Hitpoints;
import de.xftl.spec.model.ships.Room;
import de.xftl.spec.model.ships.RoomConnector;
import de.xftl.spec.model.ships.Ship;
import de.xftl.spec.model.systems.DoorSystem;
import de.xftl.spec.model.systems.EnergyConsumingSystem;
import de.xftl.spec.model.systems.EnergyManager;
import de.xftl.spec.model.systems.EnergyProducingSystem;
import de.xftl.spec.model.systems.LifeSupport;
import de.xftl.spec.model.systems.ShipSystem;

public class BasicShip implements Ship {

	private final List<Deck> _decks = new ArrayList<>();
	private Hitpoints _hitpoints;
	private final List<ShipSystem> _systems = new ArrayList<>();
	private DoorSystem _doorSystem;
	private EnergyManager _energyManager = new BasicEnergyManager();
	private LifeSupport _lifeSupport;
	private final List<CrewMember> _crew = new ArrayList<>();
	
	public BasicShip() {
	    super();
	    
	    addSystem(new BasicDoorSystem());
	    addSystem(new BasicLifeSupport());
	}
	
	@Override
	public void update(final float elapsedTime) {
	    for (ShipSystem system : _systems)
	        system.update(elapsedTime);
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
	
	public void addDeck(final BasicDeck deck) {
		_decks.add(deck);
		deck.setShip(this);
	}

    @Override
    public List<ShipSystem> getAllSystems() {
        return Collections.unmodifiableList(_systems);
    }

    @Override
    public void addSystem(final ShipSystem system) {
        _systems.add(system);
        
        if (system instanceof EnergyProducingSystem)
            _energyManager.addEnergyProducer((EnergyProducingSystem) system);
        if (system instanceof EnergyConsumer)
            _energyManager.addEnergyConsumer((EnergyConsumingSystem) system);
        
        if (system instanceof DoorSystem)
            _doorSystem = (DoorSystem) system;
        if (system instanceof LifeSupport)
            _lifeSupport = (LifeSupport) system;
    }

    @Override
    public DoorSystem getDoorSystem() {
        return _doorSystem;
    }

    @Override
    public void onRoomConnectorAdded(final RoomConnector roomConnector) {
        _doorSystem.addRoomConnector(roomConnector);
    }
    
    @Override
    public void onRoomAdded(final Room room) {
        _lifeSupport.addRoom(room);
    }

    @Override
    public EnergyManager getEnergyManager() {
        return _energyManager;
    }

    @Override
    public LifeSupport getLifeSupport() {
        return _lifeSupport;
    }
    
    @Override
    public List<CrewMember> getCrew() {
    	return Collections.unmodifiableList(_crew);
    }

	@Override
	public void addCrewMember(final CrewMember crewMember) {
		_crew.add(crewMember);
	}

	@Override
	public void removeCrewMember(final CrewMember crewMember) {
		_crew.remove(crewMember);
	}

}
