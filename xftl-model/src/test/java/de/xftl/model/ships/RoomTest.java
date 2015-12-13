package de.xftl.model.ships;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.xftl.spec.model.ships.Deck;
import de.xftl.spec.model.ships.Room;
import de.xftl.spec.model.ships.Ship;
import de.xftl.spec.model.ships.Tile;

public class RoomTest {

	private Ship ship = new BasicShip();
	private Deck deck = new BasicDeck(ship, 1);
	private Room room = new BasicRoom(2, 1, 0, 0);
	private Tile tile = room.getTiles().get(0);
	
    @Before
    public void setUp() {
    	deck.addRoom(room);
	}
    
    @Test
    public void shouldReplenishAndConsumeOxygen() {
    	assertEquals(1.0f, room.getOxygenLevel(), 0.05f);
    	
    	room.consumeOxygen(0.5f);
    	assertThat(room.getOxygenLevel()).isCloseTo(0.5f, within(0.05f));
    	
    	room.replenishOxygen(0.5f);
    	assertThat(room.getOxygenLevel()).isCloseTo(1.0f, within(0.05f));
    }
    
	@Test
	public void shouldNotReplenishOxygenWhenOnFire() {
		assertEquals(1.0f, room.getOxygenLevel(), 0.05f);
		room.consumeOxygen(0.5f);
		
		tile.ignite();
		room.replenishOxygen(0.5f);
		
		assertThat(room.getOxygenLevel()).isCloseTo(0.5f, within(0.05f));
	}

}
