package com.pokebot.discord.command;

import com.pokebot.discord.Command;
import com.pokebot.discord.EmbedUtil;
import com.pokebot.game.Pokemon;
import com.pokebot.game.PokemonGenerator;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class CommandGeneratePokemon implements Command {

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
		Pokemon p = PokemonGenerator.generatePokemon(args[1], Integer.parseInt(args[2]));
		
		chann.sendMessage(EmbedUtil.generateEmbed(p)).queue();
	}

}
