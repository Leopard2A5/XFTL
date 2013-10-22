package de.xftl.game.states.combat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import de.xftl.game.framework.GameObject;
import de.xftl.game.framework.XftlGameRenderer;
import de.xftl.spec.model.ships.Door;

public class DoorRenderer extends GameObject {

	private Door _door;
	private Texture _doorTexture;
	private Sprite _doorClosedSprite;
	private Sprite _doorOpenedSprite;
		
	public DoorRenderer(XftlGameRenderer game, Door door, boolean vertical) {
		super(game);

		_door = door;
		_doorTexture = getResources().getTexture("res/tex/door.png");
		_doorClosedSprite = new Sprite(_doorTexture, 0, 0, 32, 32);
		_doorOpenedSprite = new Sprite(_doorTexture, 32, 0, 32, 32);
		_doorClosedSprite.setOrigin(0, 16);
		_doorOpenedSprite.setOrigin(0, 16);
		
		if (vertical) {
			_doorOpenedSprite.setRotation(90);
			_doorClosedSprite.setRotation(90);
		}
	}
	
	public void setPosition(float x, float y) {
		float diffX = x - _doorOpenedSprite.getX();
		float diffY = y - _doorOpenedSprite.getY();
		_doorClosedSprite.translate(diffX, diffY-8);
		_doorOpenedSprite.translate(diffX, diffY-8);
	}
	
	public float getX() {
		return _doorOpenedSprite.getX();
	}
	
	public float getY() {
		return _doorOpenedSprite.getY();
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
