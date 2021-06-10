package com.rithsagea.pokebot.lang;

import java.util.HashMap;

public class LanguageRegistry {
	private HashMap<Integer, LanguageString> registry;
	
	public LanguageRegistry(String csvPath) {
		registry = new HashMap<>();
	}
}
