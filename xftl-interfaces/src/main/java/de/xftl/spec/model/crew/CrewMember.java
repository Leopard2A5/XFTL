package de.xftl.spec.model.crew;

import de.xftl.spec.model.XFTLModelObject;
import de.xftl.spec.model.ships.Room;

public interface CrewMember extends XFTLModelObject {
	public Health getHealth();
	public void goToRoom(Room room);
}
