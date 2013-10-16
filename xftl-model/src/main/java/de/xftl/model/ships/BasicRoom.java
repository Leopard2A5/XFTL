package de.xftl.model.ships;

import java.util.ArrayList;
import java.util.List;

import de.xftl.spec.model.Point;
import de.xftl.spec.model.crew.CrewMember;
import de.xftl.spec.model.ships.OxygenLevel;
import de.xftl.spec.model.ships.Room;
import de.xftl.spec.model.ships.RoomConnector;
import de.xftl.spec.model.ships.Tile;
import de.xftl.spec.model.ships.TileUnit;

public class BasicRoom implements Room {

	private Point<TileUnit> _leftUpperCornerPos;
	private System _system;
	private List<Tile> _tiles = new ArrayList<>();
	private OxygenLevel _oxygenLevel;
	
	@Override
	public void update(float elapsedTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Point<TileUnit> getLeftUpperCornerPos() {
		return _leftUpperCornerPos;
	}

	@Override
	public System getSystem() {
		return _system;
	}

	@Override
	public List<Tile> getTiles() {
		return _tiles;
	}

	@Override
	public List<CrewMember> getCrewMembers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoomConnector> getRoomConnectors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> getAdjacentRooms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> getAdjacentRooms(Room origin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OxygenLevel getOxygenLevel() {
		return _oxygenLevel;
	}

}
