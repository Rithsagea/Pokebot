package com.pokebot.game;

import com.pokebot.types.Gender;
import com.pokebot.types.Language;
import com.pokebot.types.Nature;
import com.pokebot.types.Stat;
import com.pokebot.types.data.AbilityData;
import com.pokebot.types.data.DataRegistry;
import com.pokebot.types.data.FormData;
import com.pokebot.types.data.MoveData;
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
	public AbilityData ability;
	
	public int otID;
	public String otName;
	public String nickname;
	
	public Pokemon(String form) {
		this(form, new int[6], new int[6], Nature.HARDY);
	}
	
	public Pokemon(String form, int[] ivs, int[] evs, Nature nature) {
		DataRegistry data = DataRegistry.getInstance();
		
		this.form  = data.getForm(form);
		this.pokemon = data.getPokemon(this.form.pokemon);
		this.species = data.getSpecies(pokemon.species);
		
		setExpPoints(0);
		this.friendship = species.base_happiness;
		
		this.ivs = new StatData();
		this.evs = new StatData();
		stats = new StatData();
		
		for(Stat stat : Stat.DEFAULT) {
			this.ivs.set(stat, ivs[stat.ordinal()]);
			this.evs.set(stat, evs[stat.ordinal()]);
		}
		
		this.nature = nature;
		
		calculateStats();
		
		currHp = stats.get(Stat.HP);
		
		moves = new PokemonMove[4];
	}
	
	public void setExpPoints(int exp) {
		expPoints = exp;
		level = species.growth_rate.getLevel(exp);
	}
	
	public boolean addExpPoints(int exp) {
		if(level >= 100) {
			return false; // already max level
		}
		
		expPoints += exp;
		if(species.growth_rate.getExperience(level + 1) < expPoints) {
			level++; return true;
		}
		
		return false;
	}
	
	public int getCurrentExp() {
		return expPoints - species.growth_rate.getExperience(level);
	}
	
	public void calculateStats() {
		Stat[] statKeys = Stat.values();
		StatData base = pokemon.base;
		if(pokemon.identifier.equals("shedinja")) stats.set(Stat.HP, 1);
		else stats.set(Stat.HP, (int) Math.floor(0.01 * (2 * base.get(Stat.HP) + ivs.get(Stat.HP) + Math.floor(0.25 * evs.get(Stat.HP))) * level) + level + 10);
		for(int x = 1; x < 6; x++) {
			stats.set(statKeys[x], (int) Math.floor(
					(Math.floor(level / 100f * (2 * base.get(statKeys[x]) + ivs.get(statKeys[x]) + Math.floor(0.25 * evs.get(statKeys[x])) ) ) + 5) *
					(1 + (nature.getIncrease() == statKeys[x] ? 0.1 : 0) - (nature.getDecrease() == statKeys[x] ? 0.1 : 0)) ));
		}
	}
	
	public double getIVPercentage() {
		int total = 0;
		for(Stat stat : Stat.DEFAULT) {
			total += ivs.get(stat);
		}
		
		return total / 1.86;
	}
	
	public void learnMove(MoveData move, int slot) {
		PokemonMove m = new PokemonMove(move);
		
		for(int x = 0; x < 4; x++) {
			if(moves[x] == null) {
				moves[x] = m;
				return;
			}
		}
		
		m.currPP = Math.min(m.currPP, moves[slot].currPP);
		moves[slot] = m;
	}
	
	public String getTypeName(Language lang) {
		String name = form.name.get(lang);
		if(name == null) name = species.name.get(lang);
		return name;
	}
	
	public String getName(Language lang) {
		if(nickname != null && !nickname.isEmpty()) return '"' + nickname + '"';
		return getTypeName(lang);
	}
}
