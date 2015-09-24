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
