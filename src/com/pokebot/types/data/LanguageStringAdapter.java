package com.pokebot.types.data;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.pokebot.types.Language;

public class LanguageStringAdapter implements JsonSerializer<LanguageString>, JsonDeserializer<LanguageString> {

	@Override
	public JsonElement serialize(LanguageString src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject obj = new JsonObject();
		for(Language lang : Language.values()) {
			String val = src.get(lang);
			if(val != null) {
				obj.addProperty(lang.name(), val);
			}
		}
		
		return obj;
	}

	@Override
	public LanguageString deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject obj = json.getAsJsonObject();
		LanguageString str = new LanguageString();
		for(String lang : obj.keySet()) {
			str.set(Language.valueOf(lang), obj.get(lang).getAsString());
		}
		
		return str;
	}

}
