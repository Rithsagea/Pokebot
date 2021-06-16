package com.pokebot.discord.command;

import com.pokebot.discord.Command;
import com.pokebot.discord.EmbedUtil;
import com.pokebot.game.PokemonManager;
import com.pokebot.game.PokemonUser;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class CommandCheckParty implements Command {

	private PokemonManager pokemonManager;
	
	public CommandCheckParty(PokemonManager pokemonManager) {
		this.pokemonManager = pokemonManager;
	}
	
	@Override
	public String getLabel() {
		return "party";
	}

	@Override
	public String[] getAliases() {
		return new String[] {"list"};
	}

	@Override
	public void onCommand(Message msg, User sender, MessageChannel chann, String[] args) {
		PokemonUser user = pokemonManager.getUser(sender.getIdLong());
		if(user == null) {
			chann.sendMessage("You don't have any pokémon, " + sender.getAsMention() + "! Use p!start to begin your journey!").queue();
			return;
		}
		
		chann.sendMessage(EmbedUtil.generatePartyEmbed("**Party Pokemon**", user.party)).queue();
	}
	
}
