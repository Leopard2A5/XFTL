package de.xftl.model.ships;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.xftl.spec.model.hardpoints.Hardpoint;
import de.xftl.spec.model.ships.Deck;
import de.xftl.spec.model.ships.DeckNumber;
import de.xftl.spec.model.ships.Room;

public class BasicDeck implements Deck {
	private DeckNumber _deckNumber;
	private List<Room> _rooms = new ArrayList<>();
	private List<Hardpoint> _hardpoints = new ArrayList<>();
	private Deck _deckAbove;
	private Deck _deckBelow;
	
	public BasicDeck(DeckNumber deckNumber) {
		super();
		
		_deckNumber = deckNumber;
	}
	
	@Override
	public void update(float elapsedTime) {
		// TODO Auto-generated method stub
		
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
	}
}
