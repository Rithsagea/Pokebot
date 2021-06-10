package com.rithsagea.pokebot;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.rithsagea.pokebot.types.LearnedMove;
import com.rithsagea.pokebot.types.RegistryMove;
import com.rithsagea.pokebot.types.RegistryPokemon;
import com.rithsagea.pokebot.types.RegistrySpecies;

public class Registry {
	
	private static final String POKEMON_CSV = "resources/csv/table/pokemon.csv";
	private static final String SPECIES_CSV = "resources/csv/table/pokemon_species.csv";
	private static final String MOVES_CSV = "resources/csv/table/moves.csv";
	private static final String POKE_MOVES_CSV = "resources/csv/table/pokemon_moves.csv";
	
	private static HashMap<Integer, RegistryPokemon> pokeReg;
	private static HashMap<Integer, RegistrySpecies> specReg;
	private static HashMap<Integer, RegistryMove> moveReg;
	
	public static void init() {
		List<String[]> data;
		RegistryPokemon p;
		int i;
		
		specReg = new HashMap<>();
		data = Util.readCsv(new File(SPECIES_CSV)); data.remove(0);
		for(String[] line : data) specReg.put(Util.parseInt(line[0]), new RegistrySpecies(line));
		
		pokeReg = new HashMap<>();
		data = Util.readCsv(new File(POKEMON_CSV)); data.remove(0);
		for(String[] line : data) pokeReg.put(Util.parseInt(line[0]), new RegistryPokemon(line));
		
		moveReg = new HashMap<>();
		data = Util.readCsv(new File(MOVES_CSV)); data.remove(0);
		for(String[] line : data) moveReg.put(Util.parseInt(line[0]), new RegistryMove(line));
	
		data = Util.readCsv(new File(POKE_MOVES_CSV)); data.remove(0);
		for(String[] line: data) {
			p = pokeReg.get(Util.parseInt(line[0]));
			i = Util.parseInt(line[1]); // version_id
			if(!p.moveset.containsKey(i)) p.moveset.put(i, new HashSet<>());
			p.moveset.get(i).add(new LearnedMove(line));
		}
	}
	
	public static RegistryPokemon getPokemon(int id) {
		return pokeReg.get(id);
	}
	
	public static RegistrySpecies getSpecies(int id) {
		return specReg.get(id);
	}
	
	public static RegistryMove getMove(int id) {
		return moveReg.get(id);
	}
}
