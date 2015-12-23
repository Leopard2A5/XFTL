package de.xftl.model.crew;

import java.util.Optional;

import de.xftl.spec.model.Point;
import de.xftl.spec.model.crew.CrewMember;
import de.xftl.spec.model.crew.Health;
import de.xftl.spec.model.ships.Tile;

public abstract class AbstractCrewMember implements CrewMember {
	
	private Health health = new Health();
	private Optional<Tile> currentTile;
	private Point<Float> leftUpperCornerPos;
	
	public AbstractCrewMember(final Tile tile) {
		super();
		
		this.currentTile = Optional.ofNullable(tile);
		if (this.currentTile.isPresent()) {
			final Point<Integer> tilePos = currentTile.get().getLeftUpperCornerPos();
			this.leftUpperCornerPos = new Point<>(Float.valueOf(tilePos.getX()), Float.valueOf(tilePos.getY()));
		}
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
	public Optional<Tile> getCurrentTile() {
		return currentTile;
	}
	
	@Override
	public Point<Float> getLeftUpperCornerPos() {
		return leftUpperCornerPos;
	}
}
