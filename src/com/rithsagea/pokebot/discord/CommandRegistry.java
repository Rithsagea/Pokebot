package com.rithsagea.pokebot.discord;

import java.util.HashMap;

public class CommandRegistry {
	
	private HashMap<String, Command> registry = new HashMap<String, Command>();
	private String prefix;
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public String getPrefix() {
		return prefix;
	}
	
	public void registerCommand(Command cmd) {
		registry.put(cmd.getLabel(), cmd);
		for(String alias : cmd.getAliases())
			registry.put(alias, cmd);
	}
	
	public Command getCommand(String label) {
		return registry.get(label);
	}
}
