package de.xftl.spec.model.systems;

import de.xftl.spec.model.ships.Room;

public interface LifeSupport extends EnergyConsumingSystem {
    public void addRoom(Room room);
    public float getAverageOxygenLevel();
}
