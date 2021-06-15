package com.pokebot;

import java.util.HashMap;

public class CommandRegistry {
	private HashMap<String, Command> cmdMap;
	
	public CommandRegistry() {
		cmdMap = new HashMap<>();
	}
	
	public void registerCommand(Command cmd) {
		cmdMap.put(cmd.getLabel(), cmd);
		if(cmd.getAliases() != null)
		for(String a : cmd.getAliases()) {
			cmdMap.put(a, cmd);
		}
	}
	
	public Command getCommand(String label) {
		return cmdMap.get(label);
	}
}
