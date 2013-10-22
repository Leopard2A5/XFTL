package de.xftl.spec.model.ships;

import java.util.List;

import de.xftl.spec.model.XFTLModelObject;

public interface Ship extends XFTLModelObject {
	public List<Deck> getDecks();
	public Hitpoints getHitpoints();
	public List<System> getSystems();
}
