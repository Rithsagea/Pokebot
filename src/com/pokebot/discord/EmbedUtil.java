package com.pokebot.discord;

import java.awt.Color;

import com.pokebot.game.Pokemon;
import com.pokebot.game.PokemonMove;
import com.pokebot.types.Language;
import com.pokebot.types.Stat;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;

public class EmbedUtil {
	public static MessageEmbed generateEmbed(Pokemon p) {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setImage(p.pokemon.sprites.artwork);
		eb.setColor(Color.GREEN);
		eb.setTitle(String.format("Level %d %s", p.level, p.getName(Language.en)));
		
		StringBuilder db = new StringBuilder();
		db.append(String.format("%d/%dXP", p.getCurrentExp(),
				p.species.growth_rate.getNextLevelExp(p.level)));
		if(p.form.type2 == null) {
			db.append("\n**Type:** ");
			db.append(p.form.type1);
		} else {
			db.append("\n**Types:** ");
			db.append(p.form.type1);
			db.append(" | ");
			db.append(p.form.type2);
		}
		db.append("\n**Nature:** ");
		db.append(p.nature);
		
		eb.setDescription(db.toString());
		
		
		eb.addField(" ", "**HP**\n**Attack**\n**Defense**\n**Sp. Atk**\n**Sp. Def**\n**Speed**\n\n**Total IV %**", true);
		eb.addField("**Stats**", String.format("(%d/%d)\n%d\n%d\n%d\n%d\n%d\n\n%.2f%%",
				p.currHp, p.stats.get(Stat.HP), p.stats.get(Stat.ATK),
				p.stats.get(Stat.DEF), p.stats.get(Stat.SPA),
				p.stats.get(Stat.SPD), p.stats.get(Stat.SPE),
				p.getIVPercentage()), true);
		eb.addField("**[EVs] (IVs)**", String.format("[%d] (%d/31)\n[%d] (%d/31)\n[%d] (%d/31)\n[%d] (%d/31)\n[%d] (%d/31)\n[%d] (%d/31)",
				p.evs.get(Stat.HP), p.ivs.get(Stat.HP),
				p.evs.get(Stat.ATK), p.ivs.get(Stat.ATK),
				p.evs.get(Stat.DEF), p.ivs.get(Stat.DEF),
				p.evs.get(Stat.SPA), p.ivs.get(Stat.SPA),
				p.evs.get(Stat.SPD), p.ivs.get(Stat.SPD),
				p.evs.get(Stat.SPE), p.ivs.get(Stat.SPE)), true);
		
		for(PokemonMove move : p.moves) {
			eb.addField(String.format("%s %s [%d/%d]",
					move.move.damage_class.getIcon(),
					move.move.name.get(Language.en), move.currPP, move.maxPP),
					move.move.flavor_text.get(Language.en), false);
		}
		
		return eb.build();
	}
}
