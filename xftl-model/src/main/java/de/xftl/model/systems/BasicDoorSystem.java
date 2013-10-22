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

import de.xftl.spec.model.ships.Door;
import de.xftl.spec.model.ships.RoomConnector;
import de.xftl.spec.model.systems.DoorSystem;
import de.xftl.spec.model.systems.Energy;
import de.xftl.spec.model.systems.EnergyPriority;

public class BasicDoorSystem implements DoorSystem {

    private List<RoomConnector> _roomConnectors = new ArrayList<>();
    
    @Override
    public void update(float elapsedTime) {
        // TODO implement me
    }

    @Override
    public Energy getEnergyConsumption() {
        // TODO implement me
        return null;
    }

    @Override
    public EnergyPriority getPriority() {
        // TODO implement me
        return null;
    }

    @Override
    public void addRoomConnector(RoomConnector roomConnector) {
        _roomConnectors.add(roomConnector);
    }
    
    @Override
    public void openDoor(Door door) {
        // TODO implement me
    }

    @Override
    public void openAllInternalDoors() {
        // TODO implement me
    }

    @Override
    public void closeAllDoors() {
        // TODO implement me
    }

}
