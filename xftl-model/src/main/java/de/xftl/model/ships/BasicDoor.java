package de.xftl.model.ships;

import de.xftl.spec.model.ships.Door;
import de.xftl.spec.model.ships.Room;

public class BasicDoor extends AbstractRoomConnector implements Door {

	private boolean _open;
	
	private float _elapsedTime;
	
	public BasicDoor() {
		super();
	}
	
	@Override
    public void addRoom(Room room) {
	    if (getConnectedRooms().size() == 2)
	        throw new RuntimeException("This door already has 2 rooms!");
	    
	    super.addRoom(room);
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

    @Override
    public void close() {
        _open = false;
    }

    @Override
    public void open() {
        _open = true;
    }

    @Override
    public boolean isAirlock() {
        return getConnectedRooms().size() == 1;
    }

}
