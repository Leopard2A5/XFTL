package de.xftl.model.systems;

import java.util.ArrayList;
import java.util.List;

import de.xftl.spec.model.EnergyConsumer;
import de.xftl.spec.model.systems.Energy;
import de.xftl.spec.model.systems.EnergyManager;
import de.xftl.spec.model.systems.EnergyProducingSystem;

public class BasicEnergyManager implements EnergyManager {

    private List<EnergyProducingSystem> _energyProducers = new ArrayList<>();
    private List<EnergyConsumer> _energyConsumers = new ArrayList<>();
    
    private Energy _maxEnergy = Energy.valueOf(0);
    private Energy _consumedEnergy = Energy.valueOf(0);
    
    @Override
    public Energy getMaxEnergy() {
        return _maxEnergy;
    }
    
    private void calculateMaxEnergy() {
    	Energy e = Energy.valueOf(0);
        
        for (EnergyProducingSystem prod : _energyProducers)
            e = e.plus(prod.getEnergyOutput());
        
        _maxEnergy = e;
    }

    @Override
    public Energy getConsumedEnergy() {
        return _consumedEnergy;
    }
    
    private void calculateConsumedEnergy() {
        Energy e = Energy.valueOf(0);
        
        for (EnergyConsumer consumer : _energyConsumers)
            e = e.plus(consumer.getEnergyConsumption());
        
        _consumedEnergy = e;
    }

    @Override
    public void addEnergyProducer(final EnergyProducingSystem producer) {
        if (!_energyProducers.contains(producer)) {
            _energyProducers.add(producer);
            calculateMaxEnergy();
        }
    }

    @Override
    public void removeEnergyProducer(final EnergyProducingSystem producer) {
        _energyProducers.remove(producer);
        calculateMaxEnergy();
    }

    @Override
    public void addEnergyConsumer(final EnergyConsumer consumer) {
        if (!_energyConsumers.contains(consumer)) {
            _energyConsumers.add(consumer);
            calculateConsumedEnergy();
        }
    }

    @Override
    public void removeEnergyConsumer(final EnergyConsumer consumer) {
        _energyConsumers.remove(consumer);
        calculateConsumedEnergy();
    }
}
