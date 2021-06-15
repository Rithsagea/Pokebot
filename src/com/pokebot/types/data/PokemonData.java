package com.pokebot.types.data;

import com.pokebot.types.PokemonType;

public class PokemonData implements Comparable<PokemonData> {

	public int id;
	public String identifier;
	
	public String ability1;
	public String ability2;
	public String abilityh;
	
	public int base_experience;
	public String[] forms;
	
	public int weight;
	public int height;
	
	//TODO: moves???
	
	public String species;
	public SpriteData sprites;
	public StatData base;
	public StatData effort;
	
	public PokemonType type1;
	public PokemonType type2;
	
	@Override
	public int compareTo(PokemonData o) {
		return id - o.id;
	}

}
