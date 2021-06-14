package com.pokebot.types.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.pokebot.json.ClassExclusionStrategy;
import com.pokebot.json.DataImporter;
import com.pokebot.types.EggGroup;
import com.pokebot.types.PokemonColor;

public class SpeciesImporter extends DataImporter<SpeciesData> {
	
	private Gson g;
	
	public SpeciesImporter(File path) {
		super(path);
		
		g = new GsonBuilder()
				.addDeserializationExclusionStrategy(new ClassExclusionStrategy(PokemonColor.class))
				.addDeserializationExclusionStrategy(new ClassExclusionStrategy(EggGroup.class))
				.create();
	}

	@Override
	protected SpeciesData construct(JsonObject o) {
		SpeciesData d = g.fromJson(o, SpeciesData.class);
		
		d.identifier = o.get("name").getAsString();
		
		d.color = PokemonColor.valueOf(o.get("color").getAsJsonObject().get("name").getAsString().toUpperCase());
		
		List<EggGroup> eggGroups = new ArrayList<>();
		for(JsonElement obj : o.get("egg_groups").getAsJsonArray())
			eggGroups.add(EggGroup.valueOf(obj.getAsJsonObject().get("name").getAsString().toUpperCase().replace('-', '_')));
		d.egg_groups = eggGroups.toArray(new EggGroup[0]);
		
		return d;
	}
}
