package com.pokebot;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public abstract class Command {
	public abstract String getLabel();
	public String[] getAliases() { return null; }
	
	public abstract void onCommand(Message msg, User sender, MessageChannel chann, String[] args);
}
