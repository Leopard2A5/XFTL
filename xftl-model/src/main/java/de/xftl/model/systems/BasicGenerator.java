package de.xftl.model.systems;

import de.xftl.spec.model.systems.Energy;
import de.xftl.spec.model.systems.EnergyProducingSystem;

public class BasicGenerator implements EnergyProducingSystem {

    private Energy _energyOutput;
    
    public BasicGenerator(final Energy energyOutput) {
        super();
        
        _energyOutput = energyOutput;
    }
    
    @Override
    public void update(final float elapsedTime) {
        // TODO implement me
    }

    @Override
    public Energy getEnergyOutput() {
        return _energyOutput;
    }

}
