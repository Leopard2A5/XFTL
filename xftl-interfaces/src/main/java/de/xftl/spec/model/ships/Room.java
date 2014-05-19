package de.xftl.spec.model.ships;

import java.util.List;

import de.xftl.spec.model.XFTLModelObject;
import de.xftl.spec.model.crew.CrewMember;
import de.xftl.spec.model.systems.ShipSystem;

public interface Room extends XFTLModelObject, Positioned<Integer> {
    public static final float NO_OXYGEN = 0;
    public static final float MAX_OXYGEN = 1;
    
	public ShipSystem getSystem();
	public List<Tile> getTiles();
	public List<CrewMember> getCrewMembers();
	public List<RoomConnector> getRoomConnectors();
	public List<Room> getAdjacentRooms();
	public List<Room> getAdjacentRooms(Room origin);
	
	public void setDeck(Deck deck);

	public boolean isOnFire();
	public float getOxygenLevel();
	public void consumeOxygen(float oxygen);
	public void replenishOxygen(float oxygen);
	
	public int getWidth();
	public int getHeigth();
	
	public void addRoomConnector(RoomConnector rc);
}
