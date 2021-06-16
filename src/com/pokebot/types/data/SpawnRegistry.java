package com.pokebot.types.data;

import java.io.InputStreamReader;

import com.google.gson.Gson;

public class SpawnRegistry {
	
	private static SpawnRegistry INSTANCE = new SpawnRegistry();
	
	public static SpawnRegistry getInstance() {
		return INSTANCE;
	}
	
	private SpawnData[] data;
	private int totalWeight;
	
	private SpawnRegistry() {
		totalWeight = 0;
	}
	
	public void loadData() {
		Gson g = new Gson();
		data = g.fromJson(new InputStreamReader(SpawnRegistry.class.getResourceAsStream("spawn.json")), SpawnData[].class);
		for(SpawnData s : data) {
			totalWeight += s.weight;
		}
	}
	
	public int getTotalWeight() {
		return totalWeight;
	}
	
	public SpawnData getSpawn(int weight) {
		int pos = 0;
		while(weight > data[pos].weight) {
			weight -= data[pos].weight;
			pos++;
		}
		
		return data[pos];
	}
	
	public SpawnData getSpawn() {
		return getSpawn((int) (Math.random() * getTotalWeight()));
	}
}
