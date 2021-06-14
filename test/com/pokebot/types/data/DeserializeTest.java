package com.pokebot.types.data;

import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class DeserializeTest {
	public static void main(String[] args) {
		Gson g = new GsonBuilder()
				.registerTypeAdapter(new TypeToken<LanguageString>() {}.getType(), new LanguageStringAdapter())
				.create();
		SpeciesData[] d = g.fromJson(new InputStreamReader(DeserializeTest.class.getResourceAsStream("species.json")), SpeciesData[].class);
		System.out.println(d[349].growth_rate);
	}
}
