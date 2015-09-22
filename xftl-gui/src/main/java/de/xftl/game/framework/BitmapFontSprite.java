package de.xftl.game.framework;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class BitmapFontSprite {
	
	private Rectangle _rectangle;
	private HorizontalTextAlign _horizontalAlign;
	private VerticalTextAlign _verticalAlign;
	private BitmapFont _bitmapFont;
	private Color _color;
	private String _text;
	private float _x;
	private float _y;
	private TextBounds _bounds;
	
	public BitmapFontSprite(BitmapFont font, String text){
		_bitmapFont = font;
		_color = new Color(1,1,1,1);
		_horizontalAlign = HorizontalTextAlign.Left;
		_verticalAlign = VerticalTextAlign.Top;
		_rectangle = new Rectangle();
		
		setText(text);
	}
	
	public void setText(String text) {
		_text = text;
		_bounds = _bitmapFont.getBounds(_text);
		_rectangle.width = _bounds.width;
		_rectangle.height = _bounds.height;
	}
	
	public void setPosition(float x, float y) {
		_x = x;
		_y = y;
		_rectangle.x = x;
		_rectangle.y = y;
	} 
	
	public void setHorizontalAlign(HorizontalTextAlign align) {
		_horizontalAlign = align;
	}
	
	public void setVerticalAlign(VerticalTextAlign align) {
		_verticalAlign = align;
	}
	
	public void setColor(Color color) {
		_color = color;
	}
	
	public Rectangle getBounds() {
		return _rectangle;
	}
	
	public void draw(SpriteBatch spriteBatch) {
		
		float x = _x;
		float y = _y;
		
		switch(_horizontalAlign) {
			case Center:
				x -= _bounds.width * 0.5f;
				break;
			case Right:
				x -= _bounds.width;
				break;
		}
		
		switch(_verticalAlign) {
		case Middle:
			y -= _bounds.height * 0.5f;
			break;
		case Bottom:
			y -= _bounds.height;
			break;
		}
		
		_bitmapFont.setColor(_color);
		_bitmapFont.draw(spriteBatch, _text, (float)Math.floor(x), (float)Math.floor(y));
	}
}
