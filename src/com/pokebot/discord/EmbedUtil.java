package com.pokebot.discord;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import com.pokebot.Util;
import com.pokebot.game.Pokemon;
import com.pokebot.game.PokemonMove;
import com.pokebot.types.Language;
import com.pokebot.types.Stat;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;

public class EmbedUtil {
	public static MessageEmbed generatePartyEmbed(Pokemon p, int id) {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setImage(p.pokemon.sprites.artwork);
		eb.setColor(p.species.color.getColor());
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
			if(move == null) {
				eb.addField("Empty Move Slot", " ", false);
			} else {
				eb.addField(String.format("%s %s [%d/%d]",
						move.move.damage_class.getIcon(),
						move.move.name.get(Language.en), move.currPP, move.maxPP),
						move.move.flavor_text.get(Language.en), false);
			}
		}
		
		eb.setFooter("ID: " + id);
		
		return eb.build();
	}

	public static MessageEmbed generateWildEmbed(Pokemon p) {
		EmbedBuilder eb = new EmbedBuilder();
		
		eb.setColor(p.species.color.getColor());
		eb.setTitle("A Wild Pokémon has Appeared!");
		eb.setDescription("Type p!catch to catch the pokemon!");
		eb.setImage(p.pokemon.sprites.artwork);
		
		return eb.build();
	}

	public static final String[][] VALID_STARTERS = {
			{"Bulbasaur", "Charmander", "Squirtle"},
			{"Chikorita", "Cyndaquil", "Totodile"},
			{"Treecko", "Torchic", "Mudkip"},
			{"Turtwig", "Chimchar", "Piplup"},
			{"Snivy", "Tepig", "Oshawott"},
			{"Chespin", "Fennekin", "Froakie"},
			{"Rowlet", "Litten", "Popplio"},
			{"Grookey", "Scorbunny", "Sobble"}
	};
	
	public static MessageEmbed generateStarterEmbed(User user) {
		EmbedBuilder eb = new EmbedBuilder();
		
		eb.setAuthor("Hello " + user.getName() + "!");
		eb.setTitle("Welcome to the world of pokémon!");
		eb.setDescription("To choose a starter, use the command p!pick <pokemon>");
		
		int i = 0;
		for(String[] gen : VALID_STARTERS) {
			i++;
			eb.addField("**Generation " + Util.toRoman(i) + "**", gen[0] + " | " + gen[1] + " | " + gen[2], false);
		}
		
		return eb.build();
	}

	public static MessageEmbed generatePartyEmbed(String title, LinkedHashMap<Integer, Pokemon> pokemon) {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle(title);
		
		StringBuilder b = new StringBuilder();
		
		Pokemon p;
		for(Entry<Integer, Pokemon> entry : pokemon.entrySet()) {
			p = entry.getValue();
			b.append(String.format("**%s** | Level: %d | ID: %d | IV: %.2f%%", p.getTypeName(Language.en), p.level, entry.getKey(), p.getIVPercentage()));
			if(p.nickname != null && !p.nickname.isEmpty()) b.append(" | Nickname: " + p.nickname);
			b.append("\n");
		}
		
		eb.setDescription(b.toString());
		
		return eb.build();
	}
	
	public static MessageEmbed generatePartyEmbed(String title, List<Pokemon> pokemon) {
		LinkedHashMap<Integer, Pokemon> map = new LinkedHashMap<>();
		for(int x = 0; x < pokemon.size(); x++) {
			map.put(x, pokemon.get(x));
		}
		
		return generatePartyEmbed(title, map);
	}
}
