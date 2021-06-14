package com.pokebot.json.importer;

import java.io.File;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.pokebot.json.ClassExclusionStrategy;
import com.pokebot.json.DataImporter;
import com.pokebot.json.FieldExclusionStrategy;
import com.pokebot.types.PokemonType;
import com.pokebot.types.data.PokemonData;
import com.pokebot.types.data.SpriteData;
import com.pokebot.types.data.StatData;

public class PokemonImporter extends DataImporter<PokemonData> {

	private Gson gson;
	
	public PokemonImporter(File path) {
		super(path);
		
		gson = new GsonBuilder()
				.addDeserializationExclusionStrategy(new FieldExclusionStrategy("ability1"))
				.addDeserializationExclusionStrategy(new FieldExclusionStrategy("ability2"))
				.addDeserializationExclusionStrategy(new FieldExclusionStrategy("abilityh"))
				.addDeserializationExclusionStrategy(new FieldExclusionStrategy("species"))
				.addDeserializationExclusionStrategy(new ClassExclusionStrategy(SpriteData.class))
				.addDeserializationExclusionStrategy(new ClassExclusionStrategy(PokemonType.class))
				.create();
	}

	@Override
	protected PokemonData construct(JsonObject o) {
		PokemonData p = gson.fromJson(o, PokemonData.class);
		
		for(JsonElement a : o.get("abilities").getAsJsonArray()) {
			String name = a.getAsJsonObject().get("ability").getAsJsonObject().get("name").getAsString();
			switch(a.getAsJsonObject().get("slot").getAsInt()) {
				case 1: p.ability1 = name; break;
				case 2: p.ability2 = name; break;
				case 3: p.abilityh = name; break;
			}
		}
		
		p.sprites = new SpriteData();
		JsonObject spriteData = o.get("sprites").getAsJsonObject();
		p.sprites.front_default = verify(spriteData.get("front_default"));
		p.sprites.front_shiny = verify(spriteData.get("front_shiny"));
		p.sprites.front_female = verify(spriteData.get("front_female"));
		p.sprites.front_female_shiny = verify(spriteData.get("front_female_shiny"));
		
		p.sprites.back_default = verify(spriteData.get("back_default"));
		p.sprites.back_shiny = verify(spriteData.get("back_shiny"));
		p.sprites.back_female = verify(spriteData.get("back_female"));
		p.sprites.back_female_shiny = verify(spriteData.get("back_female_shiny"));
		
		p.sprites.artwork = verify(spriteData.get("artwork"));
		
		p.base = new StatData();
		p.effort = new StatData();
		for(JsonElement s : o.get("stats").getAsJsonArray()) {
			int base = s.getAsJsonObject().get("base_stat").getAsInt();
			int effort = s.getAsJsonObject().get("effort").getAsInt();
			switch(s.getAsJsonObject().get("stat").getAsJsonObject().get("name").getAsString()) {
				case "hp": p.base.hp = base; p.effort.hp = effort; break;
				case "atk": p.base.atk = base; p.effort.atk = effort; break;
				case "def": p.base.def = base; p.effort.def = effort; break;
				case "spa": p.base.spa = base; p.effort.spa = effort; break;
				case "spd": p.base.spd = base; p.effort.spd = effort; break;
				case "spe": p.base.spe = base; p.effort.spe = effort; break;
			}
		}
		
		for(JsonElement t : o.get("types").getAsJsonArray()) {
			PokemonType type = PokemonType.valueOf(t.getAsJsonObject().get("type").getAsJsonObject().get("name").getAsString().toUpperCase());
			switch(t.getAsJsonObject().get("slot").getAsInt()) {
				case 1: p.type1 = type; break;
				case 2: p.type2 = type; break;
			}
		}
		
		p.species = o.get("species").getAsJsonObject().get("name").getAsString();
		
		p.identifier = o.get("name").getAsString();
		
		return p;
	}
	
	private String verify(JsonElement elem) {
		if(elem == null || elem.isJsonNull()) return null;
		return elem.getAsString();
	}

}
