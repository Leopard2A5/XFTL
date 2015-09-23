package de.xftl.game.framework.ui;

import de.xftl.game.framework.XftlGameRenderer;
import de.xftl.spec.model.Direction;

public abstract class TileRenderedGameObject extends RenderedGameObject {

	protected TileRenderedGameObject(XftlGameRenderer game) {
		super(game);
	}
	
	protected float getOffsetXForDirection(Direction direction) {
		return direction == Direction.EAST ? getGame().TileSize : 0.0f;
	}
	
	protected float getOffsetYForDirection(Direction direction) {
		return direction == Direction.SOUTH ? getGame().TileSize : 0.0f;
	}
	
	protected float getInvOffsetXForDirection(Direction direction) {
		return direction == Direction.WEST ? getGame().TileSize : 0.0f;
	}
	
	protected float getInvOffsetYForDirection(Direction direction) {
		return direction == Direction.NORTH ? getGame().TileSize : 0.0f;
	}
	
	protected float getSignXOfDirection(Direction direction) {
		return direction == Direction.EAST ? 1 : direction == Direction.WEST ? -1 : 0;
	}
	
	protected float getSignYOfDirection(Direction direction) {
		return direction == Direction.SOUTH ? 1 : direction == Direction.NORTH ? -1 : 0;
	}
	
	protected boolean isVertical(Direction direction) {
		return direction == Direction.WEST || direction == Direction.EAST;
	}
}
