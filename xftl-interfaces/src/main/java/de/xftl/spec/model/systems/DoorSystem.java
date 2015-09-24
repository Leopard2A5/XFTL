package de.xftl.spec.model.systems;

import de.xftl.spec.model.ships.RoomConnector;

public interface DoorSystem extends EnergyConsumingSystem {
    public void addRoomConnector(RoomConnector roomConnector);
    public void openRoomConnector(RoomConnector rc);
    public void closeRoomConnector(RoomConnector rc);
    public void openAllInternalRoomConnectors();
    public void closeAllRoomConnectors();
    public boolean canOperateRoomConnectorsRemotely();
}
