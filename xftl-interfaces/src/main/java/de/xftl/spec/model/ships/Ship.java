package de.xftl.spec.model.ships;

import java.util.List;

import de.xftl.spec.model.XFTLModelObject;
import de.xftl.spec.model.systems.DoorSystem;
import de.xftl.spec.model.systems.EnergyManager;
import de.xftl.spec.model.systems.LifeSupport;
import de.xftl.spec.model.systems.ShipSystem;

public interface Ship extends XFTLModelObject {
	public List<Deck> getDecks();
	public Hitpoints getHitpoints();
	public void addSystem(ShipSystem system);
	public List<ShipSystem> getAllSystems();
	public DoorSystem getDoorSystem();
	public EnergyManager getEnergyManager();
	public LifeSupport getLifeSupport();
	
	public void onRoomAdded(Room room);
	public void onRoomConnectorAdded(RoomConnector roomConnector);
}
