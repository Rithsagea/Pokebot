package com.rithsagea.pokebot.types.registry;

import com.rithsagea.pokebot.Util;
import com.rithsagea.pokebot.lang.LanguageString;

public class RegistryMove {
	public final int id;
	public final String identifier;
	public final int generation_id;
	
	public final int type_id;
	public final int power;
	public final int pp;
	public final int accuracy;
	
	public final int priority;
	public final int target_id;
	public final int damage_class_id;
	public final int effect_id;
	public final int effect_chance;
	
	public final LanguageString name;
	
	public RegistryMove(String[] s) {
		id = Util.parseInt(s[0]);
		identifier = s[1];
		generation_id = Util.parseInt(s[2]);
		type_id = Util.parseInt(s[3]);
		power = Util.parseInt(s[4]);
		pp = Util.parseInt(s[5]);
		accuracy = Util.parseInt(s[6]);
		priority = Util.parseInt(s[7]);
		target_id = Util.parseInt(s[8]);
		damage_class_id = Util.parseInt(s[9]);
		effect_id = Util.parseInt(s[10]);
		effect_chance = Util.parseInt(s[11]);
		
		name = new LanguageString();
	}
}
