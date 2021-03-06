package de.xftl.model.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.collections4.CollectionUtils;

import de.xftl.spec.game.Game;
import de.xftl.spec.game.GameFile;
import de.xftl.spec.game.GameStartParameters;
import de.xftl.spec.game.MovementPreview;
import de.xftl.spec.game.State;
import de.xftl.spec.model.crew.CrewMember;
import de.xftl.spec.model.crew.Movement;
import de.xftl.spec.model.ships.Ship;
import de.xftl.spec.model.ships.Tile;

public class BasicGame implements Game {

    private Ship _ship;
    private List<Ship> _enemyShips = new ArrayList<>();
    private State _state = State.INITIAL;
    private final List<CrewMember> _selectedCrew = new ArrayList<>();
    private final MovementPreviewProcessor _movementPreviewProcessor = new MovementPreviewProcessor();
    private final WaypointPlanner _waypointPlanner = new WaypointPlanner();
    
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

	@Override
	public List<CrewMember> getSelectedCrewMembers() {
		return Collections.unmodifiableList(_selectedCrew);
	}

	@Override
	public void selectCrewMembers(final List<CrewMember> members) {
		deselectCrewMembers();
		_selectedCrew.addAll(members);
	}

	@Override
	public void deselectCrewMembers() {
		_selectedCrew.clear();
	}

	@Override
	public MovementPreview previewMovement(final Tile tile) {
		return _movementPreviewProcessor.previewMovement(_selectedCrew, tile);
	}
	
	@Override
	public void move(final MovementPreview preview) {
		if (!CollectionUtils.isEqualCollection(_selectedCrew, preview.getSelectedCrewMembers())) {
			throw new RuntimeException("The given MovementPreview was issued for another crewMember selection than the current one");
		}
		
		for (final Entry<CrewMember, Tile> entry : preview.getMovementPreviews().entrySet()) {
			final CrewMember crew = entry.getKey();
			final Tile targetTile = entry.getValue();
			
			final Movement movement = _waypointPlanner.planMovement(crew.getCurrentTile(), targetTile);
			crew.setMovement(movement);
		}
	}
	
}
