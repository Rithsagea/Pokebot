package com.pokebot.discord.command;

import com.pokebot.discord.Command;
import com.pokebot.game.PokemonManager;
import com.pokebot.game.PokemonUser;
import com.pokebot.types.Language;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class CommandSelectPokemon implements Command {

	private PokemonManager pokemonManager;
	
	public CommandSelectPokemon(PokemonManager pokemonManager) {
		this.pokemonManager = pokemonManager;
	}
	
	@Override
	public String getLabel() {
		return "select";
	}

	@Override
	public String[] getAliases() {
		return null;
	}

	@Override
	public void onCommand(Message msg, User sender, MessageChannel chann, String[] args) {
		PokemonUser user = pokemonManager.getUser(sender.getIdLong());
		if(user != null) {
			if(user.party.get(Integer.parseInt(args[1])) != null) {
				user.selectedPokemon = Integer.parseInt(args[1]);
				chann.sendMessage("You have selected " + user.party.get(user.selectedPokemon).getName(Language.en) + " to be your main pokemon.").queue();
			}	
		}
	}
	
}
