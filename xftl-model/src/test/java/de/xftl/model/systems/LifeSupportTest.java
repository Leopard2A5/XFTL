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

package de.xftl.model.systems;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.xftl.model.ships.BasicDeck;
import de.xftl.model.ships.BasicRoom;
import de.xftl.model.ships.BasicShip;
import de.xftl.spec.model.ships.Deck;
import de.xftl.spec.model.ships.Room;
import de.xftl.spec.model.ships.Ship;
import de.xftl.spec.model.systems.LifeSupport;

public class LifeSupportTest {

    private static final float STEP = 0.1f;
    
    Ship ship = new BasicShip();
    Deck deck = new BasicDeck(ship, 1);
    Room room = new BasicRoom(0, 0, 2, 2);
    LifeSupport sut = ship.getLifeSupport();
    
    @Before
    public void setUp() throws Exception {
        deck.addRoom(room);
        room.consumeOxygen(Room.MAX_OXYGEN);
    }

    @Test
    public void shouldReplenishOxygenToMaxLevel() {
        assertEquals(0f, room.getOxygenLevel(), 0.05f);
        
        float elapsedTime = 0;
        while (elapsedTime < 10) {
            ship.update(STEP);
            
            elapsedTime += STEP;
        }
        
        assertEquals(Room.MAX_OXYGEN, room.getOxygenLevel(), 0.05f);
    }

}
