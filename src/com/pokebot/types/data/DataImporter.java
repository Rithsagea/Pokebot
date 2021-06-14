package com.pokebot.types.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class DataImporter {
	private static final String SPECIES_PATH = "python/species/";
	
	public static void main(String[] args) throws FileNotFoundException {
		HashMap<String, SpeciesData> speciesLookup;
		
		JsonElement e;
		SpeciesData d;
		for(File f : new File(SPECIES_PATH).listFiles()) {
			e = JsonParser.parseReader(new FileReader(f));
			d = new SpeciesData();
			
			System.out.println(e.getClass());
		}
	}
}
