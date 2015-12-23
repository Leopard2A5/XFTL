package de.xftl.model.ships;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.junit.Assert.assertEquals;

import org.assertj.core.data.Offset;
import org.junit.Before;
import org.junit.Test;

public class RoomTest {

	private BasicShip ship = new BasicShip();
	private BasicDeck deck = new BasicDeck(ship, 1);
	private BasicRoom room1 = new BasicRoom(2, 1, 0, 0);
	private BasicRoom room2 = new BasicRoom(2, 1, 0, 2);
	private BasicDoor door1 = new BasicDoor();
	private BasicDoor door2 = new BasicDoor();
	private BasicTile tile = (BasicTile) room1.getTiles().get(0);
	
    @Before
    public void setUp() {
    	ship.addDeck(deck);
    	
    	deck.addRoom(room1);
    	deck.addRoom(room2);
    	
    	door1.addRoom(room1);
    	door2.addRoom(room1);
    	door2.addRoom(room2);
	}
    
	@Test
    public void shouldReplenishAndConsumeOxygen() {
    	assertEquals(1.0f, room1.getOxygenLevel(), 0.05f);
    	
    	room1.consumeOxygen(0.5f);
    	assertThat(room1.getOxygenLevel()).isCloseTo(0.5f, within(0.05f));
    	
    	room1.replenishOxygen(0.5f);
    	assertThat(room1.getOxygenLevel()).isCloseTo(1.0f, within(0.05f));
    }
    
	@Test
	public void shouldNotReplenishOxygenWhenOnFire() {
		assertEquals(1.0f, room1.getOxygenLevel(), 0.05f);
		room1.consumeOxygen(0.5f);
		
		tile.ignite();
		room1.replenishOxygen(0.5f);
		
		assertThat(room1.getOxygenLevel()).isCloseTo(0.5f, within(0.05f));
	}
	
	@Test
	public void shouldNotLooseOxygenThroughClosedDoors() {
		door1.close();
		door2.close();
		
		assertThat(room1.getOxygenLevel()).isCloseTo(1f, Offset.offset(0.05f));
		assertThat(room2.getOxygenLevel()).isCloseTo(1f, Offset.offset(0.05f));
		
		ship.update(5f);
		
		assertThat(room1.getOxygenLevel()).isCloseTo(1f, Offset.offset(0.05f));
		assertThat(room2.getOxygenLevel()).isCloseTo(1f, Offset.offset(0.05f));
	}
	
	@Test
	public void shouldLooseOxygenToAdjacentDoorsOpenToSpace() {
		door1.open();
		
		assertThat(room1.getOxygenLevel()).isCloseTo(1f, Offset.offset(0.05f));
		assertThat(room2.getOxygenLevel()).isCloseTo(1f, Offset.offset(0.05f));
		
		ship.update(5f);
		
		assertThat(room1.getOxygenLevel()).isCloseTo(0.5f, Offset.offset(0.05f));
		assertThat(room2.getOxygenLevel()).isCloseTo(1f, Offset.offset(0.05f));
	}
	
	@Test
	public void shouldLooseOxygenToDoorOpenToSpaceThroughOtherRoom() {
		door1.open();
		door2.open();
		
		assertThat(room1.getOxygenLevel()).isCloseTo(1f, Offset.offset(0.05f));
		assertThat(room2.getOxygenLevel()).isCloseTo(1f, Offset.offset(0.05f));
		
		ship.update(5f);
		
		assertThat(room1.getOxygenLevel()).isCloseTo(0.5f, Offset.offset(0.05f));
		assertThat(room2.getOxygenLevel()).isCloseTo(0.5f, Offset.offset(0.05f));
	}

}
