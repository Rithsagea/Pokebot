package com.pokebot.types.data;

public class SpeciesData implements  Comparable<SpeciesData> {
	public int id;
	public String identifier;
	
	@Override
	public int compareTo(SpeciesData o) {
		return id - o.id;
	}
}
