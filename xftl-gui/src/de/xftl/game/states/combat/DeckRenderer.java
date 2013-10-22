package de.xftl.game.states.combat;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import de.xftl.game.framework.XftlGameRenderer;
import de.xftl.game.framework.ui.RenderedGameObject;
import de.xftl.spec.model.Direction;
import de.xftl.spec.model.Point;
import de.xftl.spec.model.ships.Deck;
import de.xftl.spec.model.ships.Door;
import de.xftl.spec.model.ships.Lift;
import de.xftl.spec.model.ships.Room;
import de.xftl.spec.model.ships.Tile;
import de.xftl.spec.model.ships.TileOrRoomConnector;
import de.xftl.spec.model.ships.TileUnit;

public class DeckRenderer extends RenderedGameObject {

	private Texture _floorTexture;
	private ArrayList<Sprite> _roomTiles;
	private ArrayList<DoorRenderer> _doors;
	private ArrayList<LiftRenderer> _lifts;
	private float _x;
	private float _y;

	public DeckRenderer(XftlGameRenderer game, Deck deck) {
		super(game);
		
		_floorTexture = getResources().getTexture("res/tex/floor.png");
		
		_roomTiles = new ArrayList<Sprite>();
		_doors = new ArrayList<DoorRenderer>();
		_lifts = new ArrayList<LiftRenderer>();
		
		float tileSize = getGame().TileSize;
		
		for(Room room : deck.getRooms()) {
			
			for(Tile tile : room.getTiles()) {
				
				Sprite sprite = new Sprite(_floorTexture);
				Point<TileUnit> pos = tile.getLeftUpperCornerPos();
				sprite.setPosition(pos.getX().getValue() * tileSize, pos.getY().getValue() * tileSize);
				_roomTiles.add(sprite);
				
				handleNeighbour(tile.getNorthNeighbor(), sprite.getX(), sprite.getY(), Direction.NORTH);
				handleNeighbour(tile.getSouthNeighbor(), sprite.getX(), sprite.getY(), Direction.SOUTH);
				handleNeighbour(tile.getEastNeighbor(), sprite.getX(), sprite.getY(), Direction.EAST);
				handleNeighbour(tile.getWestNeighbor(), sprite.getX(), sprite.getY(), Direction.WEST);
			}
		}
	}
	
	private boolean doorExists(Door door) {
		for(DoorRenderer doorRenderer : _doors) {
			if (doorRenderer.getDoor() == door) return true;
		}
		
		return false;
	}
	
	public void handleNeighbour(TileOrRoomConnector tile, float x, float y, Direction direction) {
		
		if (tile instanceof Lift) {
			if (!liftExists((Lift)tile)) {
				LiftRenderer liftRenderer = new LiftRenderer(getGame(), (Lift)tile,x,y, direction);
				_lifts.add(liftRenderer);
				addChild(liftRenderer);
			}
		}
		else if (tile == null) {
			addChild(new WallRenderer(getGame(), x, y, direction));
		}
		else if (tile instanceof Door) {
			if (!doorExists((Door)tile)) {
				DoorRenderer doorRenderer = new DoorRenderer(getGame(), (Door)tile, x, y, direction);
				addChild(doorRenderer);
				_doors.add(doorRenderer);
			}
		}
	}
	
	private boolean liftExists(Lift lift) {
		for(LiftRenderer doorRenderer : _lifts) {
			if (doorRenderer.getLift() == lift) return true;
		}
	
		return false;
	}
			
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		
		float diffX = x - _x;
		float diffY = y - _y;
		
		_x = x;
		_y = y;
		
		for(Sprite sprite : _roomTiles) {
			sprite.setPosition(sprite.getX() + diffX, sprite.getY() + diffY);
		}
	}
	
	public void draw() {
		for(Sprite sprite : _roomTiles) {
			sprite.draw(getSpriteBatch());
		}
		super.draw();
	}

	@Override
	public float getX() {
		return _x;
	}

	@Override
	public float getY() {
		return _y;
	}

}
