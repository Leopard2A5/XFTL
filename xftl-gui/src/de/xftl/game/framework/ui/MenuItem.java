package de.xftl.game.framework.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.math.Rectangle;

import de.xftl.game.framework.XftlGameRenderer;

public class MenuItem extends UiElement {
	
	public interface MenuItemClickListener {
		void onClick();
	}
	
	private BitmapFont _font;
	private String _text;
	private float _x;
	private float _y;
	private Rectangle _rect;
	private MenuItemClickListener _listener;
	private Color _color;
	
	public MenuItem(XftlGameRenderer game, String text, float x, float y) {
		super(game);
		_font = getResources().getBitmapFont("res/fnt/main.fnt");
		_text = text;
		_x = x;
		_y = y;
		TextBounds bounds = _font.getBounds(_text);
		_rect = new Rectangle(_x, _y, bounds.width, bounds.height);
		_color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
	}
	
	public void setMenuItemClickListener(MenuItemClickListener listener) {
		_listener = listener;
	}

	public void update(float elapsedTime) {
		int mouseX = Gdx.input.getX();
		int mouseY = Gdx.input.getY();
		
		if (_rect.contains(mouseX, mouseY)) {
			_color.set(1.0f, 1.0f, 0.0f, 1.0f);
		}
		else {
			_color.set(1.0f, 1.0f, 1.0f, 1.0f);
		}
		
		if (getGame().getMouse().isLeftButtonDownOnce() && _listener != null) {
			_listener.onClick();
		}
	}
	
	public void draw() {
		_font.setColor(_color);
		_font.draw(getSpriteBatch(), _text, _x, _y);
	}
}
