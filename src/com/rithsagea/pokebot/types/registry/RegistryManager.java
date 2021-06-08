package com.rithsagea.pokebot.types.registry;

import com.rithsagea.pokebot.types.RegistryPokemon;
import com.rithsagea.pokebot.types.RegistrySpecies;

public class RegistryManager {
	
	private static final String SPECIES_CSV = "resources/csv/table/pokemon_species.csv";
	private static final String POKEMON_CSV = "resources/csv/table/pokemon.csv";
	
	private Registry<RegistrySpecies> SPECIES_REGISTRY;
	private Registry<RegistryPokemon> POKEMON_REGISTRY;
	
	public RegistryManager() {
		SPECIES_REGISTRY = new Registry<>(RegistrySpecies.class, SPECIES_CSV);
		POKEMON_REGISTRY = new Registry<>(RegistryPokemon.class, POKEMON_CSV);
	}
}
