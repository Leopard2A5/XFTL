package de.xftl.game.states;

import com.badlogic.gdx.graphics.g2d.Sprite;

import de.xftl.game.framework.ScreenChangeInformation;
import de.xftl.game.framework.XftlGameRenderer;
import de.xftl.game.framework.ui.UiGameScreenBase;
import de.xftl.game.states.combat.ShipRenderer;
import de.xftl.spec.game.Game;

public class CombatScreen extends UiGameScreenBase {

	private Sprite _backgroundSprite;
	private ShipRenderer _shipRenderer;
	
	public CombatScreen(XftlGameRenderer game) {
		super(game);
	}
	
	@Override
	public ScreenChangeInformation onUpdate(float elapsedTime) {
		super.onUpdate(elapsedTime);
		_shipRenderer.update(elapsedTime);
		return ScreenChangeInformation.emtpy;
	}

	@Override
	public void onRender() {
		_backgroundSprite.draw(getSpriteBatch());
		_shipRenderer.draw();
		super.onRender();
	}

	@Override
	public void onEnter(Object enterInformation) {
		Game model = getGame().getGameModel();
		model.startNewGame(null);
		
		_shipRenderer = new ShipRenderer(getGame(), model.getShip());
		_shipRenderer.setPosition(30, 60);
		
		_backgroundSprite = new Sprite(getResources().getTexture("tex/starbackground.png"));
		_backgroundSprite.flip(false, true);
	}

	@Override
	public void onLeave() {
	}

}
