package com.pokebot.types.data;

import java.util.HashMap;

import com.pokebot.types.Language;

public class LanguageString {
	private transient HashMap<Language, String> text;
	
	public LanguageString() {
		text = new HashMap<>();
	}
	
	public String get(Language lang) {
		return text.get(lang);
	}
	
	public void set(Language lang, String str) {
		text.put(lang, str);
	}
}
