package de.xftl.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.xftl.game.framework.GameStateBase;
import de.xftl.game.framework.XftlGameRenderer;

public class MainMenuState extends GameStateBase {
	
	protected MainMenuState(XftlGameRenderer game) {
		super(game); 
	}

	@Override
	public void onEnter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdate(float elapsedTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRender() {
		SpriteBatch spriteBatch = getGame().getSpriteBatch();
		
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		spriteBatch.begin();
		spriteBatch.end();		
	}

	@Override
	public void onLeave() {
		// TODO Auto-generated method stub
		
	}

}
