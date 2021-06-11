package com.rithsagea.pokebot.types.registry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.rithsagea.pokebot.Registry;
import com.rithsagea.pokebot.Util;

public class RegistryPokemon {
	
	public final int id;
	public final String identifier;
	public final RegistrySpecies species;
	
	public final int height;
	public final int weight;
	
	public final boolean is_default;
	
	public final int base_experience;
	public final int base[];
	public final int effort[];
	
	public final int type1;
	public final int type2;
	
	public final RegistryAbility[] abilities;
	
	public final ArrayList<LearnedMove> levelUpMoves;
	public final Set<RegistryMove> learnableMoves;
	public final Set<RegistryMove> eggMoves;
	
	public RegistryPokemon(String[] s) {
		id = Util.parseInt(s[0]);
		identifier = s[1];
		species = Registry.getSpecies(Util.parseInt(s[2]));
		height = Util.parseInt(s[3]);
		weight = Util.parseInt(s[4]);
		base_experience = Util.parseInt(s[5]);
		is_default = Util.parseInt(s[7]) == 1;
		
		base = new int[] {
				Util.parseInt(s[8]), Util.parseInt(s[9]), Util.parseInt(s[10]),
				Util.parseInt(s[11]), Util.parseInt(s[12]), Util.parseInt(s[13])
		};
		
		effort = new int[] {
				Util.parseInt(s[14]), Util.parseInt(s[15]), Util.parseInt(s[16]),
				Util.parseInt(s[17]), Util.parseInt(s[18]), Util.parseInt(s[19])
		};
		
		type1 = Util.parseInt(s[20]);
		type2 = Util.parseInt(s[21]);
		
		abilities = new RegistryAbility[3];
		
		levelUpMoves = new ArrayList<>();
		learnableMoves = new HashSet<>();
		eggMoves = new HashSet<>();
	}
}
