package de.xftl.game.states.combat;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import de.xftl.game.framework.GameObject;
import de.xftl.game.framework.XftlGameRenderer;
import de.xftl.spec.model.Point;
import de.xftl.spec.model.ships.Deck;
import de.xftl.spec.model.ships.Door;
import de.xftl.spec.model.ships.Room;
import de.xftl.spec.model.ships.Tile;
import de.xftl.spec.model.ships.TileOrRoomConnector;
import de.xftl.spec.model.ships.TileUnit;

public class DeckRenderer extends GameObject {

	private Deck _deck;
	private Texture _floorTexture;
	private ArrayList<Sprite> _roomTiles;
	private ArrayList<Sprite> _wallTiles;
	private ArrayList<DoorRenderer> _doors;
	private float _x;
	private float _y;
	
	private final int WALLTHICKNESS = 4;
	private final int OFFSET = 2;
	
	public DeckRenderer(XftlGameRenderer game, Deck deck) {
		super(game);
		
		_deck = deck;
		_floorTexture = getResources().getTexture("res/tex/floor.png");
		
		_roomTiles = new ArrayList<Sprite>();
		_wallTiles = new ArrayList<Sprite>();
		_doors = new ArrayList<DoorRenderer>();
		
		float tileSize = getGame().TileSize;
		
		for(Room room : deck.getRooms()) {
			
			for(Tile tile : room.getTiles()) {
				
				Sprite sprite = new Sprite(_floorTexture);
				Point<TileUnit> pos = tile.getLeftUpperCornerPos();
				sprite.setPosition(pos.getX().getValue() * tileSize, pos.getY().getValue() * tileSize);
				_roomTiles.add(sprite);
				
				handleNeighbour(tile.getNorthNeighbor(), sprite.getX(), sprite.getY(), false);
				handleNeighbour(tile.getSouthNeighbor(), sprite.getX(), sprite.getY()+tileSize, false);
				handleNeighbour(tile.getEastNeighbor(), sprite.getX()+tileSize, sprite.getY(), true);
				handleNeighbour(tile.getWestNeighbor(), sprite.getX(), sprite.getY(), true);
				
//				if (tile.getNorthNeighbor() == null) {
//					addWallSprite(sprite.getX(), sprite.getY(), wallLength, WALLTHICKNESS, false);
//				}
//				if (tile.getSouthNeighbor() == null) {
//					addWallSprite(sprite.getX(), sprite.getY()+tileSize, wallLength, WALLTHICKNESS, false);
//				}
//				if (tile.getEastNeighbor() == null) {
//					addWallSprite(sprite.getX()+tileSize, sprite.getY(), WALLTHICKNESS, wallLength, true);
//				}
//				if (tile.getWestNeighbor() == null) {
//					addWallSprite(sprite.getX(), sprite.getY(), WALLTHICKNESS, wallLength, true);
//				}
			}
		}
	}
	
	public void handleNeighbour(TileOrRoomConnector tile, float x, float y, boolean vertical) {
		if (tile == null) {
			addWallSprite(x, y, vertical);
		}
		else if (tile instanceof Door) {
			addDoor((Door)tile, x, y, vertical);
		}
	}
	
	private void addDoor(Door door, float x, float y, boolean vertical) {
		DoorRenderer doorRenderer = new DoorRenderer(getGame(), door, vertical);
		doorRenderer.setPosition(x, y);
		_doors.add(doorRenderer);
	}
			
	private void addWallSprite(float x, float y, boolean vertical) {
		Sprite wall = new Sprite(getGame().getBlankTexture());
		
		if (vertical) {
			wall.setSize(WALLTHICKNESS, getGame().TileSize + OFFSET * 2);
		}
		else {
			wall.setSize(getGame().TileSize + OFFSET * 2, WALLTHICKNESS);
		}
		
		wall.setPosition(x-OFFSET, y-OFFSET);
		wall.setColor(0.0f, 0.0f, 0.0f, 1.0f);
		_wallTiles.add(wall);
	}
	
	public void setPosition(float x, float y) {
		float differenceX = x - _x;
		float differenceY = y - _y;
		_x = x;
		_y = y;
		for(Sprite sprite : _roomTiles) {
			sprite.setPosition(sprite.getX() + differenceX, sprite.getY() + differenceY);
		}	
		for(Sprite sprite : _wallTiles) {
			sprite.setPosition(sprite.getX() + differenceX, sprite.getY() + differenceY);
		}
		for(DoorRenderer door : _doors) {
			door.setPosition(door.getX() + differenceX, door.getY() + differenceY);
		}
	}
	
	public void draw() {
		for(Sprite sprite : _roomTiles) {
			sprite.draw(getSpriteBatch());
		}
		for(Sprite sprite : _wallTiles) {
			sprite.draw(getSpriteBatch());
		}
		for(DoorRenderer door : _doors) {
			door.draw();
		}
	}

}
