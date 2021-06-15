package com.pokebot.types.data;

import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.pokebot.json.LanguageStringAdapter;
import com.pokebot.json.StatDataAdapter;
import com.pokebot.types.LanguageString;

public class DeserializeTest {
	public static void main(String[] args) {
		Gson g = new GsonBuilder()
				.registerTypeAdapter(new TypeToken<LanguageString>() {}.getType(), new LanguageStringAdapter())
				.registerTypeAdapter(new TypeToken<StatData>() {}.getType(), new StatDataAdapter())
				.create();
		
		SpeciesData[] d = g.fromJson(new InputStreamReader(DeserializeTest.class.getResourceAsStream("species.json")), SpeciesData[].class);
		System.out.println(d[349].growth_rate);
		
		PokemonData[] p = g.fromJson(new InputStreamReader(DeserializeTest.class.getResourceAsStream("pokemon.json")), PokemonData[].class);
		System.out.println(p[349].species);
		
		FormData[] f = g.fromJson(new InputStreamReader(DeserializeTest.class.getResourceAsStream("form.json")), FormData[].class);
		System.out.println(f[349].form_identifier);
		
		MoveData[] m = g.fromJson(new InputStreamReader(DeserializeTest.class.getResourceAsStream("move.json")), MoveData[].class);
		System.out.println(m[150].identifier);
		
		AbilityData[] a = g.fromJson(new InputStreamReader(DeserializeTest.class.getResourceAsStream("ability.json")), AbilityData[].class);
		System.out.println(a[100].identifier);
		
		
	}
}
