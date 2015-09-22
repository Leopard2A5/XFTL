package de.xftl.game.states.combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

import de.xftl.game.framework.XftlGameRenderer;
import de.xftl.game.framework.ui.TileRenderedGameObject;
import de.xftl.spec.model.Direction;
import de.xftl.spec.model.ships.Lift;

public class LiftRenderer extends TileRenderedGameObject {
	
	private Lift _lift;
	private Texture _texture;
	private Sprite _openedSprite;
	private Sprite _closedSprite;
	private Direction _direction;
	
	public Lift getLift() {
		return _lift;
	}

	public LiftRenderer(XftlGameRenderer game, Lift lift, float x, float y, Direction direction) {
		super(game);
		_lift = lift;
		_texture = getResources().getTexture("tex/lift.png");
		_openedSprite = new Sprite(_texture, 32, 0, 32, 36);
		_openedSprite.flip(false, true);
		_openedSprite.setOrigin(16, 16);
		_openedSprite.setPosition(x, y);
		_closedSprite = new Sprite(_texture, 0, 0, 32, 36);
		_closedSprite.flip(false, true);
		_closedSprite.setOrigin(16, 16);
		_closedSprite.setPosition(x, y);
		_direction = direction;
		
		switch(direction) {
			case NORTH:
				_closedSprite.setRotation(0);
				_openedSprite.setRotation(0);
				break;
			case EAST:
				_closedSprite.setRotation(90);
				_openedSprite.setRotation(90);
				break;
			case WEST:
				_closedSprite.setRotation(-90);
				_openedSprite.setRotation(-90);
				break;
			case SOUTH:
				_closedSprite.setRotation(180);
				_openedSprite.setRotation(180);
				break;
		}
		
		_closedSprite.translate(getSignXOfDirection(direction) * 32, getSignYOfDirection(direction) * 32);
		_openedSprite.translate(getSignXOfDirection(direction) * 32, getSignYOfDirection(direction) * 32);
	}
	
	@Override
	public void update(float elapsedTime) {
		boolean mouseButtonPressed = getGame().getMouse().isLeftButtonDownOnce();
				
		Rectangle bounds = new Rectangle(_openedSprite.getX(), _openedSprite.getY(), 20, 20);
		bounds.x += getInvOffsetXForDirection(_direction) - 4;
		bounds.y += getInvOffsetYForDirection(_direction) - 4;
		
		if (isVertical(_direction)) {
			bounds.y += 6;
			bounds.width = 8;
		}else {
			bounds.x += 6;
			bounds.height = 8;
		}
		boolean mouseIntersects = bounds.contains(Gdx.input.getX(), Gdx.input.getY());
		
		if (mouseButtonPressed && mouseIntersects) {
			if (_lift.isOpen()) _lift.close(); else _lift.open();
		}
	}
	
	public void draw() {
		if (_lift.isOpen()) {
			_openedSprite.draw(getSpriteBatch());
		}
		else {
			_closedSprite.draw(getSpriteBatch());	
		}
	}

	public void setPosition(float x, float y) {
		_closedSprite.setPosition(x, y);
		_openedSprite.setPosition(x, y);
	}

	public float getX() {
		return _closedSprite.getX();
	}

	public float getY() {
		return _closedSprite.getY();
	}
}
