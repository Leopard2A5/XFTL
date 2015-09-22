package de.xftl.game.framework;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameObject {
	
	private XftlGameRenderer _game;
	
	protected GameObject(XftlGameRenderer game){
		_game = game;
	}
	
	public XftlGameRenderer getGame() {
		return _game;
	}
	
	public ResourceManager getResources() {
		return _game.getResources();
	}
	
	public void update(float elapsedTime) {
		
	}
	
	public SpriteBatch getSpriteBatch() {
		return _game.getSpriteBatch();
	}
}
