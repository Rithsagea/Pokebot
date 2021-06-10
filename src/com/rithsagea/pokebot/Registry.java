package com.rithsagea.pokebot;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.rithsagea.pokebot.lang.LanguageString;
import com.rithsagea.pokebot.types.LearnedMove;
import com.rithsagea.pokebot.types.RegistryMove;
import com.rithsagea.pokebot.types.RegistryPokemon;
import com.rithsagea.pokebot.types.RegistrySpecies;

public class Registry {
	
	private static HashMap<Integer, RegistryPokemon> pokeReg;
	private static HashMap<Integer, RegistrySpecies> specReg;
	private static HashMap<Integer, RegistryMove> moveReg;
	
	public static void init() {
		List<String[]> data;
		RegistryPokemon p;
		RegistrySpecies s;
		int i, j;
		
		//	-=-=- Data -=-=-
		specReg = new HashMap<>();
		data = Util.readCsv(new File(Resources.SPECIES_CSV)); data.remove(0);
		for(String[] line : data) specReg.put(Util.parseInt(line[0]), new RegistrySpecies(line));
		
		pokeReg = new HashMap<>();
		data = Util.readCsv(new File(Resources.POKEMON_CSV)); data.remove(0);
		for(String[] line : data) pokeReg.put(Util.parseInt(line[0]), new RegistryPokemon(line));
		
		moveReg = new HashMap<>();
		data = Util.readCsv(new File(Resources.MOVES_CSV)); data.remove(0);
		for(String[] line : data) moveReg.put(Util.parseInt(line[0]), new RegistryMove(line));
	
		data = Util.readCsv(new File(Resources.POKE_MOVES_CSV)); data.remove(0);
		for(String[] line: data) {
			p = pokeReg.get(Util.parseInt(line[0]));
			i = Util.parseInt(line[1]); // version_id
			if(!p.moveset.containsKey(i)) p.moveset.put(i, new HashSet<>());
			p.moveset.get(i).add(new LearnedMove(line));
		}
		
		//	-=-=- Lang -=-=-
		data = Util.readCsv(new File(Resources.SPECIES_LANG)); data.remove(0);
		for(String[] line : data) {
			s = specReg.get(Util.parseInt(line[0]));
			i = Util.parseInt(line[1]); // lang
			s.name.set(i, line[2]);
			s.genus.set(i, line[3]);
		}
		
		data = Util.readCsv(new File(Resources.SPECIES_FLAVOR_LANG)); data.remove(0);
		for(String[] line : data) {
			s = specReg.get(Util.parseInt(line[0]));
			i = Util.parseInt(line[1]); // version_id
			j = Util.parseInt(line[2]); // language_id
			if(!s.flavor.containsKey(i)) s.flavor.put(i, new LanguageString());
			s.flavor.get(i).set(j, line[3]);
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
