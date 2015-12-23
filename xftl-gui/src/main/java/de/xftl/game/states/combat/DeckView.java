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
import de.xftl.spec.model.ships.Room;
import de.xftl.spec.model.ships.Tile;
import de.xftl.spec.model.ships.TileOrRoomConnector;

public class DeckView extends GameObject {

	private Group _deckGroup;
	
	private ArrayList<Door> _alldoors;
	
	public DeckView(Group group, XftlGameRenderer game, Deck deck) {
		super(game);
		
		_alldoors = new ArrayList<Door>();
		
		_deckGroup = new Group();
		group.addActor(_deckGroup);
		
		ArrayList<Actor> fringe = new ArrayList<Actor>();
		
		for(Room room : deck.getRooms()) {
			for(Tile tile : room.getTiles()) {
				
				Point<Integer> pos = tile.getLeftUpperCornerPos();
				
				Texture texture = getResources().getTexture("tex/floor.png");
				SpriteActor tileActor = new SpriteActor(new TextureRegion(texture));
				tileActor.setPosition((pos.getX()-1) * ViewConstants.TILESIZE, pos.getY() * ViewConstants.TILESIZE);
				_deckGroup.addActor(tileActor);
								
				handleNeighbour(tile.getNorthNeighbor(), tileActor, Direction.NORTH, fringe);
				handleNeighbour(tile.getSouthNeighbor(), tileActor, Direction.SOUTH, fringe);
				handleNeighbour(tile.getEastNeighbor(), tileActor, Direction.EAST, fringe);
				handleNeighbour(tile.getWestNeighbor(), tileActor, Direction.WEST, fringe);
			}
		}
		
		for(Actor actor : fringe) {
			_deckGroup.addActor(actor);
		}
	}
	
	public Group getGroup() {
		return _deckGroup;
	}

	private void handleNeighbour(TileOrRoomConnector tile, Actor currentActor, Direction direction, ArrayList<Actor> connectors) {
		
		if (tile == null) {
			
			SpriteActor wall = new SpriteActor(new TextureRegion(getGame().getBlankTexture()));
			boolean vertical = direction == Direction.WEST || direction == Direction.EAST;
			
			if (vertical) {
				wall.setSize(ViewConstants.WALLTHICKNESS, ViewConstants.TILESIZE + ViewConstants.WALLOFFSET * 2);
			}
			else {
				wall.setSize(ViewConstants.TILESIZE + ViewConstants.WALLOFFSET * 2, ViewConstants.WALLTHICKNESS);
			}
			
			float posX = currentActor.getX() + getOffsetXForDirection(direction);
			float posY = currentActor.getY() + getOffsetYForDirection(direction);
			
			wall.setPosition(posX - ViewConstants.WALLOFFSET, posY-ViewConstants.WALLOFFSET);
			wall.setColor(0.0f, 0.0f, 0.0f, 1.0f);
		
			connectors.add(wall);	
		}
		else if (tile instanceof Door)
		{
			if (!_alldoors.contains(tile)) {
				_alldoors.add((Door)tile);
				
				DoorView doorView = new DoorView(getGame(), (Door)tile);
				
				float x = currentActor.getX() + getOffsetXForDirection(direction);
				float y = currentActor.getY() + getOffsetYForDirection(direction);
				
				doorView.setPosition(x,y);
				connectors.add(doorView);
			}
		}
	}
	
	private float getOffsetYForDirection(Direction direction)
	{	
		switch(direction)
		{
		case SOUTH:
			return ViewConstants.TILESIZE;
		default:
			return 0.0f;
		}
	}
	
	private float getOffsetXForDirection(Direction direction)
	{	
		switch(direction)
		{
		case EAST:
			return ViewConstants.TILESIZE;
		default:
			return 0.0f;
		}
	}
}