package de.xftl.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.xftl.game.framework.GameStateBase;
import de.xftl.game.framework.NinePatchSprite;
import de.xftl.game.framework.XftlGame;

public class TestGameState extends GameStateBase {

	private NinePatchSprite _frameSprite;
	
	public TestGameState(XftlGame game) {
		super(game);
		
		Texture texture = getGame().getTexture("res/tex/testframe.png");
		_frameSprite = new NinePatchSprite(new TextureRegion(texture), 4, 32, 4, 4);
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
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		SpriteBatch spriteBatch = getGame().getSpriteBatch();
		
		spriteBatch.begin();
		_frameSprite.draw(spriteBatch);
		spriteBatch.end();
	}

	@Override
	public void onLeave() {
		// TODO Auto-generated method stub
		
	}

}
