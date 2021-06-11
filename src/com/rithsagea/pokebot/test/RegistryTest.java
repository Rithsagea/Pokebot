package com.rithsagea.pokebot.test;

import com.rithsagea.pokebot.Registry;
import com.rithsagea.pokebot.lang.Language;
import com.rithsagea.pokebot.types.registry.RegistryPokemon;

public class RegistryTest {
	public static void main(String[] args) {
		Registry.init();
		RegistryPokemon p = Registry.getPokemon(350);
		System.out.println(p.species);
		
		for(int v : p.species.flavor.keySet()) {
			System.out.println("-=-=- " + v + " -=-=-\n" + p.species.flavor.get(v).get(Language.en_US));
		}
	}
}
