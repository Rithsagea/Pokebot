package com.pokebot.discord.command;

import com.pokebot.discord.Command;
import com.pokebot.game.PokemonGenerator;
import com.pokebot.game.PokemonManager;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class CommandGeneratePokemon implements Command {

	private PokemonManager pokemonManager;
	
	public CommandGeneratePokemon(PokemonManager pokemonManager) {
		this.pokemonManager = pokemonManager;
	}
	
	@Override
	public String getLabel() {
		return "pokegen";
	}

	@Override
	public String[] getAliases() {
		return null;
	}

	@Override
	public void onCommand(Message msg, User sender, MessageChannel chann, String[] args) {
		if(sender.getIdLong() != 171378138041942016l) return; //no permission
		
		switch(args.length) {
			case 1: pokemonManager.spawnPokemon(chann); break;
			default: pokemonManager.spawnPokemon(chann, PokemonGenerator.generatePokemon(args[1], Integer.parseInt(args[2]))); break;
		}
	}

}