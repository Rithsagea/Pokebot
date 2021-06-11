package com.rithsagea.pokebot;

import java.io.File;

import javax.security.auth.login.LoginException;

import com.rithsagea.pokebot.discord.CommandRegistry;
import com.rithsagea.pokebot.discord.MessageListener;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Pokebot {
	
	private JDA jda;
	private JDABuilder jdaBuilder;
	
	private Config config;
	
	private CommandRegistry cmdRegistry;
	
	private MessageListener msgListener;
	
	public Pokebot() {
		config = new Config(new File(Resources.CONFIG_FILE));
		config.loadConfig();
		config.saveConfig(); //sets defaults
		jdaBuilder = JDABuilder.createDefault(config.getDiscordToken());
	}
	
	public void init() {
		Registry.init();
		try {
			jda = jdaBuilder.build();
		} catch (LoginException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		cmdRegistry = new CommandRegistry();
		cmdRegistry.setPrefix(config.getBotPrefix());
		
		msgListener = new MessageListener(cmdRegistry);
		
		jda.addEventListener(msgListener);
	}
}
