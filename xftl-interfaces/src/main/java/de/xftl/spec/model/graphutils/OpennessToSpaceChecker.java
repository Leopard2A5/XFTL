package de.xftl.spec.model.graphutils;

import java.util.HashSet;
import java.util.Set;

import de.xftl.spec.model.ships.Room;
import de.xftl.spec.model.ships.RoomConnector;

public class OpennessToSpaceChecker {

	private final Set<Room> _visitedRooms = new HashSet<>();
	
	public boolean isOpenToSpace(final Room room) {
		if (!_visitedRooms.contains(room)) {
			for (final RoomConnector rc : room.getRoomConnectors()) {
				if (rc.getConnectedRooms().size() == 1 && rc.isOpen())
					return true;
			}
			
			_visitedRooms.add(room);
			
			for (final RoomConnector rc : room.getRoomConnectors()) {
				if (rc.isOpen()) {
					for (final Room curRoom : rc.getConnectedRooms(room)) {
						if (isOpenToSpace(curRoom))
							return true;
					}
				}
			}
		}
		
		return false;
	}
	
	public void reset() {
		_visitedRooms.clear();
	}
	
}
