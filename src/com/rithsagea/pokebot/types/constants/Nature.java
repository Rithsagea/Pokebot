package com.rithsagea.pokebot.types.constants;

import java.io.File;
import java.util.List;

import com.rithsagea.pokebot.Resources;
import com.rithsagea.pokebot.Util;
import com.rithsagea.pokebot.lang.LanguageString;

public class Nature {
	
	public static final int HARDY = 1;
	public static final int BOLD = 2;
	public static final int MODEST = 3;
	public static final int CALM = 4;
	public static final int TIMID = 5;
	public static final int LONELY = 6;
	public static final int DOCILE = 7;
	public static final int MILD = 8;
	public static final int GENTLE = 9;
	public static final int HASTY = 10;
	public static final int ADAMANT = 11;
	public static final int IMPISH = 12;
	public static final int BASHFUL = 13;
	public static final int CAREFUL = 14;
	public static final int RASH = 15;
	public static final int JOLLY = 16;
	public static final int NAUGHTY = 17;
	public static final int LAX = 18;
	public static final int QUIRKY = 19;
	public static final int NAIVE = 20;
	public static final int BRAVE = 21;
	public static final int RELAXED = 22;
	public static final int QUIET = 23;
	public static final int SASSY = 24;
	public static final int SERIOUS = 25;
	
	private static final int NATURE_TABLE[][] = new int[25][4];
	private static final LanguageString[] NATURE_NAMES = new LanguageString[25];
	static {
		List<String[]> data = Util.readCsv(new File(Resources.NATURE_CSV)); data.remove(0);
		int i;
		for(String[] line : data) {
			i = Util.parseInt(line[0]) - 1;
			NATURE_TABLE[i][0] = Util.parseInt(line[1]); //decr
			NATURE_TABLE[i][1] = Util.parseInt(line[2]); //incr
			NATURE_TABLE[i][2] = Util.parseInt(line[3]); //hate
			NATURE_TABLE[i][3] = Util.parseInt(line[4]); //like
		}
		
		data = Util.readCsv(new File(Resources.NATURE_LANG)); data.remove(0);
		for(i = 0; i < NATURE_NAMES.length; i++) NATURE_NAMES[i] = new LanguageString();
		for(String[] line : data) NATURE_NAMES[Util.parseInt(line[0]) - 1].set(Util.parseInt(line[1]), line[2]);
	}
	
	public static int getDecrStat(int nature) {
		return NATURE_TABLE[nature - 1][0];
	}
	
	public static int getIncrStat(int nature) {
		return NATURE_TABLE[nature - 1][1];
	}
	
	public static int getHateFlavor(int nature) {
		return NATURE_TABLE[nature - 1][2];
	}
	
	public static int getLikeFlavor(int nature) {
		return NATURE_TABLE[nature - 1][3];
	}
	
	public static String getName(int nature, int lang) {
		return NATURE_NAMES[nature - 1].get(lang);
	}
}
