package com.rithsagea.pokebot.types.registry;

import com.rithsagea.pokebot.Util;

public class RegistryAbility {
	public final int id;
	public final String identifier;
	
	public RegistryAbility(String[] s) {
		id = Util.parseInt(s[0]);
		identifier = s[1];
	}
}
