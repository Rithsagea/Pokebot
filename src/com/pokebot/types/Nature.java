package com.pokebot.types;

public enum Nature {
	HARDY(Stat.ATK, Stat.ATK, Flavor.SPICY, Flavor.SPICY),
	LONELY(Stat.ATK, Stat.DEF, Flavor.SPICY, Flavor.SOUR),
	ADAMANT(Stat.ATK, Stat.SPA, Flavor.SPICY, Flavor.DRY),
	NAUGHTY(Stat.ATK, Stat.SPD, Flavor.SPICY, Flavor.BITTER),
	BRAVE(Stat.ATK, Stat.SPE, Flavor.SPICY, Flavor.SWEET),
	
	BOLD(Stat.DEF, Stat.ATK, Flavor.SOUR, Flavor.SPICY),
	DOCILE(Stat.DEF, Stat.DEF, Flavor.SOUR, Flavor.SOUR),
	IMPISH(Stat.DEF, Stat.SPA, Flavor.SOUR, Flavor.DRY),
	LAX(Stat.DEF, Stat.SPD, Flavor.SOUR, Flavor.BITTER),
	RELAXED(Stat.DEF, Stat.SPE, Flavor.SOUR, Flavor.SWEET),
	
	MODEST(Stat.SPA, Stat.ATK, Flavor.DRY, Flavor.SPICY),
	MILD(Stat.SPA, Stat.DEF, Flavor.DRY, Flavor.SOUR),
	BASHFUL(Stat.SPA, Stat.SPA, Flavor.DRY, Flavor.DRY),
	RASH(Stat.SPA, Stat.SPD, Flavor.DRY, Flavor.BITTER),
	QUIET(Stat.SPA, Stat.SPE, Flavor.DRY, Flavor.SWEET),
	
	CALM(Stat.SPD, Stat.ATK, Flavor.BITTER, Flavor.SPICY),
	GENTLE(Stat.SPD, Stat.DEF, Flavor.BITTER, Flavor.SOUR),
	CAREFUL(Stat.SPD, Stat.SPA, Flavor.BITTER, Flavor.DRY),
	QUIRKY(Stat.SPD, Stat.SPD, Flavor.BITTER, Flavor.BITTER),
	SASSY(Stat.SPD, Stat.SPE, Flavor.BITTER, Flavor.SWEET),
	
	TIMID(Stat.SPE, Stat.ATK, Flavor.SWEET, Flavor.SPICY),
	HASTY(Stat.SPE, Stat.DEF, Flavor.SWEET, Flavor.SOUR),
	JOLLY(Stat.SPE, Stat.SPA, Flavor.SWEET, Flavor.DRY),
	NAIVE(Stat.SPE, Stat.SPD, Flavor.SWEET, Flavor.BITTER),
	SERIOUS(Stat.SPE, Stat.SPE, Flavor.SWEET, Flavor.SWEET);
	
	private Stat increase;
	private Stat decrease;
	private Flavor like;
	private Flavor hate;
	
	Nature(Stat incr, Stat decr, Flavor like, Flavor hate) {
		increase = incr;
		decrease = decr;
		this.like = like;
		this.hate = hate;
	}
	
	public Stat getIncrease() {
		return increase;
	}
	
	public Stat getDecrease() {
		return decrease;
	}
	
	public Flavor getLike() {
		return like;
	}
	
	public Flavor getHate() {
		return hate;
	}
}
