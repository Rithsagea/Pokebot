package com.pokebot.game;

import com.pokebot.types.Language;
import com.pokebot.types.Stat;
import com.pokebot.types.data.DataRegistry;

public class PokemonGeneratorTest {
	public static void main(String[] args) {
		DataRegistry.getInstance().loadData();
		Pokemon p = PokemonGenerator.generatePokemon("sceptile", 50);
		Stat[] stats = {Stat.HP, Stat.ATK, Stat.DEF, Stat.SPA, Stat.SPD, Stat.SPE};
		Language l = Language.en;
		
		System.out.printf("%s #%d The %s\n", p.getName(l), p.species.id, p.species.genus.get(l));
		System.out.printf("Level: %d\nGender: %s\nFriendship: %d\nNature: %s\nHealth: %d\n", p.level, p.gender, p.friendship, p.nature, p.currHp);
		for(Stat stat : stats) {
			System.out.printf("%-3s %-3s %-8s %-8s\n", stat, "" + p.stats.get(stat),
					String.format("[%d/31]", p.ivs.get(stat)),
					String.format("[%d/255]", p.evs.get(stat)));
		}
		
		System.out.printf("\nAbility: %s\n", p.ability.name.get(l));
		for(int x = 0; x < 4; x++) {
			System.out.printf("%-15s [%d/%d]\n", p.moves[x].move.name.get(l),
								p.moves[x].currPP, p.moves[x].maxPP);
		}
	}
}
