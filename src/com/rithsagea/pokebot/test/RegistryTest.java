package com.rithsagea.pokebot.test;

import com.rithsagea.pokebot.Registry;
import com.rithsagea.pokebot.types.RegistryPokemon;

public class RegistryTest {
	public static void main(String[] args) {
		Registry.init();
		RegistryPokemon p = Registry.getPokemon(350);
		System.out.println(p.identifier);
	}
}
