package de.xftl.model.ships;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.xftl.spec.model.ships.Deck;
import de.xftl.spec.model.ships.Room;
import de.xftl.spec.model.ships.Ship;
import de.xftl.spec.model.ships.Tile;

public class RoomTest {

	Ship ship = new BasicShip();
    Deck deck = new BasicDeck(ship, 1);
    Room room = new BasicRoom(2, 1, 0, 0);
    Tile tile = room.getTiles().get(0);
	
    @Before
    public void setUp() {
    	deck.addRoom(room);
	}
    
    @Test
    public void shouldReplenishAndConsumeOxygen() {
    	assertEquals(1.0f, room.getOxygenLevel(), 0.05f);
    	
    	room.consumeOxygen(0.5f);
    	assertEquals(0.5f, room.getOxygenLevel(), 0.05f);
    	
    	room.replenishOxygen(0.5f);
    	assertEquals(1f, room.getOxygenLevel(), 0.05f);
    }
    
	@Test
	public void shouldNotReplenishOxygenWhenOnFire() {
		assertEquals(1.0f, room.getOxygenLevel(), 0.05f);
		room.consumeOxygen(0.5f);
		
		tile.ignite();
		room.replenishOxygen(0.5f);
		
		assertEquals(0.5f, room.getOxygenLevel(), 0.05f);
	}

}