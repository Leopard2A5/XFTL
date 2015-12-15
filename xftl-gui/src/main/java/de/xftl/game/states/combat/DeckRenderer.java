package de.xftl.game.states.combat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

import de.xftl.game.framework.GameObject;
import de.xftl.game.framework.SpriteActor;
import de.xftl.game.framework.XftlGameRenderer;
import de.xftl.spec.model.Direction;
import de.xftl.spec.model.Point;
import de.xftl.spec.model.ships.Deck;
import de.xftl.spec.model.ships.Room;
import de.xftl.spec.model.ships.Tile;
import de.xftl.spec.model.ships.TileOrRoomConnector;

public class DeckRenderer extends GameObject {

	private final int WALLTHICKNESS = 4;
	private final int OFFSET = 2;

//	private ArrayList<DoorRenderer> _doors;
//	private ArrayList<LiftRenderer> _lifts;
	
	private Group _deckGroup;
	
	public DeckRenderer(Group group, XftlGameRenderer game, Deck deck) {
		super(game);
		
//		_doors = new ArrayList<DoorRenderer>();
//		_lifts = new ArrayList<LiftRenderer>();
		_deckGroup = new Group();
		group.addActor(_deckGroup);
		
		for(Room room : deck.getRooms()) {
			for(Tile tile : room.getTiles()) {
				
				Point<Integer> pos = tile.getLeftUpperCornerPos();
				
				Texture texture = getResources().getTexture("tex/floor.png");
				SpriteActor tileActor = new SpriteActor(new TextureRegion(texture));
				tileActor.setPosition(pos.getX() * game.TileSize, pos.getY() * game.TileSize);
				_deckGroup.addActor(tileActor);
								
				handleNeighbour(tile.getNorthNeighbor(), tileActor.getX(), tileActor.getY(), Direction.NORTH);
				handleNeighbour(tile.getSouthNeighbor(), tileActor.getX(), tileActor.getY(), Direction.SOUTH);
				handleNeighbour(tile.getEastNeighbor(), tileActor.getX(), tileActor.getY(), Direction.EAST);
				handleNeighbour(tile.getWestNeighbor(), tileActor.getX(), tileActor.getY(), Direction.WEST);
			}
		}
	}
	
//	private boolean doorExists(Door door) {
//		for(DoorRenderer doorRenderer : _doors) {
//			if (doorRenderer.getDoor() == door) return true;
//		}
//		
//		return false;
//	}
	
//	private boolean liftExists(Lift lift) {
//		for(LiftRenderer doorRenderer : _lifts) {
//			if (doorRenderer.getLift() == lift) return true;
//		}
//	
//		return false;
//	}
	
	private void handleNeighbour(TileOrRoomConnector tile, float x, float y, Direction direction) {
		
		if (tile == null) {
			
			SpriteActor wall = new SpriteActor(new TextureRegion(getGame().getBlankTexture()));
			boolean vertical = direction == Direction.WEST || direction == Direction.EAST;
			
			if (vertical) {
				wall.setSize(WALLTHICKNESS, getGame().TileSize + OFFSET * 2);
			}
			else {
				wall.setSize(getGame().TileSize + OFFSET * 2, WALLTHICKNESS);
			}
			
			float posX = x;// + getOffsetXForDirection(direction);
			float posY = y;// + getOffsetYForDirection(direction);
			
			wall.setPosition(posX - OFFSET, posY-OFFSET);
			wall.setColor(0.0f, 0.0f, 0.0f, 1.0f);
			
			_deckGroup.addActor(wall);			
		}
//		else if (tile instanceof Door) {
//			if (!doorExists((Door)tile)) {
//				DoorRenderer doorRenderer = new DoorRenderer(getGame(), (Door)tile, x, y, direction);
//				addChild(doorRenderer);
//				_doors.add(doorRenderer);
//			}
//		}
//		else if (tile instanceof Lift) {
//			if (!liftExists((Lift)tile)) {
//				LiftRenderer liftRenderer = new LiftRenderer(getGame(), (Lift)tile,x,y, direction);
//				_lifts.add(liftRenderer);
//				addChild(liftRenderer);
//			}
		}
	}
	
	
