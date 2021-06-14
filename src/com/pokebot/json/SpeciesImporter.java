package com.pokebot.json;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.pokebot.types.EggGroup;
import com.pokebot.types.GrowthRate;
import com.pokebot.types.Habitat;
import com.pokebot.types.Language;
import com.pokebot.types.LanguageString;
import com.pokebot.types.PokemonColor;
import com.pokebot.types.PokemonShape;
import com.pokebot.types.data.SpeciesData;

public class SpeciesImporter extends DataImporter<SpeciesData> {
	
	private Gson g;
	
	public SpeciesImporter(File path) {
		super(path);
		
		g = new GsonBuilder()
				.addDeserializationExclusionStrategy(new ClassExclusionStrategy(PokemonColor.class))
				.addDeserializationExclusionStrategy(new ClassExclusionStrategy(PokemonShape.class))
				.addDeserializationExclusionStrategy(new ClassExclusionStrategy(EggGroup.class))
				.addDeserializationExclusionStrategy(new ClassExclusionStrategy(GrowthRate.class))
				.addDeserializationExclusionStrategy(new ClassExclusionStrategy(Habitat.class))
				.addDeserializationExclusionStrategy(new ClassExclusionStrategy(LanguageString.class))
				.addDeserializationExclusionStrategy(new FieldExclusionStrategy("evolves_from_species"))
				.addDeserializationExclusionStrategy(new FieldExclusionStrategy("evolution_chain"))
				.create();
	}

	@Override
	protected SpeciesData construct(JsonObject o) {
		SpeciesData d = g.fromJson(o, SpeciesData.class);
		JsonElement e;
		
		d.identifier = o.get("name").getAsString();
		
		d.color = PokemonColor.valueOf(o.get("color").getAsJsonObject().get("name").getAsString().toUpperCase());
		
		List<EggGroup> eggGroups = new ArrayList<>();
		for(JsonElement obj : o.get("egg_groups").getAsJsonArray())
			eggGroups.add(EggGroup.valueOf(obj.getAsJsonObject().get("name").getAsString().toUpperCase().replace('-', '_')));
		d.egg_groups = eggGroups;
		
		d.genus = new LanguageString();
		for(JsonElement obj : o.get("genera").getAsJsonArray())
			d.genus.set(Language.valueOf(obj.getAsJsonObject().get("language").getAsJsonObject().get("name").getAsString().replace('-', '_')),
					obj.getAsJsonObject().get("genus").getAsString());
		
		e = o.getAsJsonObject().get("evolves_from_species");
		d.evolves_from_species = e.isJsonNull() ? null : e.getAsJsonObject().get("name").getAsString();
		
		d.growth_rate = GrowthRate.valueOf(o.get("growth_rate").getAsJsonObject().get("name").getAsString().toUpperCase().replace('-', '_'));
		
		e = o.get("habitat");
		d.habitat = e.isJsonNull() ? null : Habitat.valueOf(e.getAsJsonObject().get("name").getAsString().toUpperCase().replace('-', '_'));
		
		d.name = new LanguageString();
		for(JsonElement obj : o.get("names").getAsJsonArray())
			d.name.set(Language.valueOf(obj.getAsJsonObject().get("language").getAsJsonObject().get("name").getAsString().replace('-', '_')),
					obj.getAsJsonObject().get("name").getAsString());
		
		d.shape = PokemonShape.valueOf(o.get("shape").getAsJsonObject().get("name").getAsString().toUpperCase().replace('-','_'));
		
		String[] s = o.get("evolution_chain").getAsJsonObject().get("url").getAsString().split("/");
		d.evolution_chain = Integer.parseInt(s[s.length - 1]);
		
		return d;
	}
}
