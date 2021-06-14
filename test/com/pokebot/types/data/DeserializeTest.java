package com.pokebot.types.data;

import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DeserializeTest {
	public static void main(String[] args) {
		Gson g = new GsonBuilder().create();
		InputStreamReader isr = new InputStreamReader(DeserializeTest.class.getResourceAsStream("species.json"));
		System.out.println(isr);
		SpeciesData[] d = g.fromJson(isr, SpeciesData[].class);
		System.out.println(d[349].identifier);
	}
}
