package com.pokebot.types;

public enum DamageClass {
	STATUS(":sparkles:"),
	PHYSICAL(":crossed_swords:"),
	SPECIAL(":cyclone:");
	
	private String icon;
	DamageClass(String icon)  {
		this.icon = icon;
	}
	
	public String getIcon() {
		return icon;
	}
}
