package de.xftl.spec.model.systems;

import de.xftl.spec.model.EnergyConsumer;

public interface EnergyManager {
    public Energy getMaxEnergy();
    public Energy getConsumedEnergy();
    
    public void addEnergyProducer(EnergyProducingSystem producer);
    public void removeEnergyProducer(EnergyProducingSystem producer);
    public void addEnergyConsumer(EnergyConsumer consumer);
    public void removeEnergyConsumer(EnergyConsumer consumer);
}
