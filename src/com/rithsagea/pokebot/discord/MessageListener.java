package com.rithsagea.pokebot.discord;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {
	
	private CommandRegistry cmdReg;
	
	public MessageListener(CommandRegistry cmdReg) {
		this.cmdReg = cmdReg;
	}
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		String msg = event.getMessage().getContentRaw();
		if(msg.startsWith(cmdReg.getPrefix())) {
			String[] args = msg.substring(cmdReg.getPrefix().length()).split(" ");
			Command cmd = cmdReg.getCommand(args[0]);
			if(cmd != null) cmd.onCall(event.getChannel(), event.getMessage(), args);
			else return; //error message here
		}
	}
}
