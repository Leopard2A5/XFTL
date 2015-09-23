package de.xftl.game.states.combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

import de.xftl.game.framework.XftlGameRenderer;
import de.xftl.game.framework.ui.TileRenderedGameObject;
import de.xftl.spec.model.Direction;
import de.xftl.spec.model.ships.Door;

public class DoorRenderer extends TileRenderedGameObject {

	private Door _door;
	private Texture _doorTexture;
	private Sprite _doorClosedSprite;
	private Sprite _doorOpenedSprite;
	private float _offsetX;
	private float _offsetY;
	private boolean _isVertical;
	
	public Door getDoor() {
		return _door;
	}
		
	public DoorRenderer(XftlGameRenderer game, Door door, float x, float y, Direction direction) {
		super(game);

		_door = door;
		_doorTexture = getResources().getTexture("tex/door.png");
		_doorClosedSprite = new Sprite(_doorTexture, 0, 0, 32, 32);
		_doorOpenedSprite = new Sprite(_doorTexture, 32, 0, 32, 32);
		_doorClosedSprite.setOrigin(16, 16);
		_doorOpenedSprite.setOrigin(16, 16);
		
		_offsetX = getSignXOfDirection(direction) * 16;
		_offsetY = getSignYOfDirection(direction) * 16;
		
		_doorClosedSprite.setPosition(x+_offsetX, y+_offsetY);
		_doorOpenedSprite.setPosition(x+_offsetX, y+_offsetY);
		
		_isVertical = isVertical(direction);
		if (_isVertical) {
			_doorOpenedSprite.setRotation(90);
			_doorClosedSprite.setRotation(90);
		}
	}
	
	public void setPosition(float x, float y) {
		float diffX = x - _doorOpenedSprite.getX();
		float diffY = y - _doorOpenedSprite.getY();
		_doorClosedSprite.translate(diffX, diffY);
		_doorOpenedSprite.translate(diffX, diffY);
	}
	
	public float getX() {
		return _doorOpenedSprite.getX();
	}
	
	public float getY() {
		return _doorOpenedSprite.getY();
	}
	
	@Override
	public void update(float elapsedTime) {
		boolean mouseButtonPressed = getGame().getMouse().isLeftButtonDownOnce();
				
		Rectangle bounds = _doorOpenedSprite.getBoundingRectangle();
		if (_isVertical) {
			bounds.x += 12;
			bounds.width = 8;
		}else {
			bounds.y += 12;
			bounds.height = 8;
		}
		boolean mouseIntersects = bounds.contains(Gdx.input.getX(), Gdx.input.getY());
		
		if (mouseButtonPressed && mouseIntersects) {
			if (_door.isOpen()) _door.close(); else _door.open();
		}
	}
	
	public void draw() {
		if (_door.isOpen()) {
			_doorOpenedSprite.draw(getSpriteBatch());
		}
		else {
			_doorClosedSprite.draw(getSpriteBatch());
		}
	}
}
