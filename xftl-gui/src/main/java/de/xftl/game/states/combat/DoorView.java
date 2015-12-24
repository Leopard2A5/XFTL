package de.xftl.game.states.combat;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import de.xftl.game.framework.FrameSpriteActor;
import de.xftl.game.framework.XftlGameRenderer;
import de.xftl.spec.model.ships.Door;

public class DoorView extends FrameSpriteActor {
	private Door _door;
	
	public DoorView(XftlGameRenderer game, Door door) {
		TextureAtlas main = game.getResources().getAtlas("tex/mainAtlas.txt");
		
		addFrame(main.findRegion("door_closed"));
		addFrame(main.findRegion("door_open"));
		
		_door = door;
		
		updateDoorState();
		
		addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if (_door.isOpen()){
					_door.close();
				}
				else {
					_door.open();
				}
			}
		});
	}
	
	private void updateDoorState() {
		if (_door.isOpen())
			setFrame(1);
		else
			setFrame(0);
	}
	
	@Override
	public void act(float dt) {
		super.act(dt);
		updateDoorState();
	}

	@Override
	public Actor hit(float x, float y, boolean touchable) {
		if (x >= 0 && x <= getWidth() && y >= 0 && y <= getHeight())
			return this;

		return null;
	}
}
