package com.pokebot.discord;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {
	
	private CommandRegistry commandRegistry;
	
	public MessageListener(CommandRegistry commandRegistry) {
		this.commandRegistry = commandRegistry;
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
	}
		
}
