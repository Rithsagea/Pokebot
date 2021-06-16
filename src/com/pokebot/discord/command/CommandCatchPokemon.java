package com.pokebot.discord.command;

import com.pokebot.discord.Command;
import com.pokebot.game.Pokemon;
import com.pokebot.game.PokemonManager;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class CommandCatchPokemon implements Command {

	private PokemonManager pokemonManager;
	
	public CommandCatchPokemon(PokemonManager pokemonManager) {
		this.pokemonManager = pokemonManager;
	}
	
	@Override
	public String getLabel() {
		return "catch";
	}

	@Override
	public String[] getAliases() {
		return null;
	}

	@Override
	public void onCommand(Message msg, User sender, MessageChannel chann, String[] args) {
		Pokemon p = pokemonManager.getPokemon(chann);
		if(p != null) pokemonManager.catchPokemon(chann, sender);
	}

}
