package com.pokebot.json.importer;

import java.io.File;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.pokebot.types.DamageClass;
import com.pokebot.types.Language;
import com.pokebot.types.LanguageString;
import com.pokebot.types.MoveTarget;
import com.pokebot.types.PokemonType;
import com.pokebot.types.data.MoveData;

public class MoveImporter extends DataImporter<MoveData> {

	private Gson g;
	
	public MoveImporter(File path) {
		super(path);
		
		g = new GsonBuilder()
				.addDeserializationExclusionStrategy(new ClassExclusionStrategy(DamageClass.class))
				.addDeserializationExclusionStrategy(new ClassExclusionStrategy(LanguageString.class))
				.addDeserializationExclusionStrategy(new ClassExclusionStrategy(MoveTarget.class))
				.addDeserializationExclusionStrategy(new ClassExclusionStrategy(PokemonType.class))
				.create();
	}

	@Override
	protected MoveData construct(JsonObject o) {
		MoveData m = g.fromJson(o, MoveData.class);
		
		String s = JsonUtil.getString(o, "damage_class/name");
		if(s != null)
			m.damage_class = DamageClass.valueOf(s.toUpperCase());
		
		for(JsonElement e : o.get("effect_entries").getAsJsonArray()) {
			if(JsonUtil.getString(e, "language/name").equals("en")) {
				m.effect_entry = JsonUtil.getString(e, "effect");
				m.short_effect = JsonUtil.getString(e, "short_effect");
			}
		}
		
		m.flavor_text = new LanguageString();
		VersionGroup n, v = VersionGroup.RED_BLUE;
		for(JsonElement e : o.get("flavor_text_entries").getAsJsonArray()) {
			n = VersionGroup.valueOf(JsonUtil.getString(e, "version_group/name").toUpperCase().replace('-', '_'));
			if(n.ordinal() > v.ordinal()) v = n;
		}
		
		String v_str = v.name().toLowerCase().replace('_', '-');
		for(JsonElement e : o.get("flavor_text_entries").getAsJsonArray()) {
			if(JsonUtil.getString(e, "version_group/name").equals(v_str)) {
				m.flavor_text.set(Language.valueOf(JsonUtil.getString(e, "language/name").replace('-', '_')), JsonUtil.getString(e, "flavor_text"));
			}
		}
		
		m.name = new LanguageString();
		for(JsonElement e : o.get("names").getAsJsonArray()) {
			m.name.set(Language.valueOf(JsonUtil.getString(e, "language/name").replace('-', '_')), JsonUtil.getString(e, "name"));
		}
		
		m.identifier = JsonUtil.getString(o, "name");
		
		m.move_target = MoveTarget.valueOf(JsonUtil.getString(o, "target/name").toUpperCase().replace('-', '_').replace('+', '_'));
		
		try {
			m.type = PokemonType.valueOf(JsonUtil.getString(o, "type/name").toUpperCase());
		} catch(IllegalArgumentException e) {
			return null;
		}
		
		return m;
	}

}
