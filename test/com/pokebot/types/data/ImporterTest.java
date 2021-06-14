package com.pokebot.types.data;

import java.io.File;

import com.pokebot.json.SpeciesImporter;

public class ImporterTest {
	public static void main(String[] args) {
		String rscPath = "resources/com/pokebot/types/data/";
		
		SpeciesImporter s = new SpeciesImporter(new File("python/species"));
		s.run(new File(rscPath + "species.json"));
	}
}
