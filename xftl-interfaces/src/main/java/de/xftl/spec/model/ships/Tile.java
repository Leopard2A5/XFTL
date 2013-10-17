package de.xftl.spec.model.ships;

import java.util.Map;

import de.xftl.spec.model.Direction;
import de.xftl.spec.model.XFTLModelObject;
import de.xftl.spec.model.crew.CrewMember;

public interface Tile extends XFTLModelObject, TileUnitPositioned, TileOrRoomConnector {
	public CrewMember getCrewMember();
	public CrewMember getEnemyCrewMember();
	public Map<Direction, TileOrRoomConnector> getNeighbors();
	public void addNeighbor(Direction dir, TileOrRoomConnector neighbor);
}
