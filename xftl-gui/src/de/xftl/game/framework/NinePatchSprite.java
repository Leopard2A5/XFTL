package de.xftl.game.framework;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class NinePatchSprite {
	
	/*
	 * Patchfield Array:
	 * -------------------
	 * | 0 |    1    | 2 |
	 * -------------------
	 * |   |         |   |
	 * | 3 |    4    | 5 |
	 * |   |         |   |
	 * -------------------
	 * | 6 |    7    | 8 |
	 * ------------------- 
	 */
	
	private float _width;
	private float _height;
	private Sprite[] _sprites;
		
	public NinePatchSprite(Texture texture, int leftBorder, int topBorder, int rightBorder, int bottomBorder) {
		_sprites = new Sprite[9];
		
		_width = texture.getWidth();
		_height = texture.getHeight();
		int scaleWidth = (int)_width - leftBorder - rightBorder;
		int scaleHeight = (int)_height - topBorder - bottomBorder;
		
		_sprites[0] = createSprite(texture, 0, 0, leftBorder, topBorder, 0, 0);
		_sprites[1] = createSprite(texture, leftBorder, 0, scaleWidth, topBorder, leftBorder, 0);
		_sprites[2] = createSprite(texture, leftBorder+scaleWidth, 0, rightBorder, topBorder, leftBorder+scaleWidth, 0);
		
		_sprites[3] = createSprite(texture, 0, topBorder, leftBorder, scaleHeight, 0, topBorder);
		_sprites[4] = createSprite(texture, leftBorder, topBorder, scaleWidth, scaleHeight, leftBorder, topBorder);
		_sprites[5] = createSprite(texture, leftBorder+scaleWidth, topBorder, rightBorder, scaleHeight, leftBorder+scaleWidth, topBorder);
		
		_sprites[6] = createSprite(texture, 0, topBorder+scaleHeight, leftBorder, bottomBorder, 0, topBorder+scaleHeight);
		_sprites[7] = createSprite(texture, leftBorder, topBorder+scaleHeight, scaleWidth, bottomBorder, leftBorder, topBorder+scaleHeight);
		_sprites[8] = createSprite(texture, leftBorder+scaleWidth, topBorder+scaleHeight, rightBorder, bottomBorder, leftBorder+scaleWidth, topBorder+scaleHeight);
	}
	
	private Sprite createSprite(Texture texture, int sx, int sy, int sw, int sh, float x, float y) {
		
		Sprite sprite = new Sprite(texture, sx, sy, sw, sh);
		sprite.flip(false, true);
		sprite.setPosition(x, y);
		
		return sprite;
	}
	
	public float getX() {
		return _sprites[0].getX();
	}
	
	public float getY() {
		return _sprites[0].getY();
	}
	
	public void setPosition(float x, float y) {
		float differenceX = x - _sprites[0].getX();
		float differenceY = y - _sprites[0].getY();
		
		for(int i = 0; i < _sprites.length; i++){
			_sprites[i].translate(differenceX, differenceY);
		}
	}
	
	public void setSize(float sizeX, float sizeY) {
		_width = sizeX;
		_height = sizeY;
		
		float left = _sprites[0].getWidth();
		float right = _sprites[2].getWidth();
		float innerWidth = _width - left - right;
		float top = _sprites[0].getHeight();
		float bottom = _sprites[6].getHeight();
		float innerHeight = _height - top - bottom;
		
		_sprites[1].setSize(innerWidth, top);
		_sprites[3].setSize(left, innerHeight);
		_sprites[4].setSize(innerWidth, innerHeight);
		_sprites[5].setSize(right, innerHeight);
		_sprites[7].setSize(innerWidth, bottom);
		
		_sprites[2].setPosition(_sprites[1].getX()+innerWidth, _sprites[1].getY());
		_sprites[5].setPosition(_sprites[4].getX()+innerWidth, _sprites[4].getY());
		_sprites[6].setPosition(_sprites[3].getX(), _sprites[3].getY()+innerHeight);
		_sprites[7].setPosition(_sprites[4].getX(), _sprites[4].getY()+innerHeight);
		_sprites[8].setPosition(_sprites[5].getX(), _sprites[5].getY()+innerHeight);
	}
	
	public void draw(SpriteBatch spriteBatch) {
		for(int i = 0; i < _sprites.length; i++){
			_sprites[i].draw(spriteBatch);
		}
	}

}
