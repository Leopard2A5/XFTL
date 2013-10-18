package de.xftl.game.framework;

public class ScreenChangeInformation {
	private GameScreenName _gameScreen;
	private Object _enterInformation;
	
	public ScreenChangeInformation(GameScreenName screenName, Object enterInformation) {
		_gameScreen = screenName;
		_enterInformation = enterInformation;
	}
	
	public final static ScreenChangeInformation emtpy = new ScreenChangeInformation(GameScreenName.None, null);
	
	public GameScreenName getGameScreenName() {
		return _gameScreen;
	}
	
	public Object getEnterInformation() {
		return _enterInformation;
	}
}
