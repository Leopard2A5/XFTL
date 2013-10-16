package de.xftl.game.framework;

public class GameObject {
	
	private XftlGameRenderer _game;
	
	protected GameObject(XftlGameRenderer game){
		_game = game;
	}
	
	public XftlGameRenderer getGame() {
		return _game;
	}
}
