package de.xftl.game.framework;

public class ScreenChangeInformation {
	private GameScreenName _gameScreen;
	private Object _enterInformation;
	private boolean _quitGame;
	
	public ScreenChangeInformation(GameScreenName screenName, Object enterInformation, boolean quit) {
		_gameScreen = screenName;
		_enterInformation = enterInformation;
		_quitGame = quit;
	}
	
	public ScreenChangeInformation(GameScreenName screenName, Object enterInformation) {
		this(screenName, enterInformation, false);
	}
	
	public final static ScreenChangeInformation emtpy = new ScreenChangeInformation(GameScreenName.None, null);
	public final static ScreenChangeInformation quitGame = new ScreenChangeInformation(GameScreenName.None, null, true);
	
	public GameScreenName getGameScreenName() {
		return _gameScreen;
	}
	
	public Object getEnterInformation() {
		return _enterInformation;
	}
	
	public boolean getQuitGame() {
		return _quitGame;
	}
}
