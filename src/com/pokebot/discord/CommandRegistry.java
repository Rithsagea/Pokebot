package com.pokebot.discord;

import java.util.HashMap;

public class CommandRegistry {
	private HashMap<String, Command> cmdMap;
	private String prefix;
	
	public CommandRegistry(String prefix) {
		cmdMap = new HashMap<>();
		
		this.prefix = prefix;
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
	
	public String getPrefix() {
		return prefix;
	}
}
