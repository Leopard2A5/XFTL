package de.xftl.model.ships;

import java.util.List;

import de.xftl.spec.model.ships.Lift;
import de.xftl.spec.model.ships.Room;

public class BasicLift extends AbstractRoomConnector implements Lift {

	public BasicLift(List<Room> rooms) {
		super(rooms);
	}
	
	@Override
	public void update(float elapsedTime) {
		// TODO Auto-generated method stub

	}
	
}
