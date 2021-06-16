package com.pokebot.json;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.pokebot.game.PokemonMove;
import com.pokebot.json.importer.ClassExclusionStrategy;
import com.pokebot.json.importer.JsonUtil;
import com.pokebot.types.data.DataRegistry;
import com.pokebot.types.data.MoveData;

public class PokemonMoveAdapter implements JsonSerializer<PokemonMove>, JsonDeserializer<PokemonMove> {
	
	private DataRegistry registry;
	private Gson g;
	
	public PokemonMoveAdapter() {
		registry = DataRegistry.getInstance();
		
		g = new GsonBuilder()
				.addDeserializationExclusionStrategy(new ClassExclusionStrategy(MoveData.class))
				.addSerializationExclusionStrategy(new ClassExclusionStrategy(MoveData.class))
				.create();
	}

	@Override
	public PokemonMove deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		PokemonMove move = g.fromJson(json, PokemonMove.class);
		move.move = registry.getMove(JsonUtil.getString(json, "move"));
		return move;
	}

	@Override
	public JsonElement serialize(PokemonMove src, Type typeOfSrc, JsonSerializationContext context) {
		if(src == null) return JsonNull.INSTANCE;
		
		JsonObject obj = g.toJsonTree(src).getAsJsonObject();
		obj.addProperty("move", src.move.identifier);
		return obj;
	}
}
