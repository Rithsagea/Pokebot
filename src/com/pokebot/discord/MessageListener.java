package com.pokebot.discord;

import javax.annotation.Nonnull;

import com.pokebot.game.PokemonManager;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {
	
	private CommandRegistry commandRegistry;
	private PokemonManager pokemonManager;
	
	public MessageListener(CommandRegistry commandRegistry, PokemonManager pokemonManager) {
		this.commandRegistry = commandRegistry;
		this.pokemonManager = pokemonManager;
	}
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		Message msg = event.getMessage();
		if(msg.getContentRaw().startsWith(commandRegistry.getPrefix())) {
			String[] args = msg.getContentRaw().substring(commandRegistry.getPrefix().length()).split(" ");
			Command cmd = commandRegistry.getCommand(args[0]);
			
//			event.getChannel().sendMessage(String.format("Command %s sent with args: %s", args[0], Arrays.toString(args))).queue();
			
			if(cmd != null) {
				cmd.onCommand(msg, event.getAuthor(), event.getChannel(), args);
			}
		}
		
		if(!event.getAuthor().isBot()) {
			pokemonManager.tickServer(event.getGuild().getIdLong(), event.getChannel());
		}
	}
	
	@Override
    public void onGuildJoin(@Nonnull GuildJoinEvent event) {
		pokemonManager.addGuild(event.getGuild().getIdLong());
	}
		
}
