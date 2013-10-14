package de.xftl.spec.model.ships;

import java.util.List;

import de.xftl.spec.model.XFTLModelObject;
import de.xftl.spec.model.crew.CrewMember;

public interface Room extends XFTLModelObject {
	public System getSystem();
	public List<Tile> getTiles();
	public List<CrewMember> getCrewMembers();
	public List<RoomConnector> getRoomConnectors();
	public List<Room> getAdjacentRooms();
	public List<Room> getAdjacentRooms(Room origin);
	public OxygenLevel getOxygenLevel();
}
