package com.rithsagea.pokebot.types;

import com.rithsagea.pokebot.types.constants.GrowthRate;
import com.rithsagea.pokebot.types.constants.Nature;
import com.rithsagea.pokebot.types.constants.Stat;
import com.rithsagea.pokebot.types.registry.RegistryAbility;
import com.rithsagea.pokebot.types.registry.RegistryMove;
import com.rithsagea.pokebot.types.registry.RegistryPokemon;

public class Pokemon {
	public RegistryPokemon pokemonType;
	public boolean isShiny;
	public int experiencePoints;
	public int level;
	public int gender;
	public int friendship; //also egg steps
//	public int pokerus;
	
	public int ivs[] = new int[6];
	public int evs[] = new int[6];
	public int stats[] = new int[6];
	public int nature;
	public int currHp;
	
	public int heldItem; // id
	public RegistryMove[] moves = new RegistryMove[4];
//	public int relearnMoves[] = new int[4];
	public int[] currPP = new int[4];
	public int[] maxPP = new int[4];
	public RegistryAbility ability;
	
	public int otId;
	public String otName;
	public String nickname;
	
	public void setLevel(int level) {
		this.level = level;
		this.experiencePoints = GrowthRate.getExperience(pokemonType.species.growth_rate_id, level);
	}
	
	public void learnMove(RegistryMove move, int slot) {
		if(moves[slot] == null) currPP[slot] = move.pp;
		moves[slot] = move;
		maxPP[slot] = move.pp;
		currPP[slot] = Math.min(currPP[slot], move.pp);
	}
	
	public void calculateStats() {
		//TODO: Shedinja HP is always 1
		stats[Stat.HP - 1] = (int) (Math.floor((2 * pokemonType.base[Stat.HP - 1] + ivs[Stat.HP - 1] + Math.floor(evs[Stat.HP - 1] / 4)) * (level / 100)) + level + 10);
		for(int x = 1; x < 6; x++) {
			stats[x] = (int) (Math.floor((
						(Math.floor((2 * pokemonType.base[x] + ivs[x] + Math.floor(evs[x] / 4)) * level / 100) + 5))
					* (1 + (Nature.getIncrStat(nature) == (x + 1) ? 0.1 : 0)
						 + (Nature.getDecrStat(nature) == (x + 1) ? 0 : 0.1)) ));
		}
	}
}
