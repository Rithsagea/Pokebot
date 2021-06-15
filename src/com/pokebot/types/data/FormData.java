package com.pokebot.types.data;

import com.pokebot.types.LanguageString;
import com.pokebot.types.PokemonType;

public class FormData implements Comparable<FormData> {
	public String identifier;
	public String form_identifier;
	public int id;
	public boolean is_battle_only;
	public boolean is_default;
	public boolean is_mega;
	
	public LanguageString form_name;
	public LanguageString name;
	
	public String pokemon;
	public SpriteData sprites;
	
	public PokemonType type1;
	public PokemonType type2;

	@Override
	public int compareTo(FormData o) {
		return id - o.id;
	}
}
