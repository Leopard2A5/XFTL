package de.xftl.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.xftl.game.framework.GameStateBase;
import de.xftl.game.framework.NinePatchSprite;
import de.xftl.game.framework.XftlGameRenderer;

public class TestGameState extends GameStateBase {

	private NinePatchSprite _frameSprite1;
	private NinePatchSprite _frameSprite2;
	private NinePatchSprite _frameSprite3;
	
	public TestGameState(XftlGameRenderer game) {
		super(game);
		
		Texture texture = getGame().getTexture("res/tex/testframe.png");
		_frameSprite1 = new NinePatchSprite(texture, 4, 32, 4, 4);
		_frameSprite2 = new NinePatchSprite(texture, 4, 32, 4, 4);
		_frameSprite3 = new NinePatchSprite(texture, 4, 32, 4, 4);
		_frameSprite1.setSize(1000, 700);
		_frameSprite2.setSize(400, 300);
		_frameSprite3.setSize(100, 600);
		
		_frameSprite1.setPosition(12, 34);
		_frameSprite2.setPosition(24, 80);
		_frameSprite3.setPosition(800, 120);
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
		_frameSprite1.draw(spriteBatch);
		_frameSprite2.draw(spriteBatch);
		_frameSprite3.draw(spriteBatch);
		spriteBatch.end();
	}

	@Override
	public void onLeave() {
		// TODO Auto-generated method stub
		
	}

}
