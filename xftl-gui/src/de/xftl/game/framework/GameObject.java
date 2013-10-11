package de.xftl.game.framework;

public class GameObject {
	
	private XftlGame _game;
	
	protected GameObject(XftlGame game){
		_game = game;
	}
	
	public XftlGame getGame() {
		return _game;
	}
}
