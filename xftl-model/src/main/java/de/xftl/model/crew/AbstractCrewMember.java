package de.xftl.model.crew;

import java.util.Optional;

import de.xftl.spec.model.Point;
import de.xftl.spec.model.crew.CrewMember;
import de.xftl.spec.model.crew.Health;
import de.xftl.spec.model.crew.Movement;
import de.xftl.spec.model.ships.Tile;

public abstract class AbstractCrewMember implements CrewMember {
	
	private Health health = new Health();
	private Tile currentTile;
	private Point<Float> leftUpperCornerPos;
	private Movement movement;
	
	public AbstractCrewMember(final Tile tile) {
		super();
		
		this.currentTile = tile;
		currentTile.setCrewMember(this);
		final Point<Integer> tilePos = currentTile.getLeftUpperCornerPos();
		this.leftUpperCornerPos = new Point<>(Float.valueOf(tilePos.getX()), Float.valueOf(tilePos.getY()));
	}
	
	@Override
	public void update(float elapsedTime) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Health getHealth() {
		return health;
	}
	
	@Override
	public Tile getCurrentTile() {
		return currentTile;
	}
	
	@Override
	public Point<Float> getLeftUpperCornerPos() {
		return leftUpperCornerPos;
	}
	
	@Override
	public Optional<Movement> getMovement() {
		return Optional.ofNullable(movement);
	}
	
	@Override
	public Optional<Tile> getMovementTargetTile() {
		if (movement == null)
			return Optional.empty();
		else
			return Optional.of(movement.getTargetTile());
	}
	
	@Override
	public void setMovement(final Movement movement) {
		this.movement = movement;
	}
}
