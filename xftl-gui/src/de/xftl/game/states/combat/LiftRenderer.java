package de.xftl.game.states.combat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import de.xftl.game.framework.XftlGameRenderer;
import de.xftl.game.framework.ui.TileRenderedGameObject;
import de.xftl.spec.model.Direction;
import de.xftl.spec.model.ships.Lift;

public class LiftRenderer extends TileRenderedGameObject {
	
	private Lift _lift;
	private Texture _texture;
	private Sprite _sprite;
	
	public Lift getLift() {
		return _lift;
	}

	public LiftRenderer(XftlGameRenderer game, Lift lift, float x, float y, Direction direction) {
		super(game);
		_lift = lift;
		_texture = getResources().getTexture("res/tex/lift.png");
		_sprite = new Sprite(_texture);
		_sprite.flip(false, true);
		_sprite.setOrigin(16, 16);
		_sprite.setPosition(x, y);
		
		switch(direction) {
			case NORTH:
				_sprite.setRotation(0);
				break;
			case EAST:
				_sprite.setRotation(90);
				break;
			case WEST:
				_sprite.setRotation(-90);
				break;
			case SOUTH:
				_sprite.setRotation(180);
				break;
		}
		
		_sprite.translateX(getSignXOfDirection(direction) * 32);
		_sprite.translateY(getSignYOfDirection(direction) * 32);
	}
	
	public void draw() {
		_sprite.draw(getSpriteBatch());
	}

	public void setPosition(float x, float y) {
		_sprite.setPosition(x, y);
	}

	public float getX() {
		return _sprite.getX();
	}

	public float getY() {
		return _sprite.getY();
	}
}
