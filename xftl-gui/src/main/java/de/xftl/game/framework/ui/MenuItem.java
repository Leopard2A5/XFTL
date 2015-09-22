package de.xftl.game.framework.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import de.xftl.game.framework.BitmapFontSprite;
import de.xftl.game.framework.XftlGameRenderer;

public class MenuItem extends UiElement {
	
	public interface MenuItemClickListener {
		void onClick();
	}
	
	private MenuItemClickListener _listener;
	private Color _normalColor;
	private Color _activeColor;
	private Color _disabledColor;
	private boolean _isEnabled;
	private BitmapFontSprite _bitmapFontSprite;
	
	public MenuItem(XftlGameRenderer game, String text, float x, float y, boolean isEnabled) {
		super(game);
		_isEnabled = isEnabled;
		_bitmapFontSprite = new BitmapFontSprite(getResources().getBitmapFont("fnt/main.fnt"), text);
		_bitmapFontSprite.setPosition(x, y);
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
		boolean isMouseOver = _bitmapFontSprite.getBounds().contains(mouseX, mouseY);
		
		if (!_isEnabled) {
			_bitmapFontSprite.setColor(_disabledColor);
		}
		else if (isMouseOver) {
			_bitmapFontSprite.setColor(_activeColor);
		}
		else {
			_bitmapFontSprite.setColor(_normalColor);
		}
		
		if (_isEnabled && getGame().getMouse().isLeftButtonDownOnce() && isMouseOver && _listener != null) {
			_listener.onClick();
		}
	}
	
	public void draw() {
		_bitmapFontSprite.draw(getSpriteBatch());
	}
}
