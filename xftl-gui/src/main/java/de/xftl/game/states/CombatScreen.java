package de.xftl.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import de.xftl.game.framework.GameScreenBase;
import de.xftl.game.framework.ScreenChangeInformation;
import de.xftl.game.framework.XftlGameRenderer;
import de.xftl.game.states.combat.ShipRenderer;
import de.xftl.spec.game.Game;

public class CombatScreen extends GameScreenBase 
{
	private Stage _stage;
	private ShipRenderer _shipRenderer;
	
	public CombatScreen(XftlGameRenderer game) {
		super(game);
	}
	
	@Override
	public ScreenChangeInformation onUpdate(float elapsedTime) {	
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		_stage.act(elapsedTime);
		return ScreenChangeInformation.emtpy;
	}

	@Override
	public void onRender() {
		_stage.draw();
	}

	@Override
	public void onEnter(Object enterInformation) {
		_stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(_stage);
		Game model = getGame().getGameModel();
		model.startNewGame(null);
		_shipRenderer = new ShipRenderer(_stage, getGame(), model.getShip());
	}

	@Override
	public void onLeave() {
	}

}
