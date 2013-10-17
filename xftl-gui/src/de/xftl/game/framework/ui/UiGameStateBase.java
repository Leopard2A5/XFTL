package de.xftl.game.framework.ui;

import java.util.ArrayList;

import de.xftl.game.framework.GameStateBase;
import de.xftl.game.framework.XftlGameRenderer;

public class UiGameStateBase extends GameStateBase {

	private ArrayList<UiElement> _uiElements;
	
	protected UiGameStateBase(XftlGameRenderer game) {
		super(game);
		_uiElements = new ArrayList<UiElement>();
	}
	
	protected void addUiElement(UiElement uiElement) {
		_uiElements.add(uiElement);
	}
	
	@Override
	public void onEnter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdate(float elapsedTime) {
		for(UiElement element : _uiElements) {
			element.update(elapsedTime);
		}
	}

	@Override
	public void onRender() {
		for(UiElement element : _uiElements) {
			element.draw();
		}		
	}

	@Override
	public void onLeave() {
		// TODO Auto-generated method stub
		
	}

}
