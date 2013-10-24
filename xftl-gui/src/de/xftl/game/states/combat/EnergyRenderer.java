package de.xftl.game.states.combat;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import de.xftl.game.framework.XftlGameRenderer;
import de.xftl.game.framework.ui.RenderedGameObject;
import de.xftl.spec.model.systems.EnergyManager;

public class EnergyRenderer extends RenderedGameObject {

	private float _x;
	private float _y;
	private float _height;
	private NinePatch _ninePatch;
	private ArrayList<Sprite> _energySprites;
	private int _energyLevel;
	
	private final float TOPBORDER = 24;
	private final float SMALLBORDER = 4;
	private final float SEGMENTHEIGHT = 10;
	private final float SEGMENTPADDING = 1;
	private final float SEGMENTHEIGHTWITHPADDING = SEGMENTHEIGHT + SEGMENTPADDING;
	
	private EnergyManager _energymanager;
	
	public EnergyRenderer(XftlGameRenderer game, EnergyManager energyManager) {
		super(game);
				
		_energymanager = energyManager;
		_ninePatch = new NinePatch(getResources().getTexture("res/tex/energycontainer.png"), 4, 4, 4, 24);
		
		_energySprites = new ArrayList<Sprite>(energyManager.getMaxEnergy().intValue());
		for(int i = 0; i < energyManager.getMaxEnergy().intValue(); i++) {
			_energySprites.add(new Sprite(getGame().getBlankTexture()));
		}
		
		int segmentCount = _energymanager.getMaxEnergy().intValue();
		
		float innerHeight = segmentCount * (SEGMENTHEIGHTWITHPADDING) + SEGMENTPADDING;
		_height = TOPBORDER + SMALLBORDER + innerHeight;
		
		float currentY = _y + TOPBORDER + innerHeight - SEGMENTHEIGHTWITHPADDING;
		for(int i = 0; i < segmentCount; i++) {
			
			_energySprites.get(i).setPosition(_x + SMALLBORDER + SEGMENTPADDING, currentY);
			_energySprites.get(i).setSize(32 - (SMALLBORDER+SEGMENTPADDING) * 2, SEGMENTHEIGHT);
			currentY -= SEGMENTHEIGHTWITHPADDING;
		}
				
		setColor(new Color(0.0f, 0.8f, 0.0f, 1.0f));
	}
	
	private void setColor(Color color) {
		for(Sprite sprite : _energySprites){
			sprite.setColor(color);
		}
	}
	
	@Override
	public void setPosition(float x, float y) {
		float diffX = x - _x;
		float diffY = y - _y;
		_x = x;
		_y = y;
		
		for(Sprite sprite : _energySprites){
			sprite.translate(diffX, diffY);
		}
	}
	
	@Override
	public void update(float elapsedTime) {
		
		_energyLevel = _energymanager.getMaxEnergy().intValue() - _energymanager.getConsumedEnergy().intValue();
		float percentage =_energyLevel / (float)_energymanager.getMaxEnergy().intValue();
		
		if (percentage < 0.1f)  {
			setColor(new Color(0.8f, 0.0f, 0.0f, 1.0f));
		}
		else if (percentage < 0.5f) {
			setColor(new Color(0.8f, 0.8f, 0.0f, 1.0f));
		}
		else {
			setColor(new Color(0.0f, 0.8f, 0.0f, 1.0f));
		}
	}
	
	@Override
	public void draw() {
		_ninePatch.draw(getSpriteBatch(), _x, _y, 32, _height);
		
		for(int i = 0; i < _energyLevel; i++){
			_energySprites.get(i).draw(getSpriteBatch());
		}
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
