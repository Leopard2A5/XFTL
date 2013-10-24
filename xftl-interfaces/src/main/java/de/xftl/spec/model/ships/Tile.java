package de.xftl.spec.model.ships;

import de.xftl.spec.model.XFTLModelObject;
import de.xftl.spec.model.crew.CrewMember;

public interface Tile extends XFTLModelObject, Positioned<TileUnit>, TileOrRoomConnector {
	public static final float NO_HULL_BREACH = 0;
	public static final float MAX_HULL_BREACH = 1;
	public static final float NO_FIRE = 0;
	public static final float MAX_FIRE = 1;
	
	public CrewMember getCrewMember();
	public CrewMember getEnemyCrewMember();
	public TileOrRoomConnector getNorthNeighbor();
	public TileOrRoomConnector getEastNeighbor();
	public TileOrRoomConnector getSouthNeighbor();
	public TileOrRoomConnector getWestNeighbor();
	
	public float getHullBreachLevel();
	public boolean hasHullBreach();
	public void createHullBreach(float initialBreachValue);
	public void repairHullBreach(float repairValue);
	
	public float getFireLevel();
	public boolean isOnFire();
	public void ignite(float initialFireLevel);
	public void extinguishFire(float extinguishingLevel);
}
