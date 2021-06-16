package com.pokebot.discord.command;

import com.pokebot.discord.Command;
import com.pokebot.discord.EmbedUtil;
import com.pokebot.game.Pokemon;
import com.pokebot.game.PokemonGenerator;
import com.pokebot.game.PokemonManager;
import com.pokebot.types.Language;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class CommandStartPokemon implements Command {

	private static final String[] VALID_STARTERS = { 
			"bulbasaur", "charmander", "squirtle",
			"chikorita", "cyndaquil", "totodile",
			"treecko", "torchic", "mudkip",
			"turtwig", "chimchar", "piplup",
			"snivy", "tepig", "oshawott",
			"chespin", "fennekin", "froakie",
			"rowlet", "litten", "popplio",
			"grookey", "scorbunny", "sobble"
	};
	
	private static boolean isValid(String starter) {
		for(String s : VALID_STARTERS) {
			if(s.equals(starter.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	
	private PokemonManager pokemonManager;
	
	public CommandStartPokemon(PokemonManager pokemonManager) {
		this.pokemonManager = pokemonManager;
	}
	
	@Override
	public String getLabel() {
		return "start";
	}

	@Override
	public String[] getAliases() {
		return new String[] {"pick"};
	}

	@Override
	public void onCommand(Message msg, User sender, MessageChannel chann, String[] args) {
		if(pokemonManager.getUser(sender.getIdLong()) != null) {
			chann.sendMessage("You can only use this command once!").queue();
		} else {
			if(args.length < 2 || !isValid(args[1])) {
				chann.sendMessage(EmbedUtil.generateStarterEmbed(sender)).queue();
			} else {
				Pokemon p = PokemonGenerator.generatePokemon(args[1].toLowerCase(), 5);
				pokemonManager.createUser(sender.getIdLong()).party.add(p);
				chann.sendMessage("You picked " + p.getName(Language.en) + " as your first pokemon!").queue();
			}
		}
	}
}
