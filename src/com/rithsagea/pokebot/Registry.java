package com.rithsagea.pokebot;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.rithsagea.pokebot.lang.LanguageString;
import com.rithsagea.pokebot.types.constants.MoveLearnMethod;
import com.rithsagea.pokebot.types.registry.LearnedMove;
import com.rithsagea.pokebot.types.registry.RegistryAbility;
import com.rithsagea.pokebot.types.registry.RegistryMove;
import com.rithsagea.pokebot.types.registry.RegistryPokemon;
import com.rithsagea.pokebot.types.registry.RegistrySpecies;

public class Registry {
	
	private static HashMap<Integer, RegistryPokemon> pokeReg;
	private static HashMap<Integer, RegistrySpecies> specReg;
	private static HashMap<Integer, RegistryMove> moveReg;
	private static HashMap<Integer, RegistryAbility> abilityReg;
	
	public static void init() {
		
		initSpecies();
		initMoves();
		initAbilities();
		
		initLang();
	}
	
	private static void initSpecies() {
		List<String[]> data;
		
		specReg = new HashMap<>();
		data = Util.readCsv(new File(Resources.SPECIES_CSV)); data.remove(0);
		for(String[] line : data) specReg.put(Util.parseInt(line[0]), new RegistrySpecies(line));
		
		pokeReg = new HashMap<>();
		data = Util.readCsv(new File(Resources.POKEMON_CSV)); data.remove(0);
		for(String[] line : data) pokeReg.put(Util.parseInt(line[0]), new RegistryPokemon(line));
		
		data = Util.readCsv(new File(Resources.EGG_GROUPS_CSV)); data.remove(0);
		for(String[] line : data) {
			specReg.get(Util.parseInt(line[0])).egg_groups.add(Util.parseInt(line[1]));
		}
	}
	
	private static void initMoves() {
		List<String[]> data;
		int i, j;
		
		moveReg = new HashMap<>();
		data = Util.readCsv(new File(Resources.MOVES_CSV)); data.remove(0);
		for(String[] line : data) moveReg.put(Util.parseInt(line[0]), new RegistryMove(line));
	
		data = Util.readCsv(new File(Resources.POKE_MOVES_CSV)); data.remove(0);
		HashMap<Integer, HashMap<Integer, Set<LearnedMove>>> movesets = new HashMap<>();
		HashMap<Integer, Set<LearnedMove>> moveset;
		Set<LearnedMove> moves;
		for(String[] line: data) {
			i = Util.parseInt(line[0]);
			j = Util.parseInt(line[1]); // version_id
			if(!movesets.containsKey(i)) movesets.put(i, new HashMap<>()); //species does not exist
			if(!movesets.get(i).containsKey(j)) movesets.get(i).put(j, new HashSet<>());
			movesets.get(i).get(j).add(new LearnedMove(line));
		}
		for(RegistryPokemon poke : pokeReg.values()) {
			moveset = movesets.get(poke.id);
			moves = moveset.get(Collections.max(moveset.keySet()));
			for(LearnedMove move : moves) {
				if(move.pokemon_move_method_id == MoveLearnMethod.LEVEL_UP)
					pokeReg.get(poke.id).levelUpMoves.add(move);
			}
			
			for(Set<LearnedMove> m : moveset.values()) {
				for(LearnedMove move : m) {
					switch(move.pokemon_move_method_id) {
						case MoveLearnMethod.EGG:
							poke.eggMoves.add(move.move);
							break;
						case MoveLearnMethod.MACHINE:
						case MoveLearnMethod.TUTOR:
							poke.learnableMoves.add(move.move);
					}
				}
			}
		}
	}
	
	private static void initAbilities() {
		List<String[]> data;
		
		abilityReg = new HashMap<>();
		data = Util.readCsv(new File(Resources.ABILITY_CSV)); data.remove(0);
		for(String[] line : data) {
			abilityReg.put(Util.parseInt(line[0]), new RegistryAbility(line));
		}
		
		data = Util.readCsv(new File(Resources.POKE_ABILITY_CSV)); data.remove(0);
		for(String[] line : data) {
			pokeReg.get(Util.parseInt(line[0])).abilities[Util.parseInt(line[3])] =
					abilityReg.get(Util.parseInt(line[1]));
		}
	}
	
	private static void initLang() {
		List<String[]> data;
		RegistrySpecies s;
		int i, j;
		
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
	
	public static RegistryAbility getAbility(int id) {
		return abilityReg.get(id);
	}
}
