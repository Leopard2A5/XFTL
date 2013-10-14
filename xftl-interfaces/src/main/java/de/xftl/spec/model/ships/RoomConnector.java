package de.xftl.spec.model.ships;

import java.util.List;

import de.xftl.spec.model.XFTLModelObject;

public interface RoomConnector extends XFTLModelObject {
	public List<Room> getConnectedRooms(Room origin);
}
