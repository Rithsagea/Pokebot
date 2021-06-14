package com.pokebot.types.data;

import com.pokebot.types.EggGroup;
import com.pokebot.types.PokemonColor;

public class SpeciesData implements Comparable<SpeciesData> {
	public int id;
	public String identifier;
	
	public int base_happiness;
	public int capture_rate;
	
	public PokemonColor color;
	public EggGroup[] egg_groups;
	
	@Override
	public int compareTo(SpeciesData o) {
		return id - o.id;
	}
}
