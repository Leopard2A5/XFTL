package de.xftl.model.ships;

import de.xftl.spec.model.ships.Door;

public class BasicDoor extends AbstractRoomConnector implements Door {

	private boolean _open;
	
	@Override
	public void update(float elapsedTime) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public boolean isOpen() {
		return _open;
	}

}
