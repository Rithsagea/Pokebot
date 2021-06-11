package com.rithsagea.pokebot.types.registry;

import com.rithsagea.pokebot.Util;
import com.rithsagea.pokebot.lang.LanguageString;

public class RegistryAbility {
	public final int id;
	public final String identifier;
	
	public final LanguageString name;
	
	public RegistryAbility(String[] s) {
		id = Util.parseInt(s[0]);
		identifier = s[1];
		
		name = new LanguageString();
	}
}
