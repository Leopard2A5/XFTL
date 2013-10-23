package de.xftl.game.states.combat;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import de.xftl.game.framework.XftlGameRenderer;
import de.xftl.game.framework.ui.RenderedGameObject;

public class EnergyRenderer extends RenderedGameObject {

	private float _x;
	private float _y;
	private float _height;
	private NinePatch _ninePatch;
	private Sprite _energyLevelSprite;
	
	private final float TOPBORDER = 24;
	private final float SMALLBORDER = 4;
	
	public EnergyRenderer(XftlGameRenderer game) {
		super(game);
		
		_ninePatch = new NinePatch(getResources().getTexture("res/tex/energycontainer.png"), 4, 4, 4, 24);
		_energyLevelSprite = new Sprite(getGame().getBlankTexture());
		_energyLevelSprite.setColor(new Color(0.0f, 0.8f, 0.0f, 1.0f));
		_energyLevelSprite.setSize(22, 100);	
		_height = 200;
	}
	
	public void setHeight(float height) {
		_height = height;
	}
	
	@Override
	public void setPosition(float x, float y) {
		_x = x;
		_y = y;
	}
	
	@Override
	public void draw() {
		_ninePatch.draw(getSpriteBatch(), _x, _y, 32, _height);
		
		setPerctentageSize(0.5f);
		_energyLevelSprite.draw(getSpriteBatch());
	}
	
	private void setPerctentageSize(float percentage) {
		
		float totalHeight = _height - TOPBORDER - SMALLBORDER - 2;
		float currentHeight = (float) Math.floor((totalHeight * percentage));
		float startY = _y + TOPBORDER + 1 + (totalHeight - currentHeight);
		
		_energyLevelSprite.setPosition(_x + SMALLBORDER + 1, startY);
		_energyLevelSprite.setSize(32 - (SMALLBORDER+1)*2, currentHeight);		
	}

	@Override
	public float getX() {
		return _x;
	}

	@Override
	public float getY() {
		return _y;
	}

}
