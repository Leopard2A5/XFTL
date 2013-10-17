package de.xftl.fixture;

import java.util.List;

import de.xftl.spec.game.Game;
import de.xftl.spec.game.GameFile;
import de.xftl.spec.game.GameStartParameters;
import de.xftl.spec.game.State;
import de.xftl.spec.model.ships.Ship;

public class FixtureGame implements Game {

	private Ship _ship;
	
	@Override
	public void update(float elapsedTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPause(boolean pause) {
		// TODO Auto-generated method stub

	}

	@Override
	public State getState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void startNewGame(GameStartParameters parameters) {
		_ship = Fixture.buildShip();
	}

	@Override
	public void saveGame(GameFile file) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadGame(GameFile file) {
		// TODO Auto-generated method stub

	}

	@Override
	public Ship getShip() {
		return _ship;
	}

	@Override
	public List<Ship> getEnemyShips() {
		// TODO Auto-generated method stub
		return null;
	}

}
