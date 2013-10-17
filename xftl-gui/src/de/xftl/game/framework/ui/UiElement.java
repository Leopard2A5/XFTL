package de.xftl.game.framework.ui;

import de.xftl.game.framework.GameObject;
import de.xftl.game.framework.XftlGameRenderer;

public abstract class UiElement extends GameObject {

	protected UiElement(XftlGameRenderer game) {
		super(game);
		// TODO Auto-generated constructor stub
	}
	
	public abstract void update(float elapsedTime);
	public abstract void draw();	
}
