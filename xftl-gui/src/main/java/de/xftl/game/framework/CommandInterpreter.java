package de.xftl.game.framework;

public class CommandInterpreter extends GameObject {

	public CommandInterpreter(XftlGameRenderer game) {
		super(game);
	}
	
	public String interpretCommand(String text) {
		
		String response = "invalid Command: " + text;
		
		if (text.equalsIgnoreCase("enable profiler")) {
			getGame().setIsProfilerEnabled(true);
			response = null;
		}
		else if (text.equalsIgnoreCase("disable profiler")) {
			getGame().setIsProfilerEnabled(false);
			response = null;
		}
		
		return response;
	}
}
