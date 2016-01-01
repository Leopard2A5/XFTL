package de.xftl.spec.game;

import java.util.List;
import java.util.Map;
import java.util.Set;

import de.xftl.spec.model.crew.CrewMember;
import de.xftl.spec.model.ships.Room;
import de.xftl.spec.model.ships.Tile;

public interface MovementPreview {
	public Room getTargetRoom();
	public Set<CrewMember> getSelectedCrewMembers();
	public boolean isAllCrewMembersAssigned();
	public List<CrewMember> getAssignedCrewMembers();
	public List<CrewMember> getUnassignedCrewMembers();
	public Map<CrewMember, Tile> getMovementPreviews();
}
