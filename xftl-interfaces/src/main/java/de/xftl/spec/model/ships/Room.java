package de.xftl.spec.model.ships;

import java.util.List;

import de.xftl.spec.model.XFTLModelObject;
import de.xftl.spec.model.crew.CrewMember;
import de.xftl.spec.model.systems.ShipSystem;

public interface Room extends XFTLModelObject, Positioned<TileUnit> {
	public ShipSystem getSystem();
	public List<Tile> getTiles();
	public List<CrewMember> getCrewMembers();
	public List<RoomConnector> getRoomConnectors();
	public List<Room> getAdjacentRooms();
	public List<Room> getAdjacentRooms(Room origin);
	public OxygenLevel getOxygenLevel();
	public TileUnit getWidth();
	public TileUnit getHeigth();
	
	public void addRoomConnector(RoomConnector rc);
}
