package de.xftl.model.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.xftl.spec.game.MovementPreview;
import de.xftl.spec.model.crew.CrewMember;
import de.xftl.spec.model.ships.Room;
import de.xftl.spec.model.ships.Ship;
import de.xftl.spec.model.ships.Tile;

public class MovementPreviewProcessor {

	public MovementPreview previewMovement(final List<CrewMember> selectedCrew,
										   final Tile tile) {
		if (selectedCrew.isEmpty())
			throw new IllegalArgumentException("No crewMembers selected!");
		
		final Room room = tile.getRoom();
		final Ship ship = room.getDeck().getShip();
		final BasicMovementPreview ret = new BasicMovementPreview(tile.getRoom(), selectedCrew);
		
		// filter out crew on other ships
		final List<CrewMember> crewToBeAssigned = new ArrayList<>();
		for (final CrewMember crew : selectedCrew) {
			if (crew.getCurrentTile().getRoom().getDeck().getShip().equals(ship)) {
				crewToBeAssigned.add(crew);
			}
		}
		
		// add previews for crew that's already in the selected room
		final List<CrewMember> crewAlreadyInRoom = room.getCrewMembers();
		for (final Iterator<CrewMember> crewIt = crewToBeAssigned.iterator(); crewIt.hasNext();) {
			final CrewMember crew = crewIt.next();
			if (crewAlreadyInRoom.contains(crew)) {
				crewIt.remove();
				ret.addPreview(crew, crew.getCurrentTile());
			}
		}
		
		if (!crewToBeAssigned.isEmpty()) {
			// find remaining free tiles in the room
			final List<Tile> freeTiles = new ArrayList<>();
			for (final Tile t : room.getTiles()) {
				if (t.getCrewMember() == null) {
					freeTiles.add(t);
				}
			}
			
			// assign tiles to crew
			while (!freeTiles.isEmpty() && !crewToBeAssigned.isEmpty()) {
				final Tile t = freeTiles.remove(0);
				final CrewMember crew = crewToBeAssigned.remove(0);
				
				ret.addPreview(crew, t);
			}
		}
		
		return ret;
	}
	
	
	
}
