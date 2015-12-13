package de.xftl.model.ships;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.data.Offset;
import org.junit.Before;
import org.junit.Test;

import de.xftl.spec.model.ships.Deck;
import de.xftl.spec.model.ships.Room;
import de.xftl.spec.model.ships.Ship;
import de.xftl.spec.model.ships.Tile;

public class HullbreachTest {

	private Ship ship = new BasicShip();
	private Deck deck = new BasicDeck(ship, 1);
	private Room room = new BasicRoom(2, 1, 0, 0);
	private Tile tile = room.getTiles().get(0);
	
	@Before
    public void setUp() {
    	deck.addRoom(room);
	}
	
	@Test
	public void shouldCreateHullbreach() {
		assertThat(tile.hasHullBreach()).isFalse();
		assertThat(tile.getHullBreachLevel()).isEqualTo(0f);
		
		tile.createHullBreach(0.5f);
		assertThat(tile.hasHullBreach()).isTrue();
		assertThat(tile.getHullBreachLevel()).isCloseTo(0.5f, Offset.offset(0.05f));
	}

	@Test
	public void shouldRepairHullbreach() {
		assertThat(tile.hasHullBreach()).isFalse();
		assertThat(tile.getHullBreachLevel()).isEqualTo(0f);
		
		tile.createHullBreach(0.5f);
		assertThat(tile.hasHullBreach()).isTrue();
		
		tile.repairHullBreach(0.2f);
		assertThat(tile.getHullBreachLevel()).isCloseTo(0.3f, Offset.offset(0.05f));
		
		tile.repairHullBreach(0.3f);
		assertThat(tile.hasHullBreach()).isFalse();
		assertThat(tile.getHullBreachLevel()).isCloseTo(0f, Offset.offset(0.05f));
	}
	
	@Test
	public void hullBreachShouldExtinguishFire() {
		tile.ignite();
		assertThat(tile.isOnFire()).isTrue();
		
		tile.createHullBreach(0.5f);
		assertThat(tile.isOnFire()).isFalse();
	}
}
