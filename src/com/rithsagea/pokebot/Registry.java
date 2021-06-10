package com.rithsagea.pokebot;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import com.rithsagea.pokebot.types.RegistryPokemon;
import com.rithsagea.pokebot.types.RegistrySpecies;

public class Registry {
	
	private static final String POKEMON_CSV = "resources/csv/table/pokemon.csv";
	private static final String SPECIES_CSV = "resources/csv/table/pokemon_species.csv";
	
	private static HashMap<Integer, RegistryPokemon> pokeReg;
	private static HashMap<Integer, RegistrySpecies> specReg;
	
	public static void init() {
		List<String[]> data;
		
		pokeReg = new HashMap<>();
		data = Util.readCsv(new File(POKEMON_CSV)); data.remove(0);
		for(String[] line : data) pokeReg.put(Util.parseInt(line[0]), new RegistryPokemon(line));
	
		specReg = new HashMap<>();
		data = Util.readCsv(new File(SPECIES_CSV)); data.remove(0);
		for(String[] line : data) specReg.put(Util.parseInt(line[0]), new RegistrySpecies(line));
	}
}
