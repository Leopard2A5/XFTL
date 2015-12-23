package de.xftl.game.states.combat;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import de.xftl.game.framework.GameObject;
import de.xftl.game.framework.SpriteActor;
import de.xftl.game.framework.ViewConstants;
import de.xftl.game.framework.XftlGameRenderer;
import de.xftl.spec.model.Direction;
import de.xftl.spec.model.Point;
import de.xftl.spec.model.ships.Deck;
import de.xftl.spec.model.ships.Door;
import de.xftl.spec.model.ships.Lift;
import de.xftl.spec.model.ships.Room;
import de.xftl.spec.model.ships.Tile;
import de.xftl.spec.model.ships.TileOrRoomConnector;

public class DeckView extends GameObject {

	private Group _wallAndFringeLayerGroup;
	private Group _groundLayerGroup;
	
	private ArrayList<Door> _alldoors;
	private ArrayList<Lift> _allLifts;
	
	public DeckView(Group group, XftlGameRenderer game, Deck deck) {
		super(game);
		
		_alldoors = new ArrayList<Door>();
		_allLifts = new ArrayList<Lift>();
		
		_wallAndFringeLayerGroup = new Group();
		_groundLayerGroup = new Group();
		group.addActor(_groundLayerGroup);
		group.addActor(_wallAndFringeLayerGroup);
		
		for(Room room : deck.getRooms()) {
			for(Tile tile : room.getTiles()) {
				
				Point<Integer> pos = tile.getLeftUpperCornerPos();
				
				Texture texture = getResources().getTexture("tex/floor.png");
				SpriteActor tileActor = new SpriteActor(new TextureRegion(texture));
				tileActor.setPosition((pos.getX()-1) * ViewConstants.TILESIZEF, pos.getY() * ViewConstants.TILESIZEF);
				_groundLayerGroup.addActor(tileActor);
								
				handleNeighbour(tile.getNorthNeighbor(), tileActor, Direction.NORTH);
				handleNeighbour(tile.getSouthNeighbor(), tileActor, Direction.SOUTH);
				handleNeighbour(tile.getEastNeighbor(), tileActor, Direction.EAST);
				handleNeighbour(tile.getWestNeighbor(), tileActor, Direction.WEST);
			}
		}
	}
	
	public void moveBy(float x, float y){
		_wallAndFringeLayerGroup.moveBy(x, y);
		_groundLayerGroup.moveBy(x, y);
	}

	private void handleNeighbour(TileOrRoomConnector tile, Actor currentActor, Direction direction) {
		
		boolean vertical = direction == Direction.WEST || direction == Direction.EAST;
		float posX = currentActor.getX() + getOffsetXForDirection(direction);
		float posY = currentActor.getY() + getOffsetYForDirection(direction);
		
		if (tile == null) {
			_wallAndFringeLayerGroup.addActor(createWall(posX, posY, vertical));
		}
		else if (tile instanceof Door && !_alldoors.contains(tile))
		{
			_alldoors.add((Door)tile);
			_wallAndFringeLayerGroup.addActor(createDoor((Door)tile, posX, posY, vertical));
		}
		else if (tile instanceof Lift && !_allLifts.contains(tile)) {
			_allLifts.add((Lift)tile);
			_groundLayerGroup.addActor(createLift((Lift)tile, posX, posY, direction));
		}
	}
	
	private Actor createWall(float posX, float posY, Boolean vertical) {
		SpriteActor wall = new SpriteActor(new TextureRegion(getGame().getBlankTexture()));
		
		if (vertical) {
			wall.setSize(ViewConstants.WALLTHICKNESS, ViewConstants.TILESIZEF + ViewConstants.WALLOFFSET * 2);
		}
		else {
			wall.setSize(ViewConstants.TILESIZEF + ViewConstants.WALLOFFSET * 2, ViewConstants.WALLTHICKNESS);
		}
		wall.setPosition(posX - ViewConstants.WALLOFFSET, posY-ViewConstants.WALLOFFSET);
		wall.setColor(0.0f, 0.0f, 0.0f, 1.0f);
	
		return wall;
	}
	
	private Actor createDoor(Door door, float posX, float posY, Boolean vertical){
		DoorView doorView = new DoorView(getGame(), door);
		
		if (vertical) {
			posX += ViewConstants.WALLTHICKNESS;
		} else {
			posY -= ViewConstants.WALLTHICKNESS;
		}
		
		doorView.setPosition(posX,posY);
		if (vertical){
			doorView.setRotation(90);
		}
		
		return doorView;
	}
	
	private Actor createLift(Lift lift, float posX, float posY, Direction direction) {
		LiftView liftView = new LiftView(getGame(), lift);
		liftView.setOrigin(ViewConstants.TILESIZEF * 0.5f,
						   ViewConstants.TILESIZEF * 0.5f);
		
		switch(direction)
		{
		case EAST:
			posX += ViewConstants.TILESIZEF + ViewConstants.WALLTHICKNESS;
			liftView.setRotation(-90);
			break;
		case NORTH:
			posY -= ViewConstants.TILESIZEF - ViewConstants.WALLTHICKNESS;
			liftView.setRotation(180);
			break;
		case SOUTH:
			posY += ViewConstants.TILESIZEF + ViewConstants.WALLTHICKNESS;
			break;
		case WEST:
			liftView.setRotation(90);
			posX -= ViewConstants.TILESIZEF - ViewConstants.WALLTHICKNESS;
			break;
		}
		
		liftView.setPosition(posX, posY);
		liftView.setZIndex(5);
		
		return liftView;
	}
	
	private float getOffsetYForDirection(Direction direction)
	{	
		return direction == Direction.SOUTH ? ViewConstants.TILESIZEF : 0.0f;
	}
	
	private float getOffsetXForDirection(Direction direction)
	{	
		return direction == Direction.EAST ? ViewConstants.TILESIZEF : 0.0f;
	}
}