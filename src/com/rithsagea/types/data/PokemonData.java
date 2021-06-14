package com.rithsagea.types.data;

import com.rithsagea.types.PokemonType;

public class PokemonData {
	public String identifier;
	public int order;
	public int id;
	
	public int weight;
	public int height;
	public boolean is_default;
	public int base_experience;
	
	public StatData base_stats;
	public StatData effort;
	
	public PokemonType type1;
	public PokemonType type2;
	
	public AbilityData ability1;
	public AbilityData ability2;
	public AbilityData abilityh;
	
//	public Move[] moves;
//	public HashMap<Integer, List<Move>> move_levels;
	
	public SpeciesData species;
//	public PokemonForm[] forms;
	
	public SpriteData sprites;
}
