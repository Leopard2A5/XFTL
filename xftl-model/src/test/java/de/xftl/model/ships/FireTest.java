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

import de.xftl.spec.model.ships.Tile;

public class FireTest {

    private Fire sut = new Fire();
    
    @Before
    public void setUp() throws Exception {
        sut.ignite(0.5f);
    }

    @Test
    public void shouldGrowToMaxValueGivenMaxOxygen() {
        float elapsedTime = 0;
        
        while (elapsedTime < 10) {
            sut.updateFireAndReturnConsumedOxygen(0.1f, 1);
            elapsedTime += 0.1;
        }
        
        assertEquals(Tile.MAX_FIRE, sut.getFireLevel(), 0.05f);
    }

    @Test
    public void shouldDecreaseOnLowOxygenSupply() {
        float elapsedTime = 0;
        
        while (elapsedTime < 10) {
            sut.updateFireAndReturnConsumedOxygen(0.1f, sut.getFireLevel() - 0.1f);
            elapsedTime += 0.1f;
        }
        
        assertEquals(Tile.NO_FIRE, sut.getFireLevel(), 0.05f);
    }
}
