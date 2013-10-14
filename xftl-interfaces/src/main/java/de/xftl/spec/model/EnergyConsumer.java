package de.xftl.spec.model;

import de.xftl.spec.model.systems.Energy;
import de.xftl.spec.model.systems.EnergyPriority;

public interface EnergyConsumer {
	public Energy getEnergyConsumption();
	public EnergyPriority getPriority();
}
