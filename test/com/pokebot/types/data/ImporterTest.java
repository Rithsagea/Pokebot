package com.pokebot.types.data;

import java.io.File;

import com.pokebot.json.importer.PokemonImporter;
import com.pokebot.json.importer.SpeciesImporter;

public class ImporterTest {
	public static void main(String[] args) {
		String rscPath = "resources/com/pokebot/types/data/";
		
		SpeciesImporter s = new SpeciesImporter(new File("python/species"));
		s.run(new File(rscPath + "species.json"));
		
		PokemonImporter p = new PokemonImporter(new File("python/pokemon"));
		p.run(new File(rscPath + "pokemon.json"));
	}
}
