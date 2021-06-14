package com.pokebot.types;

import java.util.HashMap;

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
