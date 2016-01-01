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

public class GameWaypointPlanningTest {

	/*
	 * Room layout:      r6 r5
	 *                  
	 *               r1 r2
	 *                     r3 r4
	 *     _ _ _
	 *    |_|_| |
	 *  __ _  | |
	 * |__| |_|_|
	 *    |_|___|
	 */
	
	private final Game _game = new BasicGame();
	private final Ship _ship = new BasicShip();
	private final Deck _deck = new BasicDeck(_ship, 1);
	private final Room _room1 = new BasicRoom("1", 2, 1, 0, 2);
	private final Room _room2 = new BasicRoom("2", 1, 2, 2, 2);
	private final Room _room3 = new BasicRoom("3", 2, 1, 3, 3);
	private final Room _room4 = new BasicRoom("4", 1, 3, 4, 0);
	private final Room _room5 = new BasicRoom("5", 1, 1, 3, 0);
	private final Room _room6 = new BasicRoom("6", 1, 1, 2, 0);
	private final BasicTile _tile_r1_1 = (BasicTile) _room1.getTiles().get(0);
	private final BasicTile _tile_r1_2 = (BasicTile) _room1.getTiles().get(1);
	private final BasicTile _tile_r2_1 = (BasicTile) _room2.getTiles().get(0);
	private final BasicTile _tile_r2_2 = (BasicTile) _room2.getTiles().get(1);
	private final BasicTile _tile_r3_1 = (BasicTile) _room3.getTiles().get(0);
	private final BasicTile _tile_r3_2 = (BasicTile) _room3.getTiles().get(1);
	private final BasicTile _tile_r4_1 = (BasicTile) _room4.getTiles().get(0);
	private final BasicTile _tile_r4_3 = (BasicTile) _room4.getTiles().get(2);
	private final BasicTile _tile_r5 = (BasicTile) _room5.getTiles().get(0);
	private final BasicTile _tile_r6 = (BasicTile) _room6.getTiles().get(0);
	private final CrewMember _crew = new Human(_tile_r1_1);
	
	@Before
	public void setup() {
		_deck.addRoom(_room1);
		_deck.addRoom(_room2);
		_deck.addRoom(_room3);
		_deck.addRoom(_room4);
		_deck.addRoom(_room5);
		_deck.addRoom(_room6);
		
		final Door door_r1_r2 = new BasicDoor();
		door_r1_r2.addRoom(_room1);
		door_r1_r2.addRoom(_room2);
		_tile_r1_2.addNeighbor(EAST, door_r1_r2);
		_tile_r2_1.addNeighbor(WEST, door_r1_r2);
		
		final Door door_r2_r3 = new BasicDoor();
		door_r2_r3.addRoom(_room2);
		door_r2_r3.addRoom(_room3);
		_tile_r2_2.addNeighbor(EAST, door_r2_r3);
		_tile_r3_1.addNeighbor(WEST, door_r2_r3);
		
		final Door door_r3_r4 = new BasicDoor();
		door_r3_r4.addRoom(_room3);
		door_r3_r4.addRoom(_room4);
		_tile_r3_2.addNeighbor(NORTH, door_r3_r4);
		_tile_r4_3.addNeighbor(SOUTH, door_r3_r4);
		
		final Door door_r4_r5 = new BasicDoor();
		door_r4_r5.addRoom(_room4);
		door_r4_r5.addRoom(_room5);
		_tile_r4_1.addNeighbor(WEST, door_r4_r5);
		_tile_r5.addNeighbor(EAST, door_r4_r5);
		
		final Door door_r5_r6 = new BasicDoor();
		door_r5_r6.addRoom(_room5);
		door_r5_r6.addRoom(_room6);
		_tile_r5.addNeighbor(WEST, door_r5_r6);
		_tile_r6.addNeighbor(EAST, door_r5_r6);
		
		_game.selectCrewMembers(Arrays.asList(_crew));
	}
	
	@Test
	public void shouldCorrectlyPlanWaypointsInCurrentRoom() {
		final MovementPreview preview = _game.previewMovement(_tile_r1_2);
		_game.move(preview);
		
		assertThat(_crew.getMovement()).isPresent();
		final Movement movement = _crew.getMovement().get();
		assertThat(movement.getTargetTile()).isEqualTo(_tile_r1_1);
		
		final Point<Float> wp = movement.getCurrentWaypoint();
		assertThat(wp.getX()).isEqualTo(0.5f, Offset.offset(0.05f));
		assertThat(wp.getY()).isEqualTo(2.5f, Offset.offset(0.05f));
		
		movement.waypointReached();
		assertThat(movement.isEmpty()).isTrue();
	}
	
	@Test
	public void shouldNavigateToRoom5() {
		final MovementPreview preview = _game.previewMovement(_tile_r5);
		_game.move(preview);
		
		assertThat(_crew.getMovement()).isPresent();
		final Movement movement = _crew.getMovement().get();
		assertThat(movement.getTargetTile()).isEqualTo(_tile_r5);
		
		final Point<Float> wp1 = movement.getCurrentWaypoint();
		checkWP(wp1, 1.5f, 2.5f);
		movement.waypointReached();
		
		final Point<Float> wp2 = movement.getCurrentWaypoint();
		checkWP(wp2, 2.5f, 2.5f);
		movement.waypointReached();
		
		final Point<Float> wp3 = movement.getCurrentWaypoint();
		checkWP(wp3, 2.5f, 3.5f);
		movement.waypointReached();
		
		final Point<Float> wp4 = movement.getCurrentWaypoint();
		checkWP(wp4, 3.5f, 3.5f);
		movement.waypointReached();
		
		final Point<Float> wp5 = movement.getCurrentWaypoint();
		checkWP(wp5, 4.5f, 3.5f);
		movement.waypointReached();
		
		final Point<Float> wp6 = movement.getCurrentWaypoint();
		checkWP(wp6, 4.5f, 2.5f);
		movement.waypointReached();
		
		final Point<Float> wp7 = movement.getCurrentWaypoint();
		checkWP(wp7, 4.5f, 0.5f);
		movement.waypointReached();
		
		final Point<Float> wp8 = movement.getCurrentWaypoint();
		checkWP(wp8, 3.5f, 0.5f);
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
