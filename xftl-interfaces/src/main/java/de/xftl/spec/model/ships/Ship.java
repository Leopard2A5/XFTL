package de.xftl.spec.model.ships;

import java.util.List;

import de.xftl.spec.model.XFTLModelObject;
import de.xftl.spec.model.systems.DoorSystem;
import de.xftl.spec.model.systems.EnergyProducingSystem;
import de.xftl.spec.model.systems.ShipSystem;

public interface Ship extends XFTLModelObject {
	public List<Deck> getDecks();
	public Hitpoints getHitpoints();
	public void addSystem(ShipSystem system);
	public List<ShipSystem> getAllSystems();
	public List<EnergyProducingSystem> getEnergyProducingSystems();
	public DoorSystem getDoorSystem();
}
