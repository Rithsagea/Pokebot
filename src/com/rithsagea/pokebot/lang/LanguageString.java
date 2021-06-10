package com.rithsagea.pokebot.lang;

public class LanguageString {
	
	private final String data[] = new String[13];
	
	public LanguageString() {
		//literally nothing
	}
	
	public LanguageString(int lang, String str) {
		set(lang, str);
	}
	
	public void set(int lang, String str) {
		data[lang - 1] = str;
	}
	
	public String get(int lang) {
		return data[lang - 1];
	}
}
