package com.pokebot.types.data;

import com.pokebot.types.LanguageString;

public class AbilityData implements Comparable<AbilityData> {
	public int id;
	public String identifier;
	
	public LanguageString name;
	public LanguageString flavor_text;
	public String effect_entry;
	public String short_effect;
	
	@Override
	public int compareTo(AbilityData o) {
		return id - o.id;
	}
}
