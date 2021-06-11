package com.rithsagea.pokebot.test;

import com.rithsagea.pokebot.Registry;
import com.rithsagea.pokebot.Util;
import com.rithsagea.pokebot.lang.Language;
import com.rithsagea.pokebot.types.Pokemon;
import com.rithsagea.pokebot.types.constants.Gender;
import com.rithsagea.pokebot.types.constants.Nature;
import com.rithsagea.pokebot.types.constants.Stat;

public class PokeGenerationTest {
	public static void main(String[] args) {
		Registry.init();
		printPokemon(Util.generatePokemon(Registry.getPokemon(350), 10), Language.en_US);
	}
	
	public static void printPokemon(Pokemon p, int lang) {
		System.out.println(p.pokemonType.species);
		System.out.println("Level: " + p.level);
		System.out.printf("Health: %d/%d\n", p.currHp, p.stats[Stat.HP - 1]);
		System.out.println("Gender: " + Gender.getSymbol(p.gender));
		System.out.println("-=-=- Stats -=-=-");
		for(int x = 0; x < 6; x++) {
			System.out.printf("%-15s %d [%d/31][%d]\n", Stat.getName(x + 1, lang), p.stats[x],
											p.ivs[x], p.evs[x]);
		}
		System.out.println("Nature: " + Nature.getName(p.nature, lang));
		
		System.out.println("-=-=- Misc -=-=-");
		System.out.println("Ability: " + p.ability.name.get(lang));
		for(int x = 0; x < 4; x++) {
			System.out.printf("Move %d: [%d/%d] %s\n", x + 1, p.currPP[x], p.maxPP[x], p.moves[x].name.get(lang));
		}
	}
}
