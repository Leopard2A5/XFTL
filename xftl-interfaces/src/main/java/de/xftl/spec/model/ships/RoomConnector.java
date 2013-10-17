package de.xftl.spec.model.ships;

import java.util.List;

import de.xftl.spec.model.XFTLModelObject;

public interface RoomConnector extends XFTLModelObject, TileOrRoomConnector {
	public List<Room> getConnectedRooms();
	public List<Room> getConnectedRooms(Room origin);
}
