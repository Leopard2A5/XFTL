package de.xftl.spec.game;

import java.util.List;

import de.xftl.spec.model.ships.Ship;

public interface Game {
	public void update(float elapsedTime);
	public void setPause(boolean pause);
	public State getState();
	public void startNewGame(GameStartParameters parameters);
	public void saveGame(GameFile file);
	public void loadGame(GameFile file);
	public Ship getShip();
	public List<Ship> getEnemyShips();
}
