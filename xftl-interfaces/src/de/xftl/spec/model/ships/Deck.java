package de.xftl.spec.model.ships;

import java.util.List;

import de.xftl.spec.model.XFTLModelObject;
import de.xftl.spec.model.hardpoints.Hardpoint;

public interface Deck extends XFTLModelObject {
	public List<Room> getRooms();
	public List<Hardpoint> getHardpoints();
}
