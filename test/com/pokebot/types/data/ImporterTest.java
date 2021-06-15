package com.pokebot.types.data;

import java.io.File;

import com.pokebot.json.importer.AbilityImporter;
import com.pokebot.json.importer.FormImporter;
import com.pokebot.json.importer.MoveImporter;
import com.pokebot.json.importer.PokemonImporter;
import com.pokebot.json.importer.SpeciesImporter;

public class ImporterTest {
	public static void main(String[] args) {
		String rscPath = "resources/com/pokebot/types/data/";
		
		SpeciesImporter s = new SpeciesImporter(new File("python/species"));
		s.run(new File(rscPath + "species.json"));
		
		PokemonImporter p = new PokemonImporter(new File("python/pokemon"));
		p.run(new File(rscPath + "pokemon.json"));
		
		AbilityImporter a = new AbilityImporter(new File("python/ability"));
		a.run(new File(rscPath + "ability.json"));
		
		MoveImporter m = new MoveImporter(new File("python/move"));
		m.run(new File(rscPath + "move.json"));
		
		FormImporter f = new FormImporter(new File("python/form"));
		f.run(new File(rscPath + "form.json"));
	}
}
