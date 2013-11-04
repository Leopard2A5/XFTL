package de.xftl.game.states.combat;

import com.badlogic.gdx.graphics.g2d.Sprite;

import de.xftl.game.framework.XftlGameRenderer;
import de.xftl.game.framework.ui.RenderedGameObject;
import de.xftl.spec.model.Point;
import de.xftl.spec.model.ships.Room;

public class RoomOverlay extends RenderedGameObject{

	private Sprite _oxygenSprite;
	private Room _room;
	
	public RoomOverlay(XftlGameRenderer game, Room room) {
		super(game);
		_room = room;
		_oxygenSprite = new Sprite(game.getBlankTexture());
		
		Point<Integer> position = _room.getLeftUpperCornerPos();
		
		float x = position.getX() * getGame().TileSize;
		float y = position.getY() * getGame().TileSize;
		float height = _room.getHeigth() * getGame().TileSize;
		float width = _room.getWidth() * getGame().TileSize;
		
		_oxygenSprite.setPosition(x, y);
		_oxygenSprite.setSize(width, height);
	}
	
	@Override
	public void update(float elapsedTime) {
		float delta = 1.0f - _room.getOxygenLevel();
		_oxygenSprite.setColor(0.8f, 0.0f, 0.0f, delta * 0.5f);
	}
	
	@Override
	public void setPosition(float x, float y) {
		float diffX = x - _oxygenSprite.getX();
		float diffY = y - _oxygenSprite.getY();
		_oxygenSprite.translate(diffX, diffY);
	}
	
	@Override
	public void draw() {
		_oxygenSprite.draw(getSpriteBatch());
	}

	@Override
	public float getX() {
		return _oxygenSprite.getX();
	}

	@Override
	public float getY() {
		return _oxygenSprite.getY();
	}

}
