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

package de.xftl.model.game;

import java.util.ArrayList;
import java.util.List;

import de.xftl.spec.game.Game;
import de.xftl.spec.game.GameFile;
import de.xftl.spec.game.GameStartParameters;
import de.xftl.spec.game.State;
import de.xftl.spec.model.ships.Ship;

public class BasicGame implements Game {

    private Ship _ship;
    private List<Ship> _enemyShips = new ArrayList<>();
    private State _state = State.INITIAL;
    
    @Override
    public void update(final float elapsedTime) {
        if (_ship != null)
            _ship.update(elapsedTime);
        
        for (Ship ship : _enemyShips)
            ship.update(elapsedTime);
    }

    @Override
    public void setPause(final boolean pause) {
        // TODO Auto-generated method stub
    }

    @Override
    public State getState() {
        return _state;
    }

    @Override
    public void startNewGame(final GameStartParameters parameters) {
        _state = State.COMBAT;
    }

    @Override
    public void saveGame(final GameFile file) {
        // TODO Auto-generated method stub
    }

    @Override
    public void loadGame(final GameFile file) {
        // TODO Auto-generated method stub
    }

    @Override
    public Ship getShip() {
        return _ship;
    }

    @Override
    public List<Ship> getEnemyShips() {
        return _enemyShips;
    }

}
