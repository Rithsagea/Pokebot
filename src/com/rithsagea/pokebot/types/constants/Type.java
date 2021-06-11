package com.rithsagea.pokebot.types.constants;

import java.io.File;
import java.util.List;

import com.rithsagea.pokebot.Resources;
import com.rithsagea.pokebot.Util;
import com.rithsagea.pokebot.lang.LanguageString;

public class Type {
	
	public static final int NORMAL = 1;
	public static final int FIGHTING = 2;
	public static final int FLYING = 3;
	public static final int POISON = 4;
	public static final int GROUND = 5;
	public static final int ROCK = 6;
	public static final int BUG = 7;
	public static final int GHOST = 8;
	public static final int STEEL = 9;
	public static final int FIRE = 10;
	public static final int WATER = 11;
	public static final int GRASS = 12;
	public static final int ELECTRIC = 13;
	public static final int PSYCHIC = 14;
	public static final int ICE = 15;
	public static final int DRAGON = 16;
	public static final int DARK = 17;
	public static final int FAIRY = 18;
	
	private static final double MATCHUP_TABLE[][] = new double[18][18];
	private static final LanguageString[] TYPE_NAMES = new LanguageString[18];
	static {
		List<String[]> data = Util.readCsv(new File(Resources.EFFECTIVENESS_CSV)); data.remove(0);
		for(String[] matchup : data)
			MATCHUP_TABLE[Integer.parseInt(matchup[0]) - 1][Integer.parseInt(matchup[1]) - 1]
					= Integer.parseInt(matchup[2]) / 100d;
		
		data = Util.readCsv(new File(Resources.TYPE_LANG)); data.remove(0);
		for(int i = 0; i < TYPE_NAMES.length; i++) TYPE_NAMES[i] = new LanguageString();
		for(String[] line : data) TYPE_NAMES[Util.parseInt(line[0]) - 1].set(Util.parseInt(line[1]), line[2]);
	}
	
	public static double getMatchup(int attacker, int defender) {
		return MATCHUP_TABLE[attacker - 1][defender - 1];
	}
	
	public static String getName(int type, int lang) {
		return TYPE_NAMES[type - 1].get(lang);
	}
}
