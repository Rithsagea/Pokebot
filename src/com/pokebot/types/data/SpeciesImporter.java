package com.pokebot.types.data;

import java.io.File;

import com.google.gson.JsonObject;

public class SpeciesImporter extends DataImporter<SpeciesData> {
	public SpeciesImporter(File path) {
		super(path);
	}

	@Override
	protected SpeciesData construct(JsonObject o) {
		SpeciesData d = new SpeciesData();
		
		d.id = o.get("id").getAsInt();
		d.identifier = o.get("name").getAsString();
		
		return d;
	}
}
