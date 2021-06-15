package com.pokebot.types.data;

import java.util.HashMap;

import com.pokebot.types.Stat;

public class StatData {
	private transient HashMap<Stat, Integer> stats;
	
	public StatData() {
		stats = new HashMap<>();
	}
	
	public int get(Stat stat) {
		if(!stats.containsKey(stat)) return -1; //error
		return stats.get(stat);
	}
	
	public void set(Stat stat, int val) {
		stats.put(stat, val);
	}
}
