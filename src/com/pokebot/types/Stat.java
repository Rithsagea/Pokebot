package com.pokebot.types;

public enum Stat {
	HP("Health"),
	ATK("Attack"),
	DEF("Defense"),
	SPA("Special Attack"),
	SPD("Special Defense"),
	SPE("Speed"),
	
	ACC("Accuarcy"),
	EVA("Evasion");
	
	public static final Stat[] DEFAULT = {HP, ATK, DEF, SPA, SPD, SPE};
	
	private String label;
	Stat(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
	
	public String toString() {
		return getLabel();
	}
}
