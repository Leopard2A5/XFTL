package de.xftl.game.states;

import de.xftl.game.framework.ScreenChangeInformation;
import de.xftl.game.framework.XftlGameRenderer;
import de.xftl.game.framework.ui.UiGameScreenBase;
import de.xftl.game.states.combat.ShipRenderer;
import de.xftl.spec.game.Game;

public class CombatScreen extends UiGameScreenBase {

	private ShipRenderer _shipRenderer;
	
	public CombatScreen(XftlGameRenderer game) {
		super(game);
	}
	
	@Override
	public ScreenChangeInformation onUpdate(float elapsedTime) {
		super.onUpdate(elapsedTime);
		
		return ScreenChangeInformation.emtpy;
	}

	@Override
	public void onRender() {
		getGame().clearScreen(1.0f, 1.0f, 1.0f);
		_shipRenderer.draw();
		super.onRender();
	}

	@Override
	public void onEnter(Object enterInformation) {
		Game model = getGame().getGameModel();
		model.startNewGame(null);
		
		_shipRenderer = new ShipRenderer(getGame(), model.getShip());
		_shipRenderer.setPosition(30, 60);
	}

	@Override
	public void onLeave() {
	}

}
