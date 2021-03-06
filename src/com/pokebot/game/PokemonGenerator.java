package com.pokebot.game;

import java.util.ArrayList;
import java.util.Collections;

import com.pokebot.types.EggGroup;
import com.pokebot.types.Gender;
import com.pokebot.types.Nature;
import com.pokebot.types.Stat;
import com.pokebot.types.data.DataRegistry;
import com.pokebot.types.data.SpawnData;
import com.pokebot.types.data.SpawnRegistry;

public class PokemonGenerator {
	public static Pokemon generatePokemon(String form, int level) {
		Pokemon pokemon = new Pokemon(form);
		DataRegistry dataRegistry = DataRegistry.getInstance();
		
		ArrayList<Integer> ivs = new ArrayList<>();
		
		if(pokemon.species.egg_groups.contains(EggGroup.NO_EGGS)) {
			for(int x = 0; x < 3; x++) {
				ivs.add(31);
				ivs.add((int)(Math.random() * 32));
			}
			Collections.shuffle(ivs);
		} else {
			for(int x = 0; x < 6; x++) ivs.add((int)(Math.random() * 32));
		}
		
		pokemon.setExpPoints(pokemon.species.growth_rate.getExperience(level));
		if(pokemon.species.gender_rate == -1) pokemon.gender = Gender.GENDERLESS;
		else if(Math.random() < (pokemon.species.gender_rate / 8)) pokemon.gender = Gender.FEMALE;
		else pokemon.gender = Gender.MALE;
		
		for(Stat stat : Stat.DEFAULT) pokemon.ivs.set(stat, ivs.get(stat.ordinal()));
		
		pokemon.nature = Nature.values()[(int) (Math.random() * 25)];
		
		pokemon.calculateStats();
		pokemon.currHp = pokemon.stats.get(Stat.HP);
		
		int slot = 0;
		for(int x = pokemon.level; x >= 0; x--) {
			if(!pokemon.pokemon.level_moves.containsKey(x)) continue;
			for(String move : pokemon.pokemon.level_moves.get(x)) {
				pokemon.learnMove(dataRegistry.getMove(move), slot);
				slot++;
				if(slot >= 4) break;
			}
			if(slot >= 4) break;
		}
		
		if(pokemon.pokemon.ability2 != null && Math.random() > 0.5)
			pokemon.ability = dataRegistry.getAbility(pokemon.pokemon.ability2);
		else
			pokemon.ability = dataRegistry.getAbility(pokemon.pokemon.ability1);
		
		return pokemon;
	}

	public static Pokemon generateWildPokemon() {
		SpawnData s = SpawnRegistry.getInstance().getSpawn();
		return generatePokemon(s.form, (int) (Math.random() * (s.max_level - s.min_level)) + s.min_level);
	}
}
