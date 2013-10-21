package de.xftl.game.states.combat;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import de.xftl.game.framework.GameObject;
import de.xftl.game.framework.XftlGameRenderer;
import de.xftl.spec.model.Point;
import de.xftl.spec.model.ships.Deck;
import de.xftl.spec.model.ships.Room;
import de.xftl.spec.model.ships.Tile;
import de.xftl.spec.model.ships.TileUnit;

public class DeckRenderer extends GameObject {

	private Deck _deck;
	private Texture _floorTexture;
	private ArrayList<Sprite> _roomTiles;
	private float _x;
	private float _y;
	
	public DeckRenderer(XftlGameRenderer game, Deck deck) {
		super(game);
		
		_deck = deck;
		_floorTexture = getResources().getTexture("res/tex/floor.png");
		
		_roomTiles = new ArrayList<Sprite>();
		
		float tileSize = getGame().TileSize;
		
		for(Room room : deck.getRooms()) {
			Point<TileUnit> upperLeftCorner = room.getLeftUpperCornerPos();
			float baseX = upperLeftCorner.getX().getValue() * tileSize;
			float baseY = upperLeftCorner.getY().getValue() * tileSize;
			
			for(Tile tile : room.getTiles()) {
				Sprite sprite = new Sprite(_floorTexture);
				Point<TileUnit> pos = tile.getLeftUpperCornerPos();
				sprite.setPosition(baseX + pos.getX().getValue() * tileSize, baseY + pos.getY().getValue() * tileSize);
				_roomTiles.add(sprite);
			}
		}
	}
	
	public void setPosition(float x, float y) {
		float differenceX = x - _x;
		float differenceY = y - _y;
		_x = x;
		_y = y;
		for(Sprite sprite : _roomTiles) {
			sprite.setPosition(sprite.getX() + differenceX, sprite.getY() + differenceY);
		}		
	}
	
	public void draw() {
		for(Sprite sprite : _roomTiles) {
			sprite.draw(getSpriteBatch());
		}
	}

}
