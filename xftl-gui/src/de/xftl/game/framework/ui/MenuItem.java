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
	private Color _currentColor;
	private Color _normalColor;
	private Color _activeColor;
	private Color _disabledColor;
	private boolean _isEnabled;
	
	public MenuItem(XftlGameRenderer game, String text, float x, float y, boolean isEnabled) {
		super(game);
		_isEnabled = isEnabled;
		_font = getResources().getBitmapFont("res/fnt/main.fnt");
		_text = text;
		_x = x;
		_y = y;
		TextBounds bounds = _font.getBounds(_text);
		_rect = new Rectangle(_x, _y, bounds.width, bounds.height);
		_normalColor = new Color(1.0f, 1.0f, 1.0f, 1.0f);
		_activeColor = new Color(1.0f, 1.0f, 0.0f, 1.0f);
		_disabledColor = new Color(0.6f, 0.6f, 0.6f, 1.0f);
	}
	
	public MenuItem(XftlGameRenderer game, String text, float x, float y) {
		this(game, text, x, y, true);
	}
	
	public void setIsEnabled(boolean isEnabled) {
		_isEnabled = isEnabled;
	}
	
	public void setMenuItemClickListener(MenuItemClickListener listener) {
		_listener = listener;
	}

	public void update(float elapsedTime) {
		int mouseX = Gdx.input.getX();
		int mouseY = Gdx.input.getY();
		boolean isMouseOver = _rect.contains(mouseX, mouseY);
		
		if (!_isEnabled) {
			_currentColor = _disabledColor;
		}
		else if (isMouseOver) {
			_currentColor = _activeColor;
		}
		else {
			_currentColor = _normalColor;
		}
		
		if (getGame().getMouse().isLeftButtonDownOnce() && isMouseOver && _listener != null) {
			_listener.onClick();
		}
	}
	
	public void draw() {
		_font.setColor(_currentColor);
		_font.draw(getSpriteBatch(), _text, _x, _y);
	}
}
