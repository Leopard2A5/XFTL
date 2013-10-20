package de.xftl.fixture;

import de.xftl.model.ships.BasicShip;
import de.xftl.spec.game.Game;
import de.xftl.spec.model.Point;
import de.xftl.spec.model.ships.Room;
import de.xftl.spec.model.ships.Ship;
import de.xftl.spec.model.ships.Tile;
import de.xftl.spec.model.ships.TileUnit;

public class Fixture {
	
	public static Game buildGame() {
		Game game = new FixtureGame();
		
		return game;
	}
	
	public static Ship buildShip() {
		BasicShip ship = new ShipBuilder()
			.addDeck()
			.addRoom(2, 2, 0, 1)
			.addRoom(2, 2, 2, 1)
			.addRoom(2, 1, 1, 0)
			.addRoom(2, 1, 1, 3)
			.addRoom(2, 2, 4, 1)
			.addRoom(1, 2, 5, 1)
			.buildShip();
		
		return ship;
	}

	public static void main(String[] args) {
		Ship ship = buildShip();
		
		final int MATRIX_X = 10;
		final int MATRIX_Y = 6;
		
		Tile[][] matrix = new Tile[MATRIX_Y + 1][MATRIX_X + 1];
		
		for (Room room : ship.getDecks().get(0).getRooms()) {
			for (Tile tile : room.getTiles()) {
				Point<TileUnit> pos = tile.getLeftUpperCornerPos();
				int x = pos.getX().getValue();
				int y = pos.getY().getValue();
				
				matrix[y][x] = tile;
			}
		}
		
		System.out.print("  |");
		for (int x = 0; x < MATRIX_X; x++)
			System.out.print(String.format("%d", x));
		System.out.println();
		System.out.print("--|");
		for (int x = 0; x < MATRIX_X; x++)
			System.out.print("-");
		System.out.println();
		
		for (int y = 0; y < MATRIX_Y; y++) {
			System.out.print(String.format("%d |", y));
			for (int x = 0; x < MATRIX_X; x++) {
				System.out.print(matrix[y][x] == null ? ' ' : 'X');
			}
			System.out.println("");
		}
	}
}
