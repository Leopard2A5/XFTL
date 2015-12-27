package de.xftl.spec.model.ships;

import java.util.List;

import de.xftl.spec.model.XFTLModelObject;
import de.xftl.spec.model.hardpoints.Hardpoint;
import de.xftl.spec.model.systems.ShipSystem;

public interface Deck extends XFTLModelObject {
	public Ship getShip();
	public int getDeckNumber();
	public List<Room> getRooms();
	public void addRoom(Room room);
	public List<Hardpoint> getHardpoints();
	public Deck getDeckAbove();
	public Deck getDeckBelow();
	public void onSystemAdded(ShipSystem system);
	
	public void onRoomConnectorAdded(RoomConnector roomConnector);
}
