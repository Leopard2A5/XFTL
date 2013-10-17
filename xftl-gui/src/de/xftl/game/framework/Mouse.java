package de.xftl.game.framework;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;

public class Mouse {
	
	private boolean _wasLeftButtonDown;
	private boolean _isLeftButtonDown;
	
	public Mouse() {
		copyCurrentToLastInputStates();
	}
	
	private void copyCurrentToLastInputStates(){
		_wasLeftButtonDown = _isLeftButtonDown;
	}

	public void update() {
		copyCurrentToLastInputStates();
		_isLeftButtonDown = Gdx.input.isButtonPressed(Buttons.LEFT);
	}
	
	public boolean isLeftButtonDown() {
		return _isLeftButtonDown;
	}
	
	public boolean isLeftButtonDownOnce() {
		return !_wasLeftButtonDown && _isLeftButtonDown;
	}
}

