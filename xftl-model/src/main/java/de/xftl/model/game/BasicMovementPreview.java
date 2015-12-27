package de.xftl.model.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import de.xftl.spec.game.MovementPreview;
import de.xftl.spec.model.crew.CrewMember;
import de.xftl.spec.model.ships.Room;
import de.xftl.spec.model.ships.Tile;

public class BasicMovementPreview implements MovementPreview {

	private final Room _targetRoom;
	private final List<CrewMember> _assignedCrewMembers = new ArrayList<>();
	private final List<CrewMember> _unAssignedCrewMembers = new ArrayList<>();
	private final Map<CrewMember, Tile> _movementPreviews = new LinkedHashMap<>();
	
	public BasicMovementPreview(final Room targetRoom,
			                    final List<CrewMember> allCrewMembers) {
		super();
		_targetRoom = targetRoom;
		_unAssignedCrewMembers.addAll(allCrewMembers);
	}
	
	public void addPreview(final CrewMember crew,
			               final Tile tile) {
		_movementPreviews.put(crew, tile);
		
		if (!_unAssignedCrewMembers.remove(crew))
			throw new RuntimeException(String.format("Cannot add preview for crewMember '%s' since it wasn't passed in during the construction of this %s", crew, getClass().getSimpleName()));
		_assignedCrewMembers.add(crew);
	}

	@Override
	public Room getTargetRoom() {
		return _targetRoom;
	}

	@Override
	public boolean isAllCrewMembersAssigned() {
		return _unAssignedCrewMembers.isEmpty();
	}

	@Override
	public List<CrewMember> getAssignedCrewMembers() {
		return Collections.unmodifiableList(_assignedCrewMembers);
	}

	@Override
	public List<CrewMember> getUnassignedCrewMembers() {
		return Collections.unmodifiableList(_unAssignedCrewMembers);
	}

	@Override
	public Map<CrewMember, Tile> getMovementPreviews() {
		return Collections.unmodifiableMap(_movementPreviews);
	}

}
