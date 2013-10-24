package de.xftl.spec.model.ships;

import java.util.List;

import de.xftl.spec.model.XFTLModelObject;
import de.xftl.spec.model.crew.CrewMember;
import de.xftl.spec.model.systems.ShipSystem;

public interface Room extends XFTLModelObject, Positioned<TileUnit> {
    public static final float MIN_OXYGEN = 0;
    public static final float MAX_OXYGEN = 1;
    
	public ShipSystem getSystem();
	public List<Tile> getTiles();
	public List<CrewMember> getCrewMembers();
	public List<RoomConnector> getRoomConnectors();
	public List<Room> getAdjacentRooms();
	public List<Room> getAdjacentRooms(Room origin);
	
	public float getOxygenLevel();
	public void consumeOxygen(float oxygen);
	public void replenishOxygen(float oxygen);
	
	public TileUnit getWidth();
	public TileUnit getHeigth();
	
	public void addRoomConnector(RoomConnector rc);
}
