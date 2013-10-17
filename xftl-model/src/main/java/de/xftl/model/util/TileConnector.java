package de.xftl.model.util;

import java.util.Collection;

import de.xftl.spec.model.Direction;
import de.xftl.spec.model.Point;
import de.xftl.spec.model.ships.Tile;
import de.xftl.spec.model.ships.TileUnit;

public class TileConnector {
	
	private Collection<Tile> _tiles;
	
	public TileConnector(Collection<Tile> tiles) {
		super();
		
		_tiles = tiles;
	}
	
	public void connectTiles() {
		Point<TileUnit> maxDimensions = getMaxDimensions();
		
		Tile[][] matrix = new Tile[maxDimensions.getY().getValue()][maxDimensions.getX().getValue()];
		for (Tile tile : _tiles) {
			Point<TileUnit> pos = tile.getLeftUpperCornerPos();
			matrix[pos.getY().getValue()][pos.getX().getValue()] = tile;
		}
		
		for (int y = 0; y < maxDimensions.getY().getValue(); y++) {
			for (int x = 0; x < maxDimensions.getX().getValue(); x++) {
				Tile t = matrix[y][x];
				
				if (t == null) continue;
				
				if (x > 0) {
					Tile left = matrix[y][x - 1];
					if (left != null) {
						t.addNeighbor(Direction.WEST, left);
						left.addNeighbor(Direction.EAST, t);
					}
				}
				if (x < maxDimensions.getX().getValue() - 1) {
					Tile right = matrix[y][x + 1];
					if (right != null) {
						t.addNeighbor(Direction.EAST, right);
						right.addNeighbor(Direction.WEST, t);
					}
				}
				if (y > 0) {
					Tile up = matrix[y - 1][x];
					if (up != null) {
						t.addNeighbor(Direction.NORTH, up);
						up.addNeighbor(Direction.SOUTH, t);
					}
				}
				if (y < maxDimensions.getY().getValue() - 1) {
					Tile down = matrix[y + 1][x];
					t.addNeighbor(Direction.SOUTH, down);
					down.addNeighbor(Direction.NORTH, t);
				}
			}
		}
	}

	private Point<TileUnit> getMaxDimensions() {
		int x = 0;
		int y = 0;
		
		for (Tile tile : _tiles) {
			Point<TileUnit> pos = tile.getLeftUpperCornerPos();
			x = Math.max(x, pos.getX().getValue());
			y = Math.max(y, pos.getY().getValue());
		}
		
		return new Point<TileUnit>(new TileUnit(x + 1), new TileUnit(y + 1));
	}
	
}
