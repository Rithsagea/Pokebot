package com.pokebot.game;

import com.pokebot.types.data.DataRegistry;

public class LevelUpTest {
	private static void print(Pokemon p) {
		System.out.printf("Level: %d Exp: %d (%d/%d)\n", p.level, p.expPoints, p.getCurrentExp(), p.species.growth_rate.getNextLevelExp(p.level));
	}
	
	public static void main(String[] args) {
		DataRegistry data = DataRegistry.getInstance();
		data.loadData();
		
		Pokemon p = PokemonGenerator.generatePokemon("charmander", 3);
		
		print(p);
		p.addExpPoints(20);
		print(p);
		p.addExpPoints(20);
		print(p);
	}
}
