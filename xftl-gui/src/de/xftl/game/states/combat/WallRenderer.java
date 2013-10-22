package de.xftl.game.states.combat;

import com.badlogic.gdx.graphics.g2d.Sprite;

import de.xftl.game.framework.XftlGameRenderer;
import de.xftl.game.framework.ui.TileRenderedGameObject;
import de.xftl.spec.model.Direction;

public class WallRenderer extends TileRenderedGameObject {

	private final int WALLTHICKNESS = 4;
	private final int OFFSET = 2;
	
	private Sprite _wallSprite;
	
	public WallRenderer(XftlGameRenderer game, float x, float y, Direction direction) {
		super(game);
		
		_wallSprite = new Sprite(getGame().getBlankTexture());
		boolean vertical = direction == Direction.WEST || direction == Direction.EAST;
		
		if (vertical) {
			_wallSprite.setSize(WALLTHICKNESS, getGame().TileSize + OFFSET * 2);
		}
		else {
			_wallSprite.setSize(getGame().TileSize + OFFSET * 2, WALLTHICKNESS);
		}
		
		float posX = x + getOffsetXForDirection(direction);
		float posY = y + getOffsetYForDirection(direction);
		
		_wallSprite.setPosition(posX - OFFSET, posY-OFFSET);
		_wallSprite.setColor(0.0f, 0.0f, 0.0f, 1.0f);
	}
	
	public void setPosition(float x, float y) {
		float diffX = x - _wallSprite.getX();
		float diffY = y - _wallSprite.getY();
		_wallSprite.translate(diffX, diffY);
	}
	
	public float getX() {
		return _wallSprite.getX();
	}
	
	public float getY() {
		return _wallSprite.getY();
	}

	public void draw() {
		_wallSprite.draw(getSpriteBatch());
	}
}
