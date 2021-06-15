package com.pokebot.json;

import com.google.gson.JsonElement;

public class JsonUtil {
	public static JsonElement get(JsonElement obj, String path) {
		if(obj.isJsonNull()) return null;
		int i = path.indexOf('/');
		if(i == -1) return obj.getAsJsonObject().get(path);
		
		return get(obj.getAsJsonObject().get(path.substring(0, i)), path.substring(i + 1));
	}
	
	public static String getString(JsonElement obj, String path) {
		JsonElement o = get(obj, path);
		if(o == null) return null;
		return o.getAsString();
	}
}
