package de.xftl.game.framework;

import de.xftl.spec.game.Game;

public class CommandInterpreter {

	private Game _gameModel;
	
	public CommandInterpreter(Game gameModel) {
		_gameModel = gameModel;
	}
	
	public String interpretCommand(String text) {
		
		return "invalid Command: " + text;
	}
}
