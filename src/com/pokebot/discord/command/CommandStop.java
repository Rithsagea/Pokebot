package com.pokebot.discord.command;

import java.io.File;

import com.pokebot.discord.Command;
import com.pokebot.game.PokemonManager;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class CommandStop implements Command {

	private PokemonManager pokemonManager;
	
	public CommandStop(PokemonManager pokemonManager) {
		this.pokemonManager = pokemonManager;
	}
	
	@Override
	public String getLabel() {
		return "stop";
	}

	@Override
	public String[] getAliases() {
		return null;
	}

	@Override
	public void onCommand(Message msg, User sender, MessageChannel chann, String[] args) {
		if(sender.getIdLong() == 171378138041942016l) {
			pokemonManager.save(new File("userdata.json"));
			System.exit(0);
		}
	}

}
