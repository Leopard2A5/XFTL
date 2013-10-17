package de.xftl.game.framework;

public interface GameScreen {
	void onEnter();
	void onUpdate(float elapsedTime);
	void onRender();
	void onLeave();
}
