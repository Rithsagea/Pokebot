package com.rithsagea.pokebot.test;

import com.rithsagea.pokebot.types.Pokemon;
import com.rithsagea.pokebot.types.Species;
import com.rithsagea.pokebot.types.registry.Registry;

public class RegistryTest {
	public static void main(String[] args) {
		Registry<Species> r1 = new Registry<>(Species.class, "resources/csv/table/pokemon_species.csv");
		Species s = r1.getMember(382);
		System.out.println(s.identifier);
		
		Registry<Pokemon> r2 = new Registry<>(Pokemon.class, "resources/csv/table/pokemon.csv");
		Pokemon p = r2.getMember(10077);
		System.out.println(p.identifier);
	}
}
