package de.xftl.spec.model.ships;

public interface Door extends RoomConnector {
	public boolean isOpen();
	public void close();
	public void open();
}
