package de.xftl.model.ships;

import java.util.List;

import de.xftl.spec.model.ships.Door;
import de.xftl.spec.model.ships.Room;

public class BasicDoor extends AbstractRoomConnector implements Door {

	private boolean _open;
	
	public BasicDoor(List<Room> rooms) {
		super(rooms);
	}
	
	@Override
	public void update(float elapsedTime) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public boolean isOpen() {
		return _open;
	}

}
