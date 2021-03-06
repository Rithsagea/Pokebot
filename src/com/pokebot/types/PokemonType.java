package com.pokebot.types;

public enum PokemonType {
	BUG,
	DARK,
	DRAGON,
	ELECTRIC,
	FAIRY,
	FIGHTING,
	FIRE,
	FLYING,
	GHOST,
	GRASS,
	GROUND,
	ICE,
	NORMAL,
	POISON,
	PSYCHIC,
	ROCK,
	STEEL,
	WATER;
	
	private static final double[][] EFFECTIVENESS_TABLE = {
		{1,2,1,1,0.5,0.5,0.5,0.5,0.5,2,1,1,1,0.5,2,1,0.5,1},
		{1,0.5,1,1,0.5,0.5,1,1,2,1,1,1,1,1,2,1,1,1},
		{1,1,2,1,0,1,1,1,1,1,1,1,1,1,1,1,0.5,1},
		{1,1,0.5,0.5,1,1,1,2,1,0.5,0,1,1,1,1,1,1,2},
		{1,2,2,1,1,2,0.5,1,1,1,1,1,1,0.5,1,1,0.5,1},
		{0.5,2,1,1,0.5,1,1,0.5,0,1,1,2,2,0.5,0.5,2,2,1},
		{2,1,0.5,1,1,1,0.5,1,1,2,1,2,1,1,1,0.5,2,0.5},
		{2,1,1,0.5,1,2,1,1,1,2,1,1,1,1,1,0.5,0.5,1},
		{1,0.5,1,1,1,1,1,1,2,1,1,1,0,1,2,1,1,1},
		{0.5,1,0.5,1,1,1,0.5,0.5,1,0.5,2,1,1,0.5,1,2,0.5,2},
		{0.5,1,1,2,1,1,2,0,1,0.5,1,1,1,2,1,2,2,1},
		{1,1,2,1,1,1,0.5,2,1,2,2,0.5,1,1,1,1,0.5,0.5},
		{1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,0.5,0.5,1},
		{1,1,1,1,2,1,1,1,0.5,2,0.5,1,1,0.5,1,0.5,0,1},
		{1,0,1,1,1,2,1,1,1,1,1,1,1,2,0.5,1,0.5,1},
		{2,1,1,1,1,0.5,2,2,1,1,0.5,2,1,1,1,1,0.5,1},
		{1,1,1,0.5,2,1,0.5,1,1,1,1,2,1,1,1,2,0.5,0.5},
		{1,1,0.5,1,1,1,2,1,1,0.5,2,1,1,1,1,2,1,0.5}
	};
	
	public static double getEffectiveness(PokemonType attacker, PokemonType defender) {
		if(attacker == null || defender == null) return 1;
		
		return EFFECTIVENESS_TABLE[attacker.ordinal()][defender.ordinal()];
	}
}
