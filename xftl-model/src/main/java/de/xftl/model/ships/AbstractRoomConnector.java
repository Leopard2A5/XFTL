package de.xftl.model.ships;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.xftl.spec.model.ships.Room;
import de.xftl.spec.model.ships.RoomConnector;

public abstract class AbstractRoomConnector implements RoomConnector {

	private List<Room> _connectedRooms;

	public AbstractRoomConnector(List<Room> rooms) {
		super();
		
		_connectedRooms = rooms;
		for (Room room : rooms) {
			room.addRoomConnector(this);
		}
	}
	
	@Override
	public List<Room> getConnectedRooms() {
		return Collections.unmodifiableList(_connectedRooms);
	}

	@Override
	public List<Room> getConnectedRooms(Room origin) {
		List<Room> ret = new ArrayList<>(_connectedRooms.size());

		for (Room room : _connectedRooms)
			if (!room.equals(origin))
				ret.add(room);

		return Collections.unmodifiableList(ret);
	}
}
