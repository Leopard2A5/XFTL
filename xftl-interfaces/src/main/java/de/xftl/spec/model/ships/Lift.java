package de.xftl.spec.model.ships;

public interface Lift extends RoomConnector {
	public boolean isInTransit();
	public Deck getDeck();
}
