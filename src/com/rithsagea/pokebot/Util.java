package com.rithsagea.pokebot;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.opencsv.CSVReader;
import com.rithsagea.pokebot.types.Pokemon;
import com.rithsagea.pokebot.types.constants.EggGroups;
import com.rithsagea.pokebot.types.constants.Gender;
import com.rithsagea.pokebot.types.constants.Stat;
import com.rithsagea.pokebot.types.registry.LearnedMove;
import com.rithsagea.pokebot.types.registry.RegistryPokemon;

public class Util {
	public static List<String[]> readCsv(File file) {
		List<String[]> res = null;
		CSVReader r;
		try {
			r = new CSVReader(new FileReader(file));
			res = r.readAll(); r.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public static int parseInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	// https://stackoverflow.com/a/31656679
	public static List<Integer> getRandomUnique(int min, int max, int num) {
		List<Integer> range = IntStream.range(min, max).boxed()
				.collect(Collectors.toCollection(ArrayList::new));
		Collections.shuffle(range);
		return range.subList(0, num);
	}
	
	// Pokemon Generation Tools
	public static Pokemon generatePokemon(RegistryPokemon species, int level) {
		Pokemon p = new Pokemon();
		p.setLevel(level);
		
		// -=-=- Specifics -=-=-
		int movePos = 0;
		for(LearnedMove m : p.pokemonType.levelUpMoves) {
			if(m.level <= level) { p.learnMove(m.move, movePos); movePos++; }
			if(movePos >= 4) break;
		}
		
		if(p.pokemonType.abilities[2] != null && Math.random() < 0.01) {
			p.ability = p.pokemonType.abilities[2];
		}
		
		// -=-=- Misc -=-=-
		if(Math.random() < 1/4096) 
			p.isShiny = true;
		
		if(p.pokemonType.species.gender_rate == -1)
			p.gender = Gender.GENDERLESS;
		else
			p.gender = (Math.random() < (p.pokemonType.species.gender_rate / 8))
							? Gender.FEMALE : Gender.MALE;

		p.friendship = 70;
		
		// -=-=- Stats -=-=-
		for(int x = 0; x < 6; x++) {
			p.ivs[x] = (int) (32 * Math.random());
			p.evs[x] = 0;
		}
		
		if(p.pokemonType.species.egg_groups.contains(EggGroups.NO_EGGS)) {
			for(int iv : getRandomUnique(0, 6, 3))
				p.ivs[iv] = 31;
		}
		
		p.nature = (int)(25 * Math.random()) + 1;
		p.calculateStats();
		p.currHp = p.stats[Stat.HP];
		
		return p;
	}
}
