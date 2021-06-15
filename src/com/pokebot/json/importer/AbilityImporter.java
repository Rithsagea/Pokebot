package com.pokebot.json.importer;

import java.io.File;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.pokebot.json.DataImporter;
import com.pokebot.json.FieldExclusionStrategy;
import com.pokebot.json.JsonUtil;
import com.pokebot.types.Language;
import com.pokebot.types.LanguageString;
import com.pokebot.types.data.AbilityData;

public class AbilityImporter extends DataImporter<AbilityData> {

	private Gson g;
	
	public AbilityImporter(File path) {
		super(path);
		
		g = new GsonBuilder()
				.addDeserializationExclusionStrategy(new FieldExclusionStrategy("name"))
				.create();
	}

	@Override
	protected AbilityData construct(JsonObject o) {
		AbilityData a = g.fromJson(o, AbilityData.class);
		
		for(JsonElement e : o.get("effect_entries").getAsJsonArray()) {
			if(JsonUtil.getString(e, "language/name").equals("en")) {
				a.effect_entry = e.getAsJsonObject().get("effect").getAsString();
				a.short_effect = e.getAsJsonObject().get("short_effect").getAsString();
			}
		}
		
		a.name = new LanguageString();
		for(JsonElement e : o.get("names").getAsJsonArray()) {
			a.name.set(
					Language.valueOf(JsonUtil.getString(e, "language/name").replace('-', '_')),
					JsonUtil.getString(e, "name"));
		}
		
		a.flavor_text = new LanguageString();
		VersionGroup n, v = VersionGroup.RED_BLUE;
		for(JsonElement e : o.get("flavor_text_entries").getAsJsonArray()) {
			n = VersionGroup.valueOf(JsonUtil.getString(e, "version_group/name").toUpperCase().replace('-', '_'));
			if(n.ordinal() > v.ordinal()) v = n;
		}
		
		String v_str = v.name().toLowerCase().replace('_', '-');
		for(JsonElement e : o.get("flavor_text_entries").getAsJsonArray()) {
			if(JsonUtil.getString(e, "version_group/name").equals(v_str)) {
				a.flavor_text.set(Language.valueOf(JsonUtil.getString(e, "language/name").replace('-', '_')), JsonUtil.getString(e, "flavor_text"));
			}
		}
		
		a.identifier = o.get("name").getAsString();
		
		return a;
	}

}
