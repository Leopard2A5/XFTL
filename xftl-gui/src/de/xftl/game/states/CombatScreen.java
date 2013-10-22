package de.xftl.game.states;

import de.xftl.game.framework.ScreenChangeInformation;
import de.xftl.game.framework.XftlGameRenderer;
import de.xftl.game.framework.ui.UiGameScreenBase;
import de.xftl.game.states.combat.DeckRenderer;
import de.xftl.spec.game.Game;

public class CombatScreen extends UiGameScreenBase {

	private DeckRenderer _deckRenderer;
	
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
		_deckRenderer.draw();
		super.onRender();
	}

	@Override
	public void onEnter(Object enterInformation) {
		Game model = getGame().getGameModel();
		model.startNewGame(null);
		
		_deckRenderer = new DeckRenderer(getGame(), model.getShip().getDecks().get(0));
		_deckRenderer.setPosition(30, 60);
	}

	@Override
	public void onLeave() {
		// TODO Auto-generated method stub
		
	}

}
