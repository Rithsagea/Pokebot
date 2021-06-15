package com.pokebot.types.data;

import java.io.InputStreamReader;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.pokebot.json.LanguageStringAdapter;
import com.pokebot.json.StatDataAdapter;
import com.pokebot.types.LanguageString;

public class DataRegistry {
	
	private HashMap<String, SpeciesData> speciesMap;
	private HashMap<String, PokemonData> pokemonMap;
	private HashMap<String, FormData> formMap;
	private HashMap<String, MoveData> moveMap;
	private HashMap<String, AbilityData> abilityMap;
	
	public DataRegistry() {
		speciesMap = new HashMap<>();
		pokemonMap = new HashMap<>();
		formMap = new HashMap<>();
		moveMap = new HashMap<>();
		abilityMap = new HashMap<>();
	}
	
	public void loadData() {
		Gson g = new GsonBuilder()
				.registerTypeAdapter(new TypeToken<LanguageString>() {}.getType(), new LanguageStringAdapter())
				.registerTypeAdapter(new TypeToken<StatData>() {}.getType(), new StatDataAdapter())
				.create();
		
		SpeciesData[] s = g.fromJson(new InputStreamReader(DeserializeTest.class.getResourceAsStream("species.json")), SpeciesData[].class);
		PokemonData[] p = g.fromJson(new InputStreamReader(DeserializeTest.class.getResourceAsStream("pokemon.json")), PokemonData[].class);
		FormData[] f = g.fromJson(new InputStreamReader(DeserializeTest.class.getResourceAsStream("form.json")), FormData[].class);
		MoveData[] m = g.fromJson(new InputStreamReader(DeserializeTest.class.getResourceAsStream("move.json")), MoveData[].class);
		AbilityData[] a = g.fromJson(new InputStreamReader(DeserializeTest.class.getResourceAsStream("ability.json")), AbilityData[].class);
		
		for(SpeciesData d : s) speciesMap.put(d.identifier, d);
		for(PokemonData d : p) pokemonMap.put(d.identifier, d);
		for(FormData d : f) formMap.put(d.identifier, d);
		for(MoveData d : m) moveMap.put(d.identifier, d);
		for(AbilityData d : a) abilityMap.put(d.identifier, d);
	}
	
	public SpeciesData getSpecies(String identifier) {
		return speciesMap.get(identifier);
	}
	
	public PokemonData getPokemon(String identifier) {
		return pokemonMap.get(identifier);
	}
	
	public FormData getForm(String identifier) {
		return formMap.get(identifier);
	}
	
	public MoveData getMove(String identifier) {
		return moveMap.get(identifier);
	}
	
	public AbilityData getAbility(String identifier) {
		return abilityMap.get(identifier);
	}
}
