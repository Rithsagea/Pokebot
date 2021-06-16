package com.pokebot.types;

import java.awt.Color;

public enum PokemonColor {
	BLACK(Color.BLACK),
	BLUE(Color.BLUE),
	BROWN(new Color(165, 42, 42)),
	GRAY(Color.GRAY),
	GREEN(Color.GREEN),
	PINK(Color.PINK),
	PURPLE(Color.MAGENTA),
	RED(Color.RED),
	WHITE(Color.WHITE),
	YELLOW(Color.YELLOW);
	
	private Color embedColor;
	PokemonColor(Color embedColor) {
		this.embedColor = embedColor;
	}
	
	public Color getColor() {
		return embedColor;
	}
}
