package de.xftl.model.game;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.assertj.core.data.MapEntry;
import org.junit.Before;
import org.junit.Test;

import de.xftl.model.crew.Human;
import de.xftl.model.ships.BasicDeck;
import de.xftl.model.ships.BasicRoom;
import de.xftl.model.ships.BasicShip;
import de.xftl.spec.game.Game;
import de.xftl.spec.game.MovementPreview;
import de.xftl.spec.model.crew.CrewMember;
import de.xftl.spec.model.ships.Deck;
import de.xftl.spec.model.ships.Room;
import de.xftl.spec.model.ships.Ship;
import de.xftl.spec.model.ships.Tile;

public class GameMovementPreviewTest {

	private final Game _game = new BasicGame();
	private final Ship _ship = new BasicShip();
	private final Deck _deck = new BasicDeck(_ship, 1);
	private final Room _room1 = new BasicRoom(2, 1, 2, 2);
	private final Room _room2 = new BasicRoom(2, 1, 0, 0);
	private final Room _room3 = new BasicRoom(1, 1, 1, 1);
	private final Tile _tile_r1_1 = _room1.getTiles().get(0);
	private final Tile _tile_r1_2 = _room1.getTiles().get(1);
	private final Tile _tile_r2_1 = _room2.getTiles().get(0);
	private final Tile _tile_r2_2 = _room2.getTiles().get(1);
	private final Tile _tile_r3_1 = _room3.getTiles().get(0);
	private final CrewMember _crew1 = new Human(_tile_r1_1);
	private final CrewMember _crew2 = new Human(_tile_r1_2);
	
	@Before
	public void setup() {
		_deck.addRoom(_room1);
		_deck.addRoom(_room2);
		_deck.addRoom(_room3);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenNoCrewMembersAreSelected() {
		_game.deselectCrewMembers();
		
		_game.previewMovement(_tile_r1_1);
	}
	
	@Test
	public void shouldCorrectlyPreviewMovementForEmptyBigEnoughRoom() {
		_game.selectCrewMembers(Arrays.asList(_crew1, _crew2));
		final MovementPreview preview = _game.previewMovement(_tile_r2_1);
		
		assertThat(preview.isAllCrewMembersAssigned()).isTrue();
		assertThat(preview.getUnassignedCrewMembers()).isEmpty();
		assertThat(preview.getAssignedCrewMembers()).containsExactly(_crew1, _crew2);
		assertThat(preview.getMovementPreviews()).containsExactly(MapEntry.entry(_crew1, _tile_r2_1),
				                                                  MapEntry.entry(_crew2, _tile_r2_2));
	}
	
	@Test
	public void shouldCorrectlyPreviewMovementForTooSmallRoom() {
		_game.selectCrewMembers(Arrays.asList(_crew1, _crew2));
		final MovementPreview preview = _game.previewMovement(_tile_r3_1);
		
		assertThat(preview.isAllCrewMembersAssigned()).isFalse();
		assertThat(preview.getUnassignedCrewMembers()).containsExactly(_crew2);
		assertThat(preview.getAssignedCrewMembers()).containsExactly(_crew1);
		assertThat(preview.getMovementPreviews()).containsExactly(MapEntry.entry(_crew1, _tile_r3_1));
	}
	
	@Test
	public void shouldCorrectlyPreviewMovementWhenAllSelectedCrewIsInCurrentRoom() {
		assertThat(_crew1.getCurrentTile()).isEqualTo(_tile_r1_1);
		assertThat(_crew2.getCurrentTile()).isEqualTo(_tile_r1_2);
		
		_game.selectCrewMembers(Arrays.asList(_crew2, _crew1));
		final MovementPreview preview = _game.previewMovement(_tile_r1_1);
		
		assertThat(preview.isAllCrewMembersAssigned()).isTrue();
		assertThat(preview.getUnassignedCrewMembers()).isEmpty();
		assertThat(preview.getAssignedCrewMembers()).containsExactly(_crew2, _crew1);
		assertThat(preview.getMovementPreviews()).containsExactly(MapEntry.entry(_crew2, _tile_r1_2),
				 												  MapEntry.entry(_crew1, _tile_r1_1));
	}

	@Test
	public void shouldOnlyPreviewMovementForCrewOnHoveredOverShip() {
		final Ship tmpShip = new BasicShip();
		final Deck tmpDeck = new BasicDeck(tmpShip, 1);
		final Room tmpRoom = new BasicRoom(1, 1, 1, 1);
		tmpDeck.addRoom(tmpRoom);
		final Tile tmpTile = tmpRoom.getTiles().get(0);
		final CrewMember tmpCrew = new Human(tmpTile);
		
		_game.selectCrewMembers(Arrays.asList(_crew1, tmpCrew));
		final MovementPreview preview = _game.previewMovement(_tile_r2_1);
		
		assertThat(preview.isAllCrewMembersAssigned()).isFalse();
		assertThat(preview.getUnassignedCrewMembers()).containsOnly(tmpCrew);
		assertThat(preview.getAssignedCrewMembers()).containsOnly(_crew1);
	}
	
}
