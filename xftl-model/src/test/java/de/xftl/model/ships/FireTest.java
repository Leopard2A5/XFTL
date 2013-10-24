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

    Ship ship = new BasicShip();
    Deck deck = new BasicDeck(ship, new DeckNumber(1));
    Room room = new BasicRoom(1, 1, 0, 0);
    LifeSupport lifeSupport = ship.getLifeSupport();
    Tile tile = room.getTiles().get(0);
    
    @Before
    public void setUp() throws Exception {
        tile.ignite(0.5f);
    }

    @Test
    public void shouldGrowToMaxValueGivenMaxOxygen() {
        float elapsedTime = 0;
        
        while (elapsedTime < 10) {
            room.replenishOxygen(Room.MAX_OXYGEN);
            tile.update(0.1f);
            elapsedTime += 0.1;
        }
        
        assertEquals(Tile.MAX_FIRE, tile.getFireLevel(), 0.05f);
    }

    @Test
    public void shouldDecreaseOnLowOxygenSupply() {
        float elapsedTime = 0;
        
        while (elapsedTime < 10) {
            room.consumeOxygen(0.1f);
            tile.update(0.1f);
            elapsedTime += 0.1f;
        }
        
        assertEquals(Tile.NO_FIRE, tile.getFireLevel(), 0.05f);
    }
}
