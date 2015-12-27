package de.xftl.model.game;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Test;

import de.xftl.model.crew.Human;
import de.xftl.model.ships.BasicRoom;
import de.xftl.spec.game.Game;
import de.xftl.spec.model.crew.CrewMember;
import de.xftl.spec.model.ships.Room;
import de.xftl.spec.model.ships.Tile;

public class GameCrewSelectionTest {

	private Game _game = new BasicGame();
	private Room _room = new BasicRoom(2, 2, 2, 2);
	private Tile _tile1 = _room.getTiles().get(0);
	private Tile _tile2 = _room.getTiles().get(1);
	private CrewMember _crew1 = new Human(_tile1);
	private CrewMember _crew2 = new Human(_tile2);
	
	@Test
	public void shouldAllowSelectionOfCrewMembers() {
		assertThat(_game.getSelectedCrewMembers()).isEmpty();
		
		_game.selectCrewMembers(Arrays.asList(_crew1, _crew2));
		assertThat(_game.getSelectedCrewMembers()).containsExactly(_crew1, _crew2);
	}
	
	@Test
	public void shouldDeselectSelectedCrewBeforeSettingANewSelection() {
		assertThat(_game.getSelectedCrewMembers()).isEmpty();
		
		_game.selectCrewMembers(Arrays.asList(_crew1));
		_game.selectCrewMembers(Arrays.asList(_crew2));
		
		assertThat(_game.getSelectedCrewMembers()).containsExactly(_crew2);
	}
	
	@Test
	public void shouldDeselectCrewMembers() {
		assertThat(_game.getSelectedCrewMembers()).isEmpty();
		
		_game.selectCrewMembers(Arrays.asList(_crew1));
		_game.deselectCrewMembers();
		
		assertThat(_game.getSelectedCrewMembers()).isEmpty();
	}

}
