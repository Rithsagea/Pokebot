package com.pokebot.types.data;

import java.util.Map;

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
	
	public String[] tutor_moves;
	public String[] machine_moves;
	public String[] egg_moves;
	public Map<Integer, String[]> level_moves;
	
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
