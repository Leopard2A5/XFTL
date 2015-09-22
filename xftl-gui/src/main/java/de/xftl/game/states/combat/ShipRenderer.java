package de.xftl.game.states.combat;

import java.util.ArrayList;

import de.xftl.game.framework.XftlGameRenderer;
import de.xftl.game.framework.ui.RenderedGameObject;
import de.xftl.spec.model.ships.Deck;
import de.xftl.spec.model.ships.Ship;

public class ShipRenderer extends RenderedGameObject {

	private Ship _ship;
	private ArrayList<DeckRenderer> _decks;
	private EnergyRenderer _energyRenderer;
	
	public ShipRenderer(XftlGameRenderer game, Ship ship) {
		super(game);
		_ship = ship;
		_decks = new ArrayList<DeckRenderer>();
		
		for(Deck deck : ship.getDecks()) {
			DeckRenderer deckRenderer = new DeckRenderer(game, deck);
			addChild(deckRenderer);
			_decks.add(deckRenderer);
		}
		
		_energyRenderer = new EnergyRenderer(game, ship.getEnergyManager());
		_energyRenderer.setPosition(0, 400);
		addChild(_energyRenderer);
	}
	
	@Override
	public void setPosition(float x, float y) {
		float currentY = y;
		for(DeckRenderer deck : _decks) {
			deck.setPosition(x, currentY);
			currentY += deck.getSizeY() + 2 * getGame().TileSize;
		}
	}
	
	@Override
	public float getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getY() {
		// TODO Auto-generated method stub
		return 0;
	}

}
