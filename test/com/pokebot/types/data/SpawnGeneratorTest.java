package com.pokebot.types.data;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.pokebot.types.EggGroup;
import com.pokebot.types.Stat;

public class SpawnGeneratorTest {
	private static int calc(int x) {
		return (int)(95d / (1d + Math.pow(Math.E, -(350d - x) / 50d)) + 5d);
	}
	
	public static void main(String[] args) throws JsonIOException, IOException {
		DataRegistry data = DataRegistry.getInstance();
		data.loadData();
		
		Collection<FormData> forms = data.getForms();
		ArrayList<SpawnData> spawns = new ArrayList<>();
		
		int baseStatSum;
		PokemonData p;
		SpeciesData s;
		SpawnData spawn;
		for(FormData f : forms) {
			p = data.getPokemon(f.pokemon);
			s = data.getSpecies(p.species);
			
			if(f.is_mega) continue;
			if(f.is_battle_only) continue;
			if(s.egg_groups.contains(EggGroup.NO_EGGS)) continue;
			
			baseStatSum = 0;
			for(Stat stat : Stat.DEFAULT) {
				baseStatSum += p.base.get(stat);
			}
			
			spawn = new SpawnData();
			spawn.form = f.identifier;
			spawn.min_level = 5;
			spawn.max_level = 45;
			spawn.weight = calc(baseStatSum);
			
			spawns.add(spawn);
		}
		
		Gson g = new GsonBuilder().setPrettyPrinting().create();
		FileWriter writer = new FileWriter("resources/com/pokebot/types/data/spawn.json");
		g.toJson(spawns, writer);
		writer.flush();
		writer.close();
	}
}
