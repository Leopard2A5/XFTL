package de.xftl.game.framework;

public interface GameScreen {
	void onEnter(Object enterInformation);
	ScreenChangeInformation onUpdate(float elapsedTime);
	void onRender();
	void onLeave();
}
