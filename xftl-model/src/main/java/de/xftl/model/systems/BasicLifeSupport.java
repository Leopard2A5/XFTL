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

import de.xftl.spec.model.ships.Room;
import de.xftl.spec.model.systems.Energy;
import de.xftl.spec.model.systems.EnergyPriority;
import de.xftl.spec.model.systems.LifeSupport;

public class BasicLifeSupport implements LifeSupport {

    private Energy _energyConsumption = Energy.valueOf(1);
    private List<Room> _rooms = new ArrayList<>();
    
    @Override
    public void update(float elapsedTime) {
        // TODO implement me
    }

    @Override
    public Energy getEnergyConsumption() {
        return _energyConsumption;
    }

    @Override
    public EnergyPriority getPriority() {
        // TODO implement me
        return null;
    }

    @Override
    public void addRoom(Room room) {
        if (!_rooms.contains(room))
            _rooms.add(room);
    }

    @Override
    public float getAverageOxygenLevel() {
        float ret = 0;
        
        for (Room room : _rooms)
            ret += room.getOxygenLevel();
        
        return ret / _rooms.size();
    }

}
