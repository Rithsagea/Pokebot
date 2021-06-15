package com.pokebot.json.importer;

import java.io.File;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.pokebot.types.Language;
import com.pokebot.types.LanguageString;
import com.pokebot.types.PokemonType;
import com.pokebot.types.data.FormData;
import com.pokebot.types.data.SpriteData;

public class FormImporter extends DataImporter<FormData> {

	private Gson g;
	
	public FormImporter(File path) {
		super(path);
		
		g = new GsonBuilder()
				.addDeserializationExclusionStrategy(new FieldExclusionStrategy("name"))
				.addDeserializationExclusionStrategy(new FieldExclusionStrategy("form_name"))
				.addDeserializationExclusionStrategy(new FieldExclusionStrategy("pokemon"))
				.create();
	}

	@Override
	protected FormData construct(JsonObject o) {
		FormData f = g.fromJson(o, FormData.class);

		f.form_identifier = JsonUtil.getString(o, "form_name");
		f.identifier = JsonUtil.getString(o, "name");
		
		f.form_name = new LanguageString();
		for(JsonElement e : o.get("form_names").getAsJsonArray())
			f.form_name.set(Language.valueOf(JsonUtil.getString(e, "language/name").replace('-', '_')), JsonUtil.getString(e, "name"));
		
		f.name = new LanguageString();
		for(JsonElement e : o.get("names").getAsJsonArray())
			f.name.set(Language.valueOf(JsonUtil.getString(e, "language/name").replace('-', '_')), JsonUtil.getString(e, "name"));
		
		try {
			for(JsonElement e : o.get("types").getAsJsonArray()) {
				PokemonType t = PokemonType.valueOf(JsonUtil.getString(e, "type/name").toUpperCase());
				switch(e.getAsJsonObject().get("slot").getAsInt()) {
					case 1: f.type1 = t; break;
					case 2: f.type2 = t; break;
				}
			}
		} catch(IllegalArgumentException e) {
			return null;
		}
		
		f.pokemon = JsonUtil.getString(o, "pokemon/name");
		
		f.sprites = new SpriteData();
		JsonObject spriteData = o.get("sprites").getAsJsonObject();
		f.sprites.front_default = verify(spriteData.get("front_default"));
		f.sprites.front_shiny = verify(spriteData.get("front_shiny"));
		f.sprites.front_female = verify(spriteData.get("front_female"));
		f.sprites.front_female_shiny = verify(spriteData.get("front_female_shiny"));
		
		f.sprites.back_default = verify(spriteData.get("back_default"));
		f.sprites.back_shiny = verify(spriteData.get("back_shiny"));
		f.sprites.back_female = verify(spriteData.get("back_female"));
		f.sprites.back_female_shiny = verify(spriteData.get("back_female_shiny"));
		
		return f;
	}
	
	private String verify(JsonElement elem) {
		if(elem == null || elem.isJsonNull()) return null;
		return elem.getAsString();
	}

}
