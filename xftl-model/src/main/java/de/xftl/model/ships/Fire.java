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

import static de.xftl.spec.model.ships.Tile.MAX_FIRE;
import static de.xftl.spec.model.ships.Tile.NO_FIRE;
import de.xftl.spec.model.ships.Room;

public class Fire {
    private static final float AIR_CONSUMPTION_FACTOR = 0.1f;
    private static final float FIRE_GROWTH_FACTOR = 0.1f;
    
    private float _fireLevel;
    
    public float getFireLevel() {
        return _fireLevel;
    }

    public void extinguish() {
        _fireLevel = NO_FIRE;
    }
    
    public void ignite(float initialFireLevel) {
        setFireLevel(_fireLevel + initialFireLevel);
    }
    
    public void extinguishFire(float extinguishingLevel) {
        setFireLevel(_fireLevel - extinguishingLevel);
    }
    
    private void setFireLevel(float fireLevel) {
        _fireLevel = fireLevel;
        
        if (_fireLevel > MAX_FIRE)
            _fireLevel = MAX_FIRE;
        else if (_fireLevel < NO_FIRE)
            _fireLevel = NO_FIRE;
    }
    
    public float updateFireAndReturnConsumedOxygen(float elapsedTime, float currentOxygen) {
        if (currentOxygen <= Room.NO_OXYGEN) {
            _fireLevel = NO_FIRE;
        }
        else if (currentOxygen > _fireLevel) {
            setFireLevel(_fireLevel + (Math.min(FIRE_GROWTH_FACTOR, currentOxygen - _fireLevel)) * elapsedTime);
        }
        else if (currentOxygen < _fireLevel) {
            setFireLevel(_fireLevel - (Math.min(FIRE_GROWTH_FACTOR, _fireLevel - currentOxygen)) * elapsedTime);
        }
        
        return _fireLevel * AIR_CONSUMPTION_FACTOR;
    }
}
