package com.pokebot.game;

import com.pokebot.types.Gender;
import com.pokebot.types.Nature;
import com.pokebot.types.data.DataRegistry;
import com.pokebot.types.data.FormData;
import com.pokebot.types.data.PokemonData;
import com.pokebot.types.data.SpeciesData;
import com.pokebot.types.data.StatData;

public class Pokemon {
	public SpeciesData species;
	public PokemonData pokemon;
	public FormData form;
	
	public boolean isShiny;
	public int expPoints;
	public int level;
	public Gender gender;
	public int friendship;
	
	public StatData ivs;
	public StatData evs;
	public StatData stats;
	public Nature nature;
	public int currHp;
	
//	public String heldItem;
	public PokemonMove[] moves;
	public String ability;
	
	public int otID;
	public String otName;
	public String nickname;
	
	public Pokemon(String form) {
		DataRegistry data = DataRegistry.getInstance();
		
		this.form  = data.getForm(form);
		this.pokemon = data.getPokemon(this.form.pokemon);
		this.species = data.getSpecies(pokemon.species);
		
		setExpPoints(0);
		
		ivs = new StatData();
		evs = new StatData();
		stats = new StatData();
		
		moves = new PokemonMove[4];
	}
	
	public void setExpPoints(int exp) {
		expPoints = exp;
		level = species.growth_rate.getLevel(exp);
	}
}
