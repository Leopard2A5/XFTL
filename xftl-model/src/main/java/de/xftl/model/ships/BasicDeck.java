package de.xftl.model.ships;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.xftl.spec.model.hardpoints.Hardpoint;
import de.xftl.spec.model.ships.Deck;
import de.xftl.spec.model.ships.DeckNumber;
import de.xftl.spec.model.ships.Room;
import de.xftl.spec.model.ships.RoomConnector;
import de.xftl.spec.model.ships.Ship;
import de.xftl.spec.model.systems.ShipSystem;

public class BasicDeck implements Deck {
    private Ship _ship;
	private DeckNumber _deckNumber;
	private List<Room> _rooms = new ArrayList<>();
	private List<Hardpoint> _hardpoints = new ArrayList<>();
	private Deck _deckAbove;
	private Deck _deckBelow;
	
	public BasicDeck(Ship ship, DeckNumber deckNumber) {
		super();
		
		_ship = ship;
		_deckNumber = deckNumber;
	}
	
	@Override
	public void update(float elapsedTime) {
		for (Room room : _rooms)
		    room.update(elapsedTime);
		for (Hardpoint hardpoint : _hardpoints)
		    hardpoint.update(elapsedTime);
	}

	@Override
	public List<Room> getRooms() {
		return Collections.unmodifiableList(_rooms);
	}

	@Override
	public List<Hardpoint> getHardpoints() {
		return Collections.unmodifiableList(_hardpoints);
	}

	@Override
	public Deck getDeckAbove() {
		return _deckAbove;
	}

	@Override
	public Deck getDeckBelow() {
		return _deckBelow;
	}

	@Override
	public DeckNumber getDeckNumber() {
		return _deckNumber;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Deck) {
			Deck o = (Deck) obj;
			return _deckNumber.equals(o.getDeckNumber());
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return _deckNumber.hashCode(); 
	}

	@Override
	public String toString() {
		return _deckNumber.toString();
	}
	
	public void addRoom(Room room) {
		_rooms.add(room);
		_ship.onRoomAdded(room);
	}

    @Override
    public void onSystemAdded(ShipSystem system) {
        _ship.addSystem(system);
    }

    @Override
    public void onRoomConnectorAdded(RoomConnector roomConnector) {
        _ship.onRoomConnectorAdded(roomConnector);
    }

}
