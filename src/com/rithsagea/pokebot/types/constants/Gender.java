package com.rithsagea.pokebot.types.constants;

public class Gender {
	public static final int FEMALE = 1;
	public static final int MALE = 2;
	public static final int GENDERLESS = 3;
	
	public static String getSymbol(int gender) {
		switch(gender) {
			case FEMALE: return "♀";
			case MALE: return "♂";
		}
		return "";
	}
}
