package de.xftl.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;

import de.xftl.game.framework.ScreenChangeInformation;
import de.xftl.game.framework.XftlGameRenderer;
import de.xftl.game.framework.ui.UiGameScreenBase;
import de.xftl.game.states.combat.RoomRenderer;

public class CombatScreen extends UiGameScreenBase {

	private RoomRenderer _roomRenderer;
	
	public CombatScreen(XftlGameRenderer game) {
		super(game);
	}
	
	@Override
	public ScreenChangeInformation onUpdate(float elapsedTime) {
		super.onUpdate(elapsedTime);
		_roomRenderer.update(elapsedTime);
		
		return ScreenChangeInformation.emtpy;
	}

	@Override
	public void onRender() {
		
		Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		_roomRenderer.draw();
		super.onRender();
	}

	@Override
	public void onEnter(Object enterInformation) {
		_roomRenderer = new RoomRenderer(getGame(), null);
	}

	@Override
	public void onLeave() {
		// TODO Auto-generated method stub
		
	}

}
