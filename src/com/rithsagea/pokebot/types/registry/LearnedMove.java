package com.rithsagea.pokebot.types.registry;

import com.rithsagea.pokebot.Registry;
import com.rithsagea.pokebot.Util;

public class LearnedMove {
	public final RegistryMove move;
	public final int pokemon_move_method_id;
	public final int level;
	
	public LearnedMove(String[] s) {
		move = Registry.getMove(Util.parseInt(s[2]));
		pokemon_move_method_id = Util.parseInt(s[3]);
		level = Util.parseInt(s[4]);
	}
}
