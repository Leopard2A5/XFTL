package de.xftl.model.ships;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.xftl.model.util.TileUnitMatrix;
import de.xftl.model.util.TileUnitMatrixIterator;
import de.xftl.spec.model.Direction;
import de.xftl.spec.model.Point;
import de.xftl.spec.model.crew.CrewMember;
import de.xftl.spec.model.graphutils.OpennessToSpaceChecker;
import de.xftl.spec.model.ships.Deck;
import de.xftl.spec.model.ships.Room;
import de.xftl.spec.model.ships.RoomConnector;
import de.xftl.spec.model.ships.Tile;
import de.xftl.spec.model.systems.ShipSystem;

public class BasicRoom implements Room {

	private final String _name;
    private Deck _deck;
	private Point<Integer> _leftUpperCornerPos;
	private int _width;
	private int _height;
	private ShipSystem _system;
	private List<Tile> _tiles;
	private float _oxygenLevel = MAX_OXYGEN;
	private List<RoomConnector> _roomConnectors = new ArrayList<>();
	
	private final OpennessToSpaceChecker _opennessToSpaceCheck = new OpennessToSpaceChecker();
	
	public BasicRoom(final int width, final int height, final int x, final int y) {
	    this("", width, height, x, y);
	}
	
	public BasicRoom(final String name, final int width, final int height, final int x, final int y) {
	    super();

	    _name = name;
	    _leftUpperCornerPos = new Point<Integer>(x, y);
	    _width = width;
	    _height = height;
	    _tiles = buildTiles(width, height, x, y);
	}
	
	@Override
	public String toString() {
		return "Room '" + _name + "'";
	}
	
	@Override
	public Deck getDeck() {
		return _deck;
	}
	
	@Override
	public void setDeck(final Deck deck) {
	    _deck = deck;
	}

	private List<Tile> buildTiles(final int width, final int height, final int pX, final int pY) {
		final List<Tile> tiles = new ArrayList<>(width * height);
		final List<BasicTile> basicTiles = new ArrayList<>(width * height);
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				final int tux = x + pX;
				final int tuy = y + pY;
				BasicTile tile = new BasicTile(this, new Point<Integer>(tux, tuy));
				tiles.add(tile);
				basicTiles.add(tile);
			}
		}
		
		connectTiles(basicTiles);
		return tiles;
	}
	
	private void connectTiles(final Collection<BasicTile> tiles) {
		final TileUnitMatrix<BasicTile> matrix = new TileUnitMatrix<>(tiles);
        
        for (TileUnitMatrixIterator<BasicTile> it = matrix.matrixIterator(); it.hasNext();) {
        	final BasicTile t = it.next();
            
            for (Direction dir : Direction.values()) {
            	final BasicTile other = it.getNeighbor(dir);
                if (other != null) {
                    t.addNeighbor(dir, other);
                    other.addNeighbor(dir.getOpposite(), t);
                }
            }
        }
	}
	
    @Override
	public void update(final float elapsedTime) {
    	handleOpennessToSpace(elapsedTime);
    	
		for (Tile tile : _tiles)
		    tile.update(elapsedTime);
		for (RoomConnector connector : _roomConnectors)
		    connector.update(elapsedTime);
	}

	private void handleOpennessToSpace(final float elapsedTime) {
		if (isOpenToSpace()) {
			consumeOxygen(0.1f * elapsedTime);
		}
	}

	@Override
	public Point<Integer> getLeftUpperCornerPos() {
		return _leftUpperCornerPos;
	}
	
	@Override
    public int getWidth() {
        return _width;
    }

    @Override
    public int getHeigth() {
        return _height;
    }

	@Override
	public ShipSystem getSystem() {
		return _system;
	}

    public void setSystem(final ShipSystem system) {
        if (_system != null)
            throw new RuntimeException("This room already has a system assigned!");
        
        _system = system;
        
        _deck.onSystemAdded(system);
    }

	@Override
	public List<Tile> getTiles() {
		return Collections.unmodifiableList(_tiles);
	}

	@Override
	public List<CrewMember> getCrewMembers() {
		final List<CrewMember> ret = new ArrayList<>();
		
		for (final Tile tile : _tiles) {
			if (tile.getCrewMember() != null) {
				ret.add(tile.getCrewMember());
			}
		}
		
		return ret;
	}

	@Override
	public List<RoomConnector> getRoomConnectors() {
		return Collections.unmodifiableList(_roomConnectors);
	}

	@Override
	public List<Room> getAdjacentRooms() {
		final Set<Room> ret = new HashSet<>();
		
		for (final RoomConnector rc : _roomConnectors) {
			ret.addAll(rc.getConnectedRooms(this));
		}
		
		return new ArrayList<>(ret);
	}

	@Override
	public List<Room> getAdjacentRooms(final Room origin) {
		final Set<Room> ret = new HashSet<>();
		
		for (final RoomConnector rc : _roomConnectors) {
			ret.addAll(rc.getConnectedRooms());
		}
		ret.remove(this);
		
		return new ArrayList<>(ret);
	}

	@Override
	public float getOxygenLevel() {
		return _oxygenLevel;
	}
	
	@Override
	public void consumeOxygen(final float oxygen) {
		_oxygenLevel = Math.max(NO_OXYGEN, _oxygenLevel - oxygen);
	}

	@Override
	public void replenishOxygen(final float oxygen) {
		if (isOnFire() || isOpenToSpace())
			return;
		
		_oxygenLevel = Math.min(MAX_OXYGEN, _oxygenLevel + oxygen);
	}

	@Override
	public void addRoomConnector(final RoomConnector rc) {
	    if (!_roomConnectors.contains(rc)) {
	        _roomConnectors.add(rc);
	        _deck.onRoomConnectorAdded(rc);
	    }
	}

	@Override
	public boolean isOnFire() {
		for (Tile t : _tiles)
			if (t.isOnFire())
				return true;
		return false;
	}
	
	@Override
	public boolean isOpenToSpace() {
		_opennessToSpaceCheck.reset();
		return _opennessToSpaceCheck.isOpenToSpace(this);
		
//		for (final RoomConnector rc : _roomConnectors)
//			if (rc.getConnectedRooms().size() < 2 && rc.isOpen())
//				return true;
//
//		return false;
	}
	
}
