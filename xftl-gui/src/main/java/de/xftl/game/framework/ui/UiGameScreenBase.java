package de.xftl.game.framework.ui;

import java.util.ArrayList;

import de.xftl.game.framework.GameScreenBase;
import de.xftl.game.framework.ScreenChangeInformation;
import de.xftl.game.framework.XftlGameRenderer;

public abstract class UiGameScreenBase extends GameScreenBase {

	private ArrayList<UiElement> _uiElements;
	private Cursor _cursor;
	
	protected UiGameScreenBase(XftlGameRenderer game) {
		super(game);
		_uiElements = new ArrayList<UiElement>();
		_cursor = new Cursor(game);
	}
	
	protected <T extends UiElement> T addUiElement(T uiElement) {
		_uiElements.add(uiElement);
		return uiElement;
	}
	
	@Override
	public ScreenChangeInformation onUpdate(float elapsedTime) {
		_cursor.update();
		for(UiElement element : _uiElements) {
			element.update(elapsedTime);
		}
		
		return ScreenChangeInformation.emtpy;
	}
	
	protected void redrawCursor() {
		_cursor.draw();
	}

	@Override
	public void onRender() {
		for(UiElement element : _uiElements) {
			element.draw();
		}		
		_cursor.draw();
	}
}
