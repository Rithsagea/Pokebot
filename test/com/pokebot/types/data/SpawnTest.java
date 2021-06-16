package com.pokebot.types.data;

public class SpawnTest {
	public static void main(String[] args) {
		DataRegistry d = DataRegistry.getInstance();
		SpawnRegistry s = SpawnRegistry.getInstance();
		
		d.loadData();
		s.loadData();
		
		SpawnData data;
		for(int x = 0; x < 100; x++) {
			data = s.getSpawn();
			System.out.println(data.form + ": " + data.weight);
		}
		
		System.out.println("Total Weight: " + s.getTotalWeight());
	}
}
