package com.pokebot.discord.command;

import com.pokebot.discord.Command;
import com.pokebot.game.PokemonManager;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class CommandRedirect implements Command {

	private PokemonManager pokemonManager;
	
	public CommandRedirect(PokemonManager pokemonManager) {
		this.pokemonManager = pokemonManager;
	}
	
	@Override
	public String getLabel() {
		return "redirect";
	}

	@Override
	public String[] getAliases() {
		return null;
	}

	@Override
	public void onCommand(Message msg, User sender, MessageChannel chann, String[] args) {
		if(msg.getGuild().getMember(sender).hasPermission(Permission.ADMINISTRATOR)) {
			pokemonManager.setSpawnChannel(msg.getGuild().getIdLong(), chann.getIdLong());
			chann.sendMessage("Pokemon spawns will now be redirected to this channel!").queue();
		}
	}

}
