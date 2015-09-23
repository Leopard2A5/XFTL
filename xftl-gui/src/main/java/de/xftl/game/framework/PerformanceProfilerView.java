package de.xftl.game.framework;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;

import de.xftl.game.framework.ui.RenderedGameObject;


public class PerformanceProfilerView extends RenderedGameObject {

	private boolean _isEnabled;
	private NinePatch _ninePatch;
	private BitmapFont _font;
	private int _drawCalls;
	private float _frameTime;
	private float _frameRateElapsedTime;
	private int _currentFrames;
	private float _currentFrameTime;
	private int _frameRate;
	private Color _fontColor;
	
	public PerformanceProfilerView(XftlGameRenderer game) {
		super(game);
		_ninePatch = new NinePatch(getResources().getTexture("tex/performanceframe.png"), 2,2,2,2);
		_font = getResources().getBitmapFont("fnt/debug.fnt");
		_fontColor = new Color(1, 1, 0, 1);
	}
	
	public void update(float elapsedTime) {
		if (!_isEnabled) return;
		
		_drawCalls = getSpriteBatch().renderCalls;
		
		_currentFrameTime = elapsedTime;
		_currentFrameTime *= 0.5f;
		
		_currentFrames++;
		_frameRateElapsedTime += elapsedTime;
		if (_frameRateElapsedTime >= 1.0f) {
			_frameRateElapsedTime -= 1.0f;
			_frameRate = _currentFrames;
			_currentFrames = 0;
			_frameTime = _currentFrameTime;
		}
	}
	
	public void toggleIsEnabled() {
		_isEnabled = !_isEnabled;
	}
	
	public void setIsEnabled(boolean isTrue) {
		_isEnabled = isTrue;
	}
	
	@Override
	public void draw() {
		
		if (!_isEnabled) return;
		
		getSpriteBatch().begin();
		_ninePatch.draw(getSpriteBatch(), 1024-200, 0, 200, 40);
		
		_font.setColor(_fontColor);
		_font.draw(getSpriteBatch(), "Framerate:   " + _frameRate, 1024-190, 4);
		_font.draw(getSpriteBatch(), "Frametime:   " + String.format("%.2f", _frameTime * 1000.0f) + " ms", 1024-190, 14);
		_font.draw(getSpriteBatch(), "Draw Calls:  " + _drawCalls, 1024-190, 24);
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
