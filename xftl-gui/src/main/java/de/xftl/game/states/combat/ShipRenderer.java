package de.xftl.game.states.combat;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

import de.xftl.game.framework.GameObject;
import de.xftl.game.framework.XftlGameRenderer;
import de.xftl.spec.model.ships.Deck;
import de.xftl.spec.model.ships.Ship;

public class ShipRenderer extends GameObject {
	private Group _shipGroup;
	private ArrayList<DeckRenderer> _decks;
	
	public ShipRenderer(Stage stage, XftlGameRenderer game, Ship ship) {
		super(game);
		
		_shipGroup = new Group();
		_shipGroup.moveBy(24.0f, 100.0f);
		stage.addActor(_shipGroup);
		
		_decks = new ArrayList<DeckRenderer>();
		for(Deck deck : ship.getDecks()) {
			DeckRenderer deckRenderer = new DeckRenderer(_shipGroup, game, deck);
			_decks.add(deckRenderer);
		}
	}
}
