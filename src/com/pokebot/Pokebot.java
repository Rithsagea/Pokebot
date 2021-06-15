package com.pokebot;

import java.io.File;

import javax.security.auth.login.LoginException;

import com.pokebot.types.data.DataRegistry;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Pokebot {

	private JDABuilder builder;
	private JDA jda;
	
	private Config config;
	
	private DataRegistry dataRegistry;
	
	public Pokebot() {
		config = new Config(new File("pokebot.config"));
		config.loadConfig(); config.saveConfig();
		
		builder = JDABuilder.createDefault(config.getDiscordToken());
		
		dataRegistry = new DataRegistry();
	}
	
	public void init() {
		try {
			jda = builder.build();
		} catch (LoginException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		dataRegistry.loadData();
		
		try {
			jda.awaitReady();
		} catch (InterruptedException e) {
			e.printStackTrace(); //not sure when this happens. just in case
			System.exit(0);
		}
	}
}
