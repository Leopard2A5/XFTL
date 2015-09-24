package de.xftl.model.systems;

import java.util.ArrayList;
import java.util.List;

import de.xftl.spec.model.ships.Door;
import de.xftl.spec.model.ships.RoomConnector;
import de.xftl.spec.model.systems.DoorSystem;
import de.xftl.spec.model.systems.Energy;
import de.xftl.spec.model.systems.EnergyPriority;

public class BasicDoorSystem implements DoorSystem {
    
    private static final Energy MIN_ENERGY_LEVEL_FOR_REMOTE_DOOR_OP = Energy.valueOf(1);

    private List<RoomConnector> _roomConnectors = new ArrayList<>();
    private Energy _energyConsumption = Energy.valueOf(1);
    
    @Override
    public void update(final float elapsedTime) {
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
    public void addRoomConnector(final RoomConnector roomConnector) {
        if (!_roomConnectors.contains(roomConnector))
            _roomConnectors.add(roomConnector);
    }
    
    @Override
    public void openRoomConnector(final RoomConnector rc) {
        if (!canOperateRoomConnectorsRemotely())
            return;
        
        rc.open();
    }
    
    @Override
    public void closeRoomConnector(final RoomConnector rc) {
        if (!canOperateRoomConnectorsRemotely())
            return;
        
        rc.close();
    }

    @Override
    public void openAllInternalRoomConnectors() {
        if (!canOperateRoomConnectorsRemotely())
            return;
        
        for (RoomConnector rc : _roomConnectors) {
            if (rc instanceof Door && ((Door) rc).isAirlock())
                continue;
            
            rc.open();
        }
    }

    @Override
    public void closeAllRoomConnectors() {
        if (!canOperateRoomConnectorsRemotely())
            return;
        
        for (RoomConnector rc : _roomConnectors)
            rc.close();
    }

    @Override
    public boolean canOperateRoomConnectorsRemotely() {
        return _energyConsumption.compareTo(MIN_ENERGY_LEVEL_FOR_REMOTE_DOOR_OP) >= 0;
    }
}
