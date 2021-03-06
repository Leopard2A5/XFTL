package de.xftl.spec.model.crew;

import java.util.Optional;

import de.xftl.spec.model.XFTLModelObject;
import de.xftl.spec.model.ships.Positioned;
import de.xftl.spec.model.ships.Tile;

public interface CrewMember extends XFTLModelObject, Positioned<Float> {
	public Health getHealth();
	
	public Tile getCurrentTile();
	
	public Optional<Movement> getMovement();
	public Optional<Tile> getMovementTargetTile();
	public void setMovement(Movement movement);
}
