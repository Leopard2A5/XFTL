package de.xftl.game.framework;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class SpriteActor extends Actor {
	
	private TextureRegion _region;
	
    public SpriteActor(TextureRegion region) {
    	_region = region;
    	setBounds(0.0f, 0.0f, _region.getRegionWidth(),_region.getRegionHeight());
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        Color color = getColor();
        
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);

        batch.draw(_region, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), 
                getScaleX(), getScaleY(), getRotation());
        
    }
}