/*
 * Copyright 2005-2012 IT Service Omikron.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.xftl.model.ships;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.xftl.spec.model.ships.Deck;
import de.xftl.spec.model.ships.DeckNumber;
import de.xftl.spec.model.ships.Room;
import de.xftl.spec.model.ships.Ship;
import de.xftl.spec.model.ships.Tile;
import de.xftl.spec.model.systems.LifeSupport;

public class FireTest {

    private static final float STEP = 0.1f;
    
    Ship ship = new BasicShip();
    Deck deck = new BasicDeck(ship, new DeckNumber(1));
    Room room = new BasicRoom(2, 1, 0, 0);
    LifeSupport lifeSupport = ship.getLifeSupport();
    Tile tile = room.getTiles().get(0);
    Tile tile2 = room.getTiles().get(1);
    
    @Before
    public void setUp() throws Exception {
        deck.addRoom(room);
        
        tile.ignite(0.5f);
    }

    @Test
    public void shouldGrowToMaxValueGivenMaxOxygen() {
        float elapsedTime = 0;
        
        while (elapsedTime < 10) {
            room.replenishOxygen(Room.MAX_OXYGEN);
            tile.update(STEP);
            elapsedTime += STEP;
        }
        
        assertEquals(Tile.MAX_FIRE, tile.getFireLevel(), 0.05f);
    }

    @Test
    public void shouldDecreaseOnLowOxygenSupply() {
        float elapsedTime = 0;
        
        while (elapsedTime < 10) {
            room.consumeOxygen(STEP);
            tile.update(STEP);
            elapsedTime += STEP;
        }
        
        assertEquals(Tile.NO_FIRE, tile.getFireLevel(), 0.05f);
    }
    
    @Test
    public void shouldSpreadToOtherTiles() {
        float elapsedTime = 0;
        
        while (elapsedTime < 20 && !tile2.isOnFire()) {
            room.replenishOxygen(Room.MAX_OXYGEN);
            tile.update(STEP);
            elapsedTime += STEP;
        }
        
        assertTrue(tile2.isOnFire());
    }
    
    @Test
    public void shouldDieDownWithWorkingLifeSupport() {
        float elapsedTime = 0;
        
        while (elapsedTime < 10 && tile.isOnFire()) {
            lifeSupport.update(STEP);
            tile.update(STEP);
            elapsedTime += STEP;
        }
        
        assertFalse(tile.isOnFire());
    }
}