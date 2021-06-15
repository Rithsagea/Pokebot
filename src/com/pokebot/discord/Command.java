package com.pokebot.discord;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public interface Command {
	public String getLabel();
	public String[] getAliases();
	
	public void onCommand(Message msg, User sender, MessageChannel chann, String[] args);
}
