package de.xftl.model.ships;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import de.xftl.model.crew.Human;
import de.xftl.spec.model.crew.CrewMember;

public class ShipCrewTest {

	private BasicShip ship = new BasicShip();
	private BasicRoom room = new BasicRoom(1, 1, 0, 0);
	private BasicTile tile = (BasicTile) room.getTiles().get(0);
	private CrewMember crew = new Human(tile);
	
	@Test
	public void shouldAddCrew() {
		assertThat(ship.getCrew()).isEmpty();
		
		ship.addCrewMember(crew);
		assertThat(ship.getCrew()).hasSize(1);
		assertThat(ship.getCrew()).containsOnly(crew);
	}
	
	@Test
	public void shouldRemoveCrew() {
		ship.addCrewMember(crew);
		assertThat(ship.getCrew()).hasSize(1);
		
		ship.removeCrewMember(crew);
		assertThat(ship.getCrew()).isEmpty();
	}

}
