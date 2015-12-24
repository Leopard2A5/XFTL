package de.xftl.game.framework;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class FrameSpriteActor extends Actor {
	private ArrayList<TextureRegion> _frames;
	private int _currentFrameIndex;
	
    public FrameSpriteActor() {
    	_currentFrameIndex = -1;
    	_frames = new ArrayList<TextureRegion>();
    }
    
    public void addFrame(TextureRegion region){
    	_frames.add(region);
    	if (_currentFrameIndex == -1) setFrame(0);
    }
    
    public void setFrame(int index) {
    	_currentFrameIndex = index;
    	
    	TextureRegion current = _frames.get(_currentFrameIndex);
    	setBounds(getX(), getY(), current.getRegionWidth(),current.getRegionHeight());
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
    	if (_currentFrameIndex == -1) return;
    	
        Color color = getColor();
        
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        
        batch.draw(_frames.get(_currentFrameIndex), getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), 
                getScaleX(), getScaleY(), getRotation());
        
    }
}