package com.rithsagea.types.data;

import com.rithsagea.types.EggGroup;
import com.rithsagea.types.GrowthRate;
import com.rithsagea.types.Habitat;
import com.rithsagea.types.PokemonColor;
import com.rithsagea.types.PokemonShape;

public class SpeciesData {
	
	public String identifier;
	public int order;
	
	public int id;
	public String name;
	public String genus;
	public String flavor_text;
	public int generation;
	
	public int base_happiness;
	public int gender_rate;
	public int capture_rate;
	
	public int hatch_counter;
	public boolean has_gender_differences;
	public EggGroup egg_group;
	
	public boolean is_baby;
	public boolean is_legendary;
	public boolean is_mythical;
	public boolean forms_switchable;
	
	public Habitat habitat;
	public PokemonShape shape;
	public PokemonColor color;
	public GrowthRate growthRate;
	
//	public EvolutionChain evolution_chain;
	public SpeciesData evolves_from_species;
	public PokemonData[] varieties;
}
