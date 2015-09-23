package de.xftl.fixture;

import de.xftl.model.ships.BasicShip;
import de.xftl.model.systems.BasicGenerator;
import de.xftl.spec.game.Game;
import de.xftl.spec.model.Point;
import de.xftl.spec.model.ships.Room;
import de.xftl.spec.model.ships.Ship;
import de.xftl.spec.model.ships.Tile;
import de.xftl.spec.model.systems.Energy;

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
			.addDoorSystem()
			.addRoom(2, 1, 1, 3)
			.addLifeSupport()
			.addRoom(2, 2, 4, 1)
			.addRoom(1, 2, 6, 1)
			.addDoor(new Point<Integer>(1, 0), new Point<Integer>(1, 1))
			.addDoor(new Point<Integer>(1, 2), new Point<Integer>(1, 3))
			.addDoor(new Point<Integer>(2, 0), new Point<Integer>(2, 1))
			.addDoor(new Point<Integer>(2, 2), new Point<Integer>(2, 3))
			.addDoor(new Point<Integer>(3, 1), new Point<Integer>(4, 1))
			.addDoor(new Point<Integer>(3, 2), new Point<Integer>(4, 2))
			.addDoor(new Point<Integer>(5, 2), new Point<Integer>(6, 2))
			.addAirlock(new Point<Integer>(0, 1), new Point<Integer>(-1, 1))
			.addAirlock(new Point<Integer>(0, 2), new Point<Integer>(-1, 2))
			.addAirlock(new Point<Integer>(3, 1), new Point<Integer>(3, 0))
			.addAirlock(new Point<Integer>(3, 2), new Point<Integer>(3, 3))
			.addLift(new Point<Integer>(1, 0), new Point<Integer>(0, 0), "1")
			.addLift(new Point<Integer>(5, 1), new Point<Integer>(5, 0), "2")
			.addDeck()
			.addRoom(2, 2, 0, 1)
			.addRoom(2, 2, 2, 1)
			.addSystem(new BasicGenerator(Energy.valueOf(20)))
			.addRoom(2, 1, 1, 0)
			.addRoom(2, 1, 1, 3)
			.addRoom(2, 2, 4, 1)
			.addRoom(1, 2, 6, 1)
			.addDoor(new Point<Integer>(1, 0), new Point<Integer>(1, 1))
			.addDoor(new Point<Integer>(1, 2), new Point<Integer>(1, 3))
			.addDoor(new Point<Integer>(2, 0), new Point<Integer>(2, 1))
			.addDoor(new Point<Integer>(2, 2), new Point<Integer>(2, 3))
			.addDoor(new Point<Integer>(3, 1), new Point<Integer>(4, 1))
			.addDoor(new Point<Integer>(3, 2), new Point<Integer>(4, 2))
			.addDoor(new Point<Integer>(5, 2), new Point<Integer>(6, 2))
			.addAirlock(new Point<Integer>(0, 1), new Point<Integer>(-1, 1))
			.addAirlock(new Point<Integer>(0, 2), new Point<Integer>(-1, 2))
			.addAirlock(new Point<Integer>(3, 1), new Point<Integer>(3, 0))
			.addAirlock(new Point<Integer>(3, 2), new Point<Integer>(3, 3))
			.addLift(new Point<Integer>(1, 0), new Point<Integer>(0, 0), "1")
			.addLift(new Point<Integer>(5, 1), new Point<Integer>(5, 0), "2")
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
				Point<Integer> pos = tile.getLeftUpperCornerPos();
				int x = pos.getX();
				int y = pos.getY();
				
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
