package de.xftl.model.ships;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import de.xftl.model.util.TileUnitMatrix;
import de.xftl.model.util.TileUnitMatrixIterator;
import de.xftl.spec.model.Direction;
import de.xftl.spec.model.Point;
import de.xftl.spec.model.crew.CrewMember;
import de.xftl.spec.model.ships.OxygenLevel;
import de.xftl.spec.model.ships.Room;
import de.xftl.spec.model.ships.RoomConnector;
import de.xftl.spec.model.ships.Tile;
import de.xftl.spec.model.ships.TileUnit;
import de.xftl.spec.model.ships.Positioned;

public class BasicRoom implements Room {

	private Point<TileUnit> _leftUpperCornerPos;
	private System _system;
	private List<Tile> _tiles;
	private OxygenLevel _oxygenLevel;
	
	public BasicRoom(int width, int height, int x, int y) {
	    super();

	    _leftUpperCornerPos = new Point<TileUnit>(new TileUnit(x), new TileUnit(y));
	    _tiles = buildTiles(width, height, x, y);
	}

	private List<Tile> buildTiles(int width, int height, int pX, int pY) {
		List<Tile> tiles = new ArrayList<>(width * height);
		List<BasicTile> basicTiles = new ArrayList<>(width * height);
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				TileUnit tux = new TileUnit(x + pX);
				TileUnit tuy = new TileUnit(y + pY);
				BasicTile tile = new BasicTile(this, new Point<TileUnit>(tux, tuy));
				tiles.add(tile);
			}
		}
		
		connectTiles(basicTiles);
		return tiles;
	}
	
	private void connectTiles(Collection<BasicTile> tiles) {
	    TileUnitMatrix<BasicTile> matrix = new TileUnitMatrix<>(tiles);
        
        for (TileUnitMatrixIterator<BasicTile> it = matrix.matrixIterator(); it.hasNext();) {
            BasicTile t = it.next();
            
            for (Direction dir : Direction.values()) {
                BasicTile other = it.getNeighbor(dir);
                if (other != null) {
                    t.addNeighbor(dir, other);
                    other.addNeighbor(dir.getOpposite(), t);
                }
            }
        }
	}
	
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
		return Collections.unmodifiableList(_tiles);
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

	@Override
    public int compareTo(Positioned<TileUnit> o) {
        return _leftUpperCornerPos.compareTo(o.getLeftUpperCornerPos());
    }
}
