package de.xftl.game.states.combat;

import com.badlogic.gdx.graphics.g2d.Sprite;

import de.xftl.game.framework.XftlGameRenderer;
import de.xftl.game.framework.ui.RenderedGameObject;
import de.xftl.spec.model.Point;
import de.xftl.spec.model.ships.Tile;
import de.xftl.spec.model.ships.TileUnit;

public class TileRenderer extends RenderedGameObject{

	private Sprite _floorSprite;
	private Sprite _fireSprite;
	private float _fireLifeTime;
	private Tile _tile;
	private int _frame;
	
	public TileRenderer(XftlGameRenderer game, Tile tile) {
		super(game);
		_tile = tile;
		
		Point<TileUnit> pos = tile.getLeftUpperCornerPos();
		_floorSprite = new Sprite(getResources().getTexture("res/tex/floor.png"));
		_fireSprite = new Sprite(getResources().getTexture("res/tex/fire.png"),0,0,32,32);
		
		_floorSprite.setPosition(pos.getX().getValue() * game.TileSize, pos.getY().getValue() * game.TileSize);
		_fireSprite.setPosition(pos.getX().getValue() * game.TileSize, pos.getY().getValue() * game.TileSize);
		_fireSprite.flip(false, true);
	}
	
	@Override
	public void update(float elapsedTime) {
		_fireSprite.setScale(0.5f + 1f * 0.5f);
		if (_tile.isOnFire()) {
			_fireLifeTime += elapsedTime;
			if (_fireLifeTime > 0.1f) {
				if (++_frame == 4) _frame = 0;
				_fireSprite.setRegion(_frame * 32, 0, 32, 32);
				_fireSprite.flip(false, true);
				_fireLifeTime -= 0.1f;
			}
		}
		else {
			_fireLifeTime = 0;
		}
	}
	
	@Override
	public void draw() {
		_floorSprite.draw(getSpriteBatch());
		if (_tile.isOnFire()) _fireSprite.draw(getSpriteBatch());
	}
	
	@Override
	public void setPosition(float x, float y) {
		float diffX = x - getX();
		float diffY = y - getY();
		_floorSprite.translate(diffX, diffY);
		_fireSprite.translate(diffX, diffY);
	}

	@Override
	public float getX() {
		return _floorSprite.getX();
	}

	@Override
	public float getY() {
		return _floorSprite.getY();
	}

}
