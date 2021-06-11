package com.rithsagea.pokebot.test;

import com.rithsagea.pokebot.Registry;
import com.rithsagea.pokebot.types.registry.RegistryMove;
import com.rithsagea.pokebot.types.registry.RegistryPokemon;

public class MovesetTest {
	public static void main(String[] args) {
		Registry.init();
		RegistryPokemon p = Registry.getPokemon(350);
		for(RegistryMove m : p.learnableMoves) {
			System.out.println(m.identifier);
		}
	}
}
