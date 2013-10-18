package de.xftl.game.states.combat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import de.xftl.game.framework.GameObject;
import de.xftl.game.framework.XftlGameRenderer;
import de.xftl.spec.model.ships.Room;

public class RoomRenderer extends GameObject {

	private Room _room;
	private Texture _floorTexture;
	private Sprite[] _roomTiles;
	
	public RoomRenderer(XftlGameRenderer game, Room room) {
		super(game);
		_room = room;
		_floorTexture = getResources().getTexture("res/tex/floor.png");
		
		_roomTiles = new Sprite[12];
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 4; x++) {
				_roomTiles[y*4+x] = new Sprite(_floorTexture);
				_roomTiles[y*4+x].flip(false, true);
				_roomTiles[y*4+x].setPosition(x*32, y*32);
			}
			
		}
	}

	public void update(float elapsedTime) {
		
	}
	
	public void draw() {
		for(Sprite sprite : _roomTiles) {
			sprite.draw(getSpriteBatch());
		}
	}
}
