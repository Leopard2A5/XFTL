package de.xftl.model.crew;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.data.Offset;
import org.junit.Test;

import de.xftl.model.ships.BasicRoom;
import de.xftl.model.ships.BasicTile;
import de.xftl.spec.model.Point;
import de.xftl.spec.model.crew.CrewMember;

public class AbstractCrewMemberTest {
	
	private BasicRoom room = new BasicRoom(1, 1, 1, 2);
	private BasicTile tile = (BasicTile) room.getTiles().get(0);
	private CrewMember crew = new Human(tile);
	
	@Test
	public void shouldInitializeCorrectly() {
		assertThat(crew.getCurrentTile()).isEqualTo(tile);
		
		final Point<Float> tileFloatPos = new Point<>(Float.valueOf(tile.getLeftUpperCornerPos().getX()),
				                                      Float.valueOf(tile.getLeftUpperCornerPos().getY()));
		assertThat(crew.getLeftUpperCornerPos().getX()).isCloseTo(tileFloatPos.getX(), Offset.offset(0.05f));
		assertThat(crew.getLeftUpperCornerPos().getY()).isCloseTo(tileFloatPos.getY(), Offset.offset(0.05f));
	}

}
