package com.rithsagea.pokebot.types.constants;

import java.io.File;
import java.util.List;

import com.rithsagea.pokebot.Resources;
import com.rithsagea.pokebot.Util;

public class GrowthRate {
	
	public static final int SLOW = 1;
	
	public static final int MEDIUM_FAST = 2;
	
	public static final int FAST = 3;
	
	public static final int MEDIUM_SLOW = 4;
	
	public static final int ERRATIC = 5;
	
	public static final int FLUCTUATING = 6;
	
	private static final int EXPERIENCE_TABLE[][] = new int[6][100];
	static {
		List<String[]> data = Util.readCsv(new File(Resources.EXPERIENCE_CSV));
		data.remove(0);
		for(int x = 0; x < 100; x++) {
			for(int y = 0; y < 6; y++) {
				EXPERIENCE_TABLE[y][x] = Integer.parseInt(data.get(x)[y + 1]);
			}
		}
	}
	
	public static int getExperience(int growthRate, int level) {
		return EXPERIENCE_TABLE[growthRate - 1][level - 1];
	}
}
