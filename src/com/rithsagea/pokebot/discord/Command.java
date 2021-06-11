package com.rithsagea.pokebot.discord;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;

public abstract class Command {
	
	public abstract String getLabel();
	public abstract String[] getAliases();
	public abstract void onCall(MessageChannel chann, Message msg, String[] args);
}
