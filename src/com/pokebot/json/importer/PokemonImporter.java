package com.pokebot.json.importer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.pokebot.types.PokemonType;
import com.pokebot.types.Stat;
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
				.addDeserializationExclusionStrategy(new FieldExclusionStrategy("forms"))
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
			Stat stat = null;
			switch(JsonUtil.getString(s, "stat/name")) {
				case "hp": stat = Stat.HP; break;
				case "attack": stat = Stat.ATK; break;
				case "defense": stat = Stat.DEF; break;
				case "special-attack": stat = Stat.SPA; break;
				case "special-defense": stat = Stat.SPD; break;
				case "speed": stat = Stat.SPE; break;
			}
			p.base.set(stat, s.getAsJsonObject().get("base_stat").getAsInt());
			p.effort.set(stat, s.getAsJsonObject().get("effort").getAsInt());
		}
		
		for(JsonElement t : o.get("types").getAsJsonArray()) {
			PokemonType type = PokemonType.valueOf(t.getAsJsonObject().get("type").getAsJsonObject().get("name").getAsString().toUpperCase());
			switch(t.getAsJsonObject().get("slot").getAsInt()) {
				case 1: p.type1 = type; break;
				case 2: p.type2 = type; break;
			}
		}
		
		p.species = JsonUtil.get(o, "species/name").getAsString();
		
		p.identifier = o.get("name").getAsString();
		
		ArrayList<String> f = new ArrayList<>();
		for(JsonElement t : o.get("forms").getAsJsonArray()) {
			f.add(t.getAsJsonObject().get("name").getAsString());
		}
		
		p.forms = f.toArray(new String[0]);
		
		VersionGroup n, v = VersionGroup.RED_BLUE;
		for(JsonElement m : o.get("moves").getAsJsonArray()) {
			for(JsonElement l : m.getAsJsonObject().get("version_group_details").getAsJsonArray()) {
				n = VersionGroup.valueOf(JsonUtil.getString(l, "version_group/name").toUpperCase().replace('-', '_'));
				if(n.ordinal() > v.ordinal()) v = n;
			}
		}
		
		HashMap<Integer, ArrayList<String>> level_move = new HashMap<>();
		ArrayList<String> egg_move = new ArrayList<>();
		ArrayList<String> tutor_move = new ArrayList<>();
		ArrayList<String> machine_move = new ArrayList<>();
		
		String v_str = v.name().toLowerCase().replace('_', '-');
		for(JsonElement m : o.get("moves").getAsJsonArray()) {
			String move = JsonUtil.getString(m, "move/name");
			for(JsonElement l : m.getAsJsonObject().get("version_group_details").getAsJsonArray()) {
				switch(JsonUtil.getString(l, "move_learn_method/name")) {
					case "level-up": 
						if(JsonUtil.getString(l, "version_group/name").equals(v_str)) {
							int level = l.getAsJsonObject().get("level_learned_at").getAsInt();
							if(!level_move.containsKey(level)) level_move.put(level, new ArrayList<>());
							level_move.get(level).add(move);
						}
						break;
					case "egg": egg_move.add(move); break;
					case "tutor": tutor_move.add(move); break;
					case "machine": machine_move.add(move); break;
				}
			}
		}
		
		p.level_moves = new LinkedHashMap<>();
		level_move.keySet().stream().sorted().forEach((level) -> {
			p.level_moves.put(level, level_move.get(level).stream().distinct().toArray(String[]::new));
		});
		p.egg_moves = egg_move.stream().distinct().sorted().toArray(String[]::new);
		p.tutor_moves = tutor_move.stream().distinct().sorted().toArray(String[]::new);
		p.machine_moves = machine_move.stream().distinct().sorted().toArray(String[]::new);
		
		return p;
	}
	
	private String verify(JsonElement elem) {
		if(elem == null || elem.isJsonNull()) return null;
		return elem.getAsString();
	}

}
