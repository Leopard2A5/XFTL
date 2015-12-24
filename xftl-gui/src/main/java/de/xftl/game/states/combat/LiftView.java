package de.xftl.game.states.combat;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import de.xftl.game.framework.FrameSpriteActor;
import de.xftl.game.framework.XftlGameRenderer;
import de.xftl.spec.model.ships.Lift;

public class LiftView extends FrameSpriteActor {

	private Lift _lift;
	
	public LiftView(XftlGameRenderer game, Lift lift) {
		super();
		
		_lift = lift;
		
		TextureAtlas main = game.getResources().getAtlas("tex/mainAtlas.txt");
		
		addFrame(main.findRegion("lift_closed"));
		addFrame(main.findRegion("lift_open"));
		
		addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if (_lift.isOpen()){
					_lift.close();
				}
				else {
					_lift.open();
				}
			}
		});
		
		updateLiftState();
	}
	
	private void updateLiftState(){
		setFrame(_lift.isOpen() ? 1 : 0);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		updateLiftState();
	}

	@Override
	public Actor hit(float x, float y, boolean touchable) {
		if (x >= 0 && x <= getWidth() && y >= 0 && y <= getHeight())
			return this;

		return null;
	}
}
