package com.pokebot.json;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import com.pokebot.game.Pokemon;
import com.pokebot.game.PokemonMove;
import com.pokebot.json.importer.ClassExclusionStrategy;
import com.pokebot.json.importer.JsonUtil;
import com.pokebot.types.data.AbilityData;
import com.pokebot.types.data.DataRegistry;
import com.pokebot.types.data.FormData;
import com.pokebot.types.data.PokemonData;
import com.pokebot.types.data.SpeciesData;
import com.pokebot.types.data.StatData;

public class PokemonAdapter implements JsonSerializer<Pokemon>, JsonDeserializer<Pokemon> {

	private DataRegistry registry;
	private Gson g;
	
	public PokemonAdapter() {
		registry = DataRegistry.getInstance();
		
		g = new GsonBuilder()
				.addDeserializationExclusionStrategy(new ClassExclusionStrategy(SpeciesData.class))
				.addDeserializationExclusionStrategy(new ClassExclusionStrategy(PokemonData.class))
				.addDeserializationExclusionStrategy(new ClassExclusionStrategy(FormData.class))
				.addDeserializationExclusionStrategy(new ClassExclusionStrategy(AbilityData.class))
				.addSerializationExclusionStrategy(new ClassExclusionStrategy(SpeciesData.class))
				.addSerializationExclusionStrategy(new ClassExclusionStrategy(PokemonData.class))
				.addSerializationExclusionStrategy(new ClassExclusionStrategy(FormData.class))
				.addSerializationExclusionStrategy(new ClassExclusionStrategy(AbilityData.class))
				.registerTypeAdapter(new TypeToken<PokemonMove>() {}.getType(), new PokemonMoveAdapter())
				.registerTypeAdapter(new TypeToken<StatData>() {}.getType(), new StatDataAdapter())
				.create();
	}
	
	@Override
	public Pokemon deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		Pokemon p = g.fromJson(json, Pokemon.class);
		
		p.species = registry.getSpecies(JsonUtil.getString(json, "species"));
		p.pokemon = registry.getPokemon(JsonUtil.getString(json, "pokemon"));
		p.form = registry.getForm(JsonUtil.getString(json, "form"));
		p.ability = registry.getAbility(JsonUtil.getString(json, "ability"));
		
		return p;
	}

	@Override
	public JsonElement serialize(Pokemon src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject obj = g.toJsonTree(src).getAsJsonObject();
		
		obj.addProperty("species", src.species.identifier);
		obj.addProperty("pokemon", src.pokemon.identifier);
		obj.addProperty("form", src.form.identifier);
		obj.addProperty("ability", src.ability.identifier);
		
		return obj;
	}

}
