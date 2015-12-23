package de.xftl.model.systems;

import java.util.ArrayList;
import java.util.List;

import de.xftl.spec.model.ships.Room;
import de.xftl.spec.model.systems.Energy;
import de.xftl.spec.model.systems.EnergyPriority;
import de.xftl.spec.model.systems.LifeSupport;

public class BasicLifeSupport implements LifeSupport {

    private static final float OXYGEN_REPLENISHMENT_RATE = 0.1f;
    
    private Energy _energyConsumption = Energy.valueOf(1);
    private List<Room> _rooms = new ArrayList<>();
    
    @Override
    public void update(final float elapsedTime) {
        if (_energyConsumption.intValue() >= 1) {
            for (Room room : _rooms) {
                room.replenishOxygen((OXYGEN_REPLENISHMENT_RATE * elapsedTime) / room.getTiles().size());
            }
        }
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
    public void addRoom(final Room room) {
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
