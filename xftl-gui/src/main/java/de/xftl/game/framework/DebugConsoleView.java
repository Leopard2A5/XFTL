package de.xftl.game.framework;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;

import de.xftl.game.framework.ui.RenderedGameObject;

public class DebugConsoleView extends RenderedGameObject implements InputProcessor  {

	private CommandInterpreter _intepreter;
	private boolean _isEnabled;
	private String _currentEnteredText;
	private final float BORDER = 2;
	private final float PADDING = 1;
	private Color _fontColor;
	private BitmapFont _consoleFont;
	private NinePatch _ninePatch; 
	private float _width;
	private float _height;
	private ArrayList<String> _consoleLines;
	
	public DebugConsoleView(XftlGameRenderer game, CommandInterpreter interpreter) {
		super(game);
		
		_intepreter = interpreter;
		_consoleFont = getResources().getBitmapFont("fnt/debug.fnt");
		_fontColor = new Color(1.0f, 1.0f, 1.0f, 1.0f);
		_ninePatch = new NinePatch(getResources().getTexture("tex/consoleframe.png"), (int)BORDER, (int)BORDER, 22, (int)BORDER);
		_ninePatch.setColor(new Color(1.0f, 1.0f, 1.0f, 0.5f));
		_width = 400;
		_height = 200;
		_consoleLines = new ArrayList<String>();
		_consoleLines.add("console started");
		_currentEnteredText = "";
		
		Gdx.input.setInputProcessor(this);
	}
	
	public void setEnable(boolean isTrue) {
		_isEnabled = isTrue;
	}
	
	@Override
	public void draw() {
		
		if (!_isEnabled) return;
		
		getSpriteBatch().begin();
		_ninePatch.draw(getSpriteBatch(), getX(), getY(), _width, _height);
		_consoleFont.setColor(_fontColor);
		
		float startX = getX() + BORDER + PADDING;
		float startY = getY() + BORDER * PADDING;
		
		for(String text : _consoleLines) {
			_consoleFont.draw(getSpriteBatch(), text, startX, startY);
			startY += _consoleFont.getLineHeight();
		}
		
		_consoleFont.draw(getSpriteBatch(), _currentEnteredText + "_", getX() + BORDER + PADDING, getY() + _height - 2 * BORDER - 2*PADDING - 10); 
		
		getSpriteBatch().end();
	}

	private void appendConsoleLine(String line) {
		_consoleLines.add(line);
		if (_consoleLines.size() > 11) {
			_consoleLines.remove(0);
		}
	}
	
	@Override
	public boolean keyTyped(char character) {
		if (!_isEnabled) return false;
		
		if (character == '\b' && _currentEnteredText.length() > 0) {
			_currentEnteredText = _currentEnteredText.substring(0, _currentEnteredText.length()-1);
		}
		else if (character == '\r' && _currentEnteredText.length() > 0)
		{
			invokeCommand();
		}
		else
		{
			if (_currentEnteredText.length() < 50) _currentEnteredText += Character.toString(character);
		}
		
		return false;
	}
	
	private void invokeCommand() {
		
		if (_currentEnteredText.equalsIgnoreCase("clear")) {
			_consoleLines.clear();
		}
		else if (_currentEnteredText.equalsIgnoreCase("exit")) {
			setEnable(false);
		}
		else
		{
			appendConsoleLine(_currentEnteredText);
			String response = _intepreter.interpretCommand(_currentEnteredText);
			if (response != null && response.length() > 0) {
				appendConsoleLine(response);	
			}
		}
		
		_currentEnteredText = "";
	}
	
	@Override
	public float getX() { return 0;	}

	@Override
	public float getY() { return 0;	}

	@Override
	public boolean keyDown(int keycode) { return false; }

	@Override
	public boolean keyUp(int keycode) { return false; }

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) { return false; }

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }

	@Override
	public boolean mouseMoved(int screenX, int screenY) { return false; }

	@Override
	public boolean scrolled(int amount) { return false; }

}
