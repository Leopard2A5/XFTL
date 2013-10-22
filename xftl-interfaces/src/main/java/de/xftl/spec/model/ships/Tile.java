package de.xftl.spec.model.ships;

import de.xftl.spec.model.XFTLModelObject;
import de.xftl.spec.model.crew.CrewMember;

public interface Tile extends XFTLModelObject, Positioned<TileUnit>, TileOrRoomConnector {
	public CrewMember getCrewMember();
	public CrewMember getEnemyCrewMember();
	public TileOrRoomConnector getNorthNeighbor();
	public TileOrRoomConnector getEastNeighbor();
	public TileOrRoomConnector getSouthNeighbor();
	public TileOrRoomConnector getWestNeighbor();
}
