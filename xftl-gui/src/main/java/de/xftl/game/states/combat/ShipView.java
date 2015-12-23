package de.xftl.game.states.combat;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

import de.xftl.game.framework.GameObject;
import de.xftl.game.framework.XftlGameRenderer;
import de.xftl.spec.model.ships.Deck;
import de.xftl.spec.model.ships.Ship;

public class ShipView extends GameObject {
	private Group _shipGroup;
	private ArrayList<DeckView> _decks;
	
	public ShipView(Stage stage, XftlGameRenderer game, Ship ship) {
		super(game);
		
		_shipGroup = new Group();
		stage.addActor(_shipGroup);
		
		_decks = new ArrayList<DeckView>();
		
		float verticalDistance = 0.0f;
		for(Deck deck : ship.getDecks()) {
			DeckView deckView = new DeckView(_shipGroup, game, deck);
			_decks.add(deckView);
			verticalDistance += 200.0f;
			deckView.getGroup().moveBy(64.0f, verticalDistance);
		}
	}
}
