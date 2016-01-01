package de.xftl.model.game;

import static de.xftl.spec.model.Direction.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.assertj.core.data.Offset;
import org.junit.Before;
import org.junit.Test;

import de.xftl.model.crew.Human;
import de.xftl.model.ships.BasicDeck;
import de.xftl.model.ships.BasicDoor;
import de.xftl.model.ships.BasicRoom;
import de.xftl.model.ships.BasicShip;
import de.xftl.model.ships.BasicTile;
import de.xftl.spec.game.Game;
import de.xftl.spec.game.MovementPreview;
import de.xftl.spec.model.Point;
import de.xftl.spec.model.crew.CrewMember;
import de.xftl.spec.model.crew.Movement;
import de.xftl.spec.model.ships.Deck;
import de.xftl.spec.model.ships.Door;
import de.xftl.spec.model.ships.Room;
import de.xftl.spec.model.ships.Ship;

public class GameWaypointPlanningTestMultiway {

	/*
	 * Room layout:
	 *     r3       r7
	 *  r1 r2 r4  r6  r8
	 *     r5                
	 *    _     _            
	 *  _|_|_ _|_|_               
	 * |_|_|_|___|_|
	 *   |_|                    
	 */
	
	private final Game _game = new BasicGame();
	private final Ship _ship = new BasicShip();
	private final Deck _deck = new BasicDeck(_ship, 1);
	private final Room _room1 = new BasicRoom("1", 1, 1, 0, 1);
	private final Room _room2 = new BasicRoom("2", 1, 1, 1, 1);
	private final Room _room3 = new BasicRoom("3", 1, 1, 1, 0);
	private final Room _room4 = new BasicRoom("4", 1, 1, 2, 1);
	private final Room _room5 = new BasicRoom("5", 1, 1, 1, 2);
	private final Room _room6 = new BasicRoom("6", 2, 1, 3, 1);
	private final Room _room7 = new BasicRoom("7", 1, 1, 4, 0);
	private final Room _room8 = new BasicRoom("8", 1, 1, 5, 1);
	
	private final BasicTile _tile_r1 = (BasicTile) _room1.getTiles().get(0);
	private final BasicTile _tile_r2 = (BasicTile) _room2.getTiles().get(0);
	private final BasicTile _tile_r3 = (BasicTile) _room3.getTiles().get(0);
	private final BasicTile _tile_r4 = (BasicTile) _room4.getTiles().get(0);
	private final BasicTile _tile_r5 = (BasicTile) _room5.getTiles().get(0);
	private final BasicTile _tile_r6_t1 = (BasicTile) _room6.getTiles().get(0);
	private final BasicTile _tile_r6_t2 = (BasicTile) _room6.getTiles().get(1);
	private final BasicTile _tile_r7 = (BasicTile) _room7.getTiles().get(0);
	private final BasicTile _tile_r8 = (BasicTile) _room8.getTiles().get(0);
	
	private final CrewMember _crew = new Human(_tile_r5);
	
	@Before
	public void setup() {
		_deck.addRoom(_room1);
		_deck.addRoom(_room2);
		_deck.addRoom(_room3);
		_deck.addRoom(_room4);
		_deck.addRoom(_room5);
		_deck.addRoom(_room6);
		_deck.addRoom(_room7);
		_deck.addRoom(_room8);
		
		final Door door_r1_r2 = new BasicDoor();
		door_r1_r2.addRoom(_room1);
		door_r1_r2.addRoom(_room2);
		_tile_r1.addNeighbor(EAST, door_r1_r2);
		_tile_r2.addNeighbor(WEST, door_r1_r2);
		
		final Door door_r3_r2 = new BasicDoor();
		door_r3_r2.addRoom(_room3);
		door_r3_r2.addRoom(_room2);
		_tile_r3.addNeighbor(SOUTH, door_r3_r2);
		_tile_r2.addNeighbor(NORTH, door_r3_r2);
		
		final Door door_r5_r2 = new BasicDoor();
		door_r5_r2.addRoom(_room5);
		door_r5_r2.addRoom(_room2);
		_tile_r5.addNeighbor(NORTH, door_r5_r2);
		_tile_r2.addNeighbor(SOUTH, door_r5_r2);
		
		final Door door_r4_r2 = new BasicDoor();
		door_r4_r2.addRoom(_room4);
		door_r4_r2.addRoom(_room2);
		_tile_r4.addNeighbor(WEST, door_r4_r2);
		_tile_r2.addNeighbor(EAST, door_r4_r2);
		
		final Door door_r4_r6 = new BasicDoor();
		door_r4_r6.addRoom(_room4);
		door_r4_r6.addRoom(_room6);
		_tile_r4.addNeighbor(EAST, door_r4_r6);
		_tile_r6_t1.addNeighbor(WEST, door_r4_r6);
		
		final Door door_r6_r7 = new BasicDoor();
		door_r6_r7.addRoom(_room6);
		door_r6_r7.addRoom(_room7);
		_tile_r6_t2.addNeighbor(NORTH, door_r6_r7);
		_tile_r7.addNeighbor(SOUTH, door_r6_r7);
		
		final Door door_r6_r8 = new BasicDoor();
		door_r6_r8.addRoom(_room6);
		door_r6_r8.addRoom(_room8);
		_tile_r6_t2.addNeighbor(EAST, door_r6_r8);
		_tile_r8.addNeighbor(WEST, door_r6_r8);
		
		_game.selectCrewMembers(Arrays.asList(_crew));
	}
	
	@Test
	public void shouldNavigateToRoom8() {
		final MovementPreview preview = _game.previewMovement(_tile_r8);
		_game.move(preview);
		
		assertThat(_crew.getMovement()).isPresent();
		final Movement movement = _crew.getMovement().get();
		assertThat(movement.getTargetTile()).isEqualTo(_tile_r8);
		
		final Point<Float> wp1 = movement.getCurrentWaypoint();
		checkWP(wp1, 1.5f, 1.5f);
		movement.waypointReached();
		
		final Point<Float> wp2 = movement.getCurrentWaypoint();
		checkWP(wp2, 2.5f, 1.5f);
		movement.waypointReached();
		
		final Point<Float> wp3 = movement.getCurrentWaypoint();
		checkWP(wp3, 3.5f, 1.5f);
		movement.waypointReached();
		
		final Point<Float> wp4 = movement.getCurrentWaypoint();
		checkWP(wp4, 4.5f, 1.5f);
		movement.waypointReached();
		
		final Point<Float> wp5 = movement.getCurrentWaypoint();
		checkWP(wp5, 5.5f, 1.5f);
		movement.waypointReached();
		assertThat(movement.isEmpty()).isTrue();
	}
	
	private void checkWP(final Point<Float> point,
			             final float x,
			             final float y) {
		assertThat(point.getX()).isEqualTo(x, Offset.offset(0.05f));
		assertThat(point.getY()).isEqualTo(y, Offset.offset(0.05f));
	}

	// TODO elevators
	
}
