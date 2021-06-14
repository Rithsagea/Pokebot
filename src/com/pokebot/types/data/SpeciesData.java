package com.pokebot.types.data;

import java.util.List;

import com.pokebot.types.EggGroup;
import com.pokebot.types.GrowthRate;
import com.pokebot.types.Habitat;
import com.pokebot.types.PokemonColor;
import com.pokebot.types.PokemonShape;

public class SpeciesData implements Comparable<SpeciesData> {
	public int id;
	public String identifier;
	
	public LanguageString genus;
	public LanguageString name;
	
	public int base_happiness;
	public int capture_rate;
	
	public int evolution_chain;
	public String evolves_from_species;
	
	public PokemonShape shape;
	public PokemonColor color;
	public Habitat habitat;
	
	public GrowthRate growth_rate;
	public List<EggGroup> egg_groups;
	public int hatch_counter;
	
	public boolean is_baby;
	public boolean is_legendary;
	public boolean is_mythical;
	
	public int gender_rate;
	public boolean has_gender_differences;
	public boolean forms_switchable;
	
	@Override
	public int compareTo(SpeciesData o) {
		return id - o.id;
	}
}
