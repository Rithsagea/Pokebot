package com.rithsagea.pokebot.types;

import java.util.HashMap;

import com.rithsagea.pokebot.Util;
import com.rithsagea.pokebot.lang.Language;
import com.rithsagea.pokebot.lang.LanguageString;

public class RegistrySpecies {
	
	public final int id;
	public final String identifier;
	public final int generation_id;
	
	public final int evolves_from_species_id;
	public final int evolution_chain_id;
	
	public final int color_id;
	public final int shape_id;
	public final int habitat_id;
	
	/**
	 * The chance of this Pokémon being female, in eighths; or -1 for genderless
	 */
	public final int gender_rate;
	public final int capture_rate;
	public final int base_happiness;
	
	public final boolean has_gender_differences;
	public final boolean is_baby;
	public final int hatch_counter;
	
	public final int growth_rate_id;
	
	public final boolean forms_switchable;
	public final boolean is_legendary;
	public final boolean is_mythical;
	
	public final LanguageString name;
	public final LanguageString genus;
	public final HashMap<Integer, LanguageString> flavor;
	
	public RegistrySpecies(String[] s) {
		id = Util.parseInt(s[0]);
		identifier = s[1];
		generation_id = Util.parseInt(s[2]);
		evolves_from_species_id = Util.parseInt(s[3]);
		evolution_chain_id = Util.parseInt(s[4]);
		color_id = Util.parseInt(s[5]);
		shape_id = Util.parseInt(s[6]);
		habitat_id = Util.parseInt(s[7]);
		gender_rate = Util.parseInt(s[8]);
		capture_rate = Util.parseInt(s[9]);
		base_happiness = Util.parseInt(s[10]);
		is_baby = Util.parseInt(s[11]) == 1;
		hatch_counter = Util.parseInt(s[12]);
		has_gender_differences = Util.parseInt(s[13]) == 1;
		growth_rate_id = Util.parseInt(s[14]);
		forms_switchable = Util.parseInt(s[15]) == 1;
		is_legendary = Util.parseInt(s[16]) == 1;
		is_mythical = Util.parseInt(s[17]) == 1;
		
		name = new LanguageString();
		genus = new LanguageString();
		flavor = new HashMap<>();
	}
	
	public String toString() {
		return String.format("#%d %s - %s", id, name.get(Language.en_US), genus.get(Language.en_US));
	}
}
