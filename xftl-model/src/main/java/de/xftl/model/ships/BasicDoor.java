package de.xftl.model.ships;

import de.xftl.spec.model.ships.Door;
import de.xftl.spec.model.ships.Room;

public class BasicDoor extends AbstractRoomConnector implements Door {

	private boolean _open;
	
	public BasicDoor() {
		super();
	}
	
	@Override
    public void addRoom(final Room room) {
	    if (getConnectedRooms().size() == 2)
	        throw new RuntimeException("This door already has 2 rooms!");
	    
	    super.addRoom(room);
	    room.addRoomConnector(this);
    }
	
	@Override
	public void update(final float elapsedTime) {
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
