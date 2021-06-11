package com.rithsagea.pokebot.types.constants;

import java.io.File;
import java.util.List;

import com.rithsagea.pokebot.Resources;
import com.rithsagea.pokebot.Util;
import com.rithsagea.pokebot.lang.LanguageString;

public class Stat {
	public static final int HP = 1;
	
	public static final int ATK = 2;
	
	public static final int DEF = 3;
	
	public static final int SPA = 4;
	
	public static final int SPD = 5;
	
	public static final int SPE = 6;
	
	public static final int ACC = 7; //accuracy
	
	public static final int EVA = 8; //evasion
	
	private static final LanguageString[] STAT_NAMES = new LanguageString[8];
	static {
		List<String[]> data = Util.readCsv(new File(Resources.STAT_LANG));
		for(int i = 0; i < STAT_NAMES.length; i++) STAT_NAMES[i] = new LanguageString();
		for(String[] line : data) STAT_NAMES[Util.parseInt(line[0]) - 1].set(Util.parseInt(line[1]), line[2]);
	}
	
	public static String getName(int stat, int lang) {
		return STAT_NAMES[stat - 1].get(lang);
	}
}
