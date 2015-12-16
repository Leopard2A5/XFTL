package de.xftl.model.ships;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import de.xftl.spec.model.hardpoints.Hardpoint;
import de.xftl.spec.model.ships.Deck;
import de.xftl.spec.model.ships.Room;
import de.xftl.spec.model.ships.RoomConnector;
import de.xftl.spec.model.ships.Ship;
import de.xftl.spec.model.systems.ShipSystem;

public class BasicDeck implements Deck {
    private Ship _ship;
	private int _deckNumber;
	private List<Room> _rooms = new ArrayList<>();
	private List<Hardpoint> _hardpoints = new ArrayList<>();
	private Deck _deckAbove;
	private Deck _deckBelow;
	
	public BasicDeck(final Ship ship, final int deckNumber) {
		super();
		
		_ship = ship;
		_deckNumber = deckNumber;
	}
	
	@Override
	public void update(final float elapsedTime) {
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
	public int getDeckNumber() {
		return _deckNumber;
	}
	
	@Override
	public boolean equals(final Object obj) {
		if (obj == null) return false;
		if (this == obj) return true;
		if (!getClass().equals(obj)) return false;
		
		final Deck o = (Deck) obj;
		return _deckNumber == o.getDeckNumber();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getClass(), _deckNumber);
	}

	@Override
	public String toString() {
		return "Deck " + _deckNumber;
	}
	
	@Override
	public void addRoom(final Room room) {
	    room.setDeck(this);
		_rooms.add(room);
		_ship.onRoomAdded(room);
	}

    @Override
    public void onSystemAdded(final ShipSystem system) {
        _ship.addSystem(system);
    }

    @Override
    public void onRoomConnectorAdded(final RoomConnector roomConnector) {
        _ship.onRoomConnectorAdded(roomConnector);
    }

    public void setShip(Ship ship) {
		_ship = ship;
	}
}
