package de.xftl.spec.game;

import java.util.List;

import de.xftl.spec.model.crew.CrewMember;
import de.xftl.spec.model.ships.Ship;
import de.xftl.spec.model.ships.Tile;

public interface Game {
	public void update(float elapsedTime);
	public void setPause(boolean pause);
	public State getState();
	public void startNewGame(GameStartParameters parameters);
	public void saveGame(GameFile file);
	public void loadGame(GameFile file);
	public Ship getShip();
	public List<Ship> getEnemyShips();
	
	public List<CrewMember> getSelectedCrewMembers();
	public void selectCrewMembers(List<CrewMember> members);
	public void deselectCrewMembers();
	
	public MovementPreview previewMovement(Tile tile);
}
