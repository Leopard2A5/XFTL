package de.xftl.game.framework.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.xftl.game.framework.GameObject;
import de.xftl.game.framework.XftlGameRenderer;

public class Cursor extends GameObject {

	private Sprite _sprite;
	
	public Cursor(XftlGameRenderer game) {
		super(game);
		
		TextureRegion region = getResources().getAtlas("tex/mainAtlas.txt").findRegion("cursor");
		_sprite = new Sprite(region);
		_sprite.flip(false, true);
	}
	
	public void update() {
		_sprite.setPosition(Gdx.input.getX(), Gdx.input.getY());
	}
	
	public void draw() {
		_sprite.draw(getSpriteBatch());
	}

}
