package com.pokebot.json;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.pokebot.types.Stat;
import com.pokebot.types.data.StatData;

public class StatDataAdapter implements JsonSerializer<StatData>, JsonDeserializer<StatData> {

	@Override
	public StatData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject obj = json.getAsJsonObject();
		StatData data = new StatData();
		for(String stat : obj.keySet()) {
			data.set(Stat.valueOf(stat), obj.get(stat).getAsInt());
		}
		
		return data;
	}

	@Override
	public JsonElement serialize(StatData src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject obj = new JsonObject();
		for(Stat stat : Stat.values()) {
			int val = src.get(stat);
			if(val != -1) {
				obj.addProperty(stat.name(), val);
			}
		}
		
		return obj;
	}

}
