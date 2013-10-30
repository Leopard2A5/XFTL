package de.xftl.game.framework;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;

import de.xftl.game.framework.ui.RenderedGameObject;

public class DebugConsoleView extends RenderedGameObject{

	private final float BORDER = 2;
	private final float PADDING = 1;
	private Color _fontColor;
	private BitmapFont _consoleFont;
	private NinePatch _ninePatch; 
	private float _width;
	private float _height;
	private ArrayList<String> _consoleLines;
	
	public DebugConsoleView(XftlGameRenderer game) {
		super(game);
		_consoleFont = getResources().getBitmapFont("res/fnt/debug.fnt");
		_fontColor = new Color(1.0f, 1.0f, 1.0f, 1.0f);
		_ninePatch = new NinePatch(getResources().getTexture("res/tex/consoleframe.png"), (int)BORDER, (int)BORDER, 22, (int)BORDER);
		_ninePatch.setColor(new Color(1.0f, 1.0f, 1.0f, 0.5f));
		_width = 400;
		_height = 200;
		_consoleLines = new ArrayList<String>();
		_consoleLines.add("console enabled");
		_consoleLines.add("have fun!");
		_consoleLines.add("console enabled");
		_consoleLines.add("have fun!");
		_consoleLines.add("console enabled");
		_consoleLines.add("have fun!");
		_consoleLines.add("console enabled");
		_consoleLines.add("have fun!");
	}
	
	@Override
	public void draw() {
		
		getSpriteBatch().begin();
		_ninePatch.draw(getSpriteBatch(), getX(), getY(), _width, _height);
		_consoleFont.setColor(_fontColor);
		
		float startX = getX() + BORDER + PADDING;
		float startY = getY() + BORDER * PADDING;
		
		for(String text : _consoleLines) {
			_consoleFont.draw(getSpriteBatch(), text, startX, startY);
			startY += _consoleFont.getLineHeight();
		}
		getSpriteBatch().end();
	}

	@Override
	public float getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getY() {
		// TODO Auto-generated method stub
		return 0;
	}

}
