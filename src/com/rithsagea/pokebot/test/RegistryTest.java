package com.rithsagea.pokebot.test;

import com.rithsagea.pokebot.types.RegistryPokemon;
import com.rithsagea.pokebot.types.RegistrySpecies;
import com.rithsagea.pokebot.types.registry.Registry;

public class RegistryTest {
	public static void main(String[] args) {
		Registry<RegistrySpecies> r1 = new Registry<>(RegistrySpecies.class, "resources/csv/table/pokemon_species.csv");
		RegistrySpecies s = r1.getMember(382);
		System.out.println(s.identifier);
		
		Registry<RegistryPokemon> r2 = new Registry<>(RegistryPokemon.class, "resources/csv/table/pokemon.csv");
		RegistryPokemon p = r2.getMember(10077);
		System.out.println(p.identifier);
	}
}
