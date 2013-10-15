package de.xftl.game.framework;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
	
	public NinePatchSprite(TextureRegion textureRegion, int leftBorder, int topBorder, int rightBorder, int bottomBorder) {
		_sprites = new Sprite[9];
		
		_width = textureRegion.getRegionWidth();
		_height = textureRegion.getRegionHeight();
		int scaleWidth = (int)_width - leftBorder - rightBorder;
		int scaleHeight = (int)_height - topBorder - bottomBorder;
		
		int startY = 0;
		int endY = topBorder;
		for(int i = 0; i < 3; i++)
		{
			_sprites[i*3+0] = new Sprite(textureRegion, 0, startY, leftBorder, endY);
			_sprites[i*3+1] = new Sprite(textureRegion, leftBorder, startY, scaleWidth, endY);
			_sprites[i*3+2] = new Sprite(textureRegion, (int) (_width-rightBorder), startY, rightBorder, endY);
			
			if (startY == 0)
			{
				startY += topBorder;
				endY += topBorder + scaleHeight; 
			}
			else if (startY == 1)
			{
				startY += scaleHeight;
				endY += bottomBorder;
			}
		}
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
