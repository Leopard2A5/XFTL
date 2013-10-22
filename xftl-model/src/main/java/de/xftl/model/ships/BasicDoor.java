package de.xftl.model.ships;

import java.util.List;

import de.xftl.spec.model.ships.Door;
import de.xftl.spec.model.ships.Room;

public class BasicDoor extends AbstractRoomConnector implements Door {

	private boolean _open;
	
	private float _elapsedTime;
	
	public BasicDoor(List<Room> rooms) {
		super(rooms);
		
		if (rooms.size() > 2)
			throw new RuntimeException("A Door can only connect 2 rooms!");
	}
	
	@Override
	public void update(float elapsedTime) {
		_elapsedTime += elapsedTime;
		
		if (_elapsedTime > 1) {
		    if (Math.random() > 0.5)
		        _open = !_open;
		    
		    _elapsedTime = 0;
		}
	}
	
	@Override
	public boolean isOpen() {
		return _open;
	}

}
