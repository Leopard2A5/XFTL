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

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import de.xftl.spec.model.ships.Deck;
import de.xftl.spec.model.ships.Room;
import de.xftl.spec.model.ships.Ship;
import de.xftl.spec.model.ships.Tile;
import de.xftl.spec.model.systems.LifeSupport;

public class FireTest {

    private static final float STEP = 0.1f;
    
    Ship ship = new BasicShip();
    Deck deck = new BasicDeck(ship, 1);
    Room room = new BasicRoom(2, 1, 0, 0);
    LifeSupport lifeSupport = ship.getLifeSupport();
    Tile tile = room.getTiles().get(0);
    Tile tile2 = room.getTiles().get(1);
    
    @Before
    public void setUp() throws Exception {
        deck.addRoom(room);
        tile.ignite();
    }

    @Test
    public void shouldDieOnLowOxygenSupply() {
        float elapsedTime = 0;
        
        while (elapsedTime < 10) {
            room.consumeOxygen(STEP);
            tile.update(STEP);
            elapsedTime += STEP;
        }
        
        assertThat(tile.isOnFire()).isFalse();
    }
    
    @Test
    public void shouldSpreadToOtherTiles() {
        float elapsedTime = 0;
        
        while (elapsedTime < 20 && !tile2.isOnFire()) {
            room.replenishOxygen(Room.MAX_OXYGEN);
            tile.update(STEP);
            elapsedTime += STEP;
        }
        
        assertThat(tile2.isOnFire()).isTrue();
    }
    
    @Test
    public void shouldDieDownWithWorkingLifeSupport() {
        float elapsedTime = 0;
        
        while (elapsedTime < 20 && tile.isOnFire()) {
            lifeSupport.update(STEP);
            tile.update(STEP);
            elapsedTime += STEP;
        }
        
        assertThat(tile.isOnFire()).isFalse();
    }
}