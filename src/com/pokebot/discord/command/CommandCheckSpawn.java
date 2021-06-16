package com.pokebot.discord.command;

import com.pokebot.discord.Command;
import com.pokebot.discord.EmbedUtil;
import com.pokebot.game.Pokemon;
import com.pokebot.game.PokemonManager;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class CommandCheckSpawn implements Command {

	private PokemonManager pokemonManager;
	
	public CommandCheckSpawn(PokemonManager pokemonManager) {
		this.pokemonManager = pokemonManager;
	}
	
	@Override
	public String getLabel() {
		return "check";
	}

	@Override
	public String[] getAliases() {
		return new String[] {"checkspawn"};
	}

	@Override
	public void onCommand(Message msg, User sender, MessageChannel chann, String[] args) {
		Pokemon p = pokemonManager.getPokemon(chann);
		if(p != null) chann.sendMessage(EmbedUtil.generateWildEmbed(p)).queue();
		else return;
	}

}
