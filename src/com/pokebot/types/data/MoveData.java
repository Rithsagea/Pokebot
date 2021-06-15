package com.pokebot.types.data;

import com.pokebot.types.DamageClass;
import com.pokebot.types.LanguageString;
import com.pokebot.types.MoveTarget;
import com.pokebot.types.PokemonType;

public class MoveData implements Comparable<MoveData> {

	public int id;
	public String identifier;
	
	public PokemonType type;
	
	public int power;
	public int accuracy;
	public int pp;
	public int priority;
	
	public DamageClass damage_class;
	public MoveTarget move_target;
	
	public int effect_chance;
	public String effect_entry;
	public String short_effect;

	public LanguageString name;
	public LanguageString flavor_text;
	
	
	@Override
	public int compareTo(MoveData o) {
		return id - o.id;
	}
	
}
