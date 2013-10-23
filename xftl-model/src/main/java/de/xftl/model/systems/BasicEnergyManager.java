/*
 * Copyright 2005-2012 IT Service Omikron.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
    public void addEnergyProducer(EnergyProducingSystem producer) {
        if (!_energyProducers.contains(producer)) {
            _energyProducers.add(producer);
            calculateMaxEnergy();
        }
    }

    @Override
    public void removeEnergyProducer(EnergyProducingSystem producer) {
        _energyProducers.remove(producer);
        calculateMaxEnergy();
    }

    @Override
    public void addEnergyConsumer(EnergyConsumer consumer) {
        if (!_energyConsumers.contains(consumer)) {
            _energyConsumers.add(consumer);
            calculateConsumedEnergy();
        }
    }

    @Override
    public void removeEnergyConsumer(EnergyConsumer consumer) {
        _energyConsumers.remove(consumer);
        calculateConsumedEnergy();
    }
}
