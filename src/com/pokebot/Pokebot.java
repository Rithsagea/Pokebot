package com.pokebot;

import java.io.File;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.security.auth.login.LoginException;

import com.pokebot.discord.CommandRegistry;
import com.pokebot.discord.MessageListener;
import com.pokebot.discord.command.CommandCatchPokemon;
import com.pokebot.discord.command.CommandCheckParty;
import com.pokebot.discord.command.CommandCheckSpawn;
import com.pokebot.discord.command.CommandGeneratePokemon;
import com.pokebot.discord.command.CommandInfoPokemon;
import com.pokebot.discord.command.CommandStartPokemon;
import com.pokebot.discord.command.CommandStop;
import com.pokebot.game.PokemonManager;
import com.pokebot.types.data.DataRegistry;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Pokebot {

	private JDABuilder builder;
	private JDA jda;
	
	private ScheduledExecutorService executorService;
	private Config config;
	private File userDataFile;
	
	private PokemonManager pokemonManager;
	
	private CommandRegistry commandRegistry;
	private MessageListener messageListener;
	
	public Pokebot() {
		config = new Config(new File("pokebot.config"));
		config.loadConfig(); config.saveConfig();
		userDataFile = new File("userData.json");
		
		builder = JDABuilder.createDefault(config.getDiscordToken());
		executorService = Executors.newSingleThreadScheduledExecutor();
	}
	
	private void saveFile() {
		System.out.println("Saving User Data at " + Instant.now());
		pokemonManager.save(userDataFile);
	}
	
	public void init() {
		try {
			jda = builder.build();
		} catch (LoginException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		DataRegistry.getInstance().loadData();
		pokemonManager = new PokemonManager(userDataFile);
		executorService.scheduleAtFixedRate(this::saveFile, 0, 1, TimeUnit.MINUTES);
		
		commandRegistry = new CommandRegistry(config.getBotPrefix());
		messageListener = new MessageListener(commandRegistry);
		
		commandRegistry.registerCommand(new CommandGeneratePokemon(pokemonManager));
		commandRegistry.registerCommand(new CommandCheckSpawn(pokemonManager));
		commandRegistry.registerCommand(new CommandCatchPokemon(pokemonManager));
		commandRegistry.registerCommand(new CommandStartPokemon(pokemonManager));
		commandRegistry.registerCommand(new CommandCheckParty(pokemonManager));
		commandRegistry.registerCommand(new CommandInfoPokemon(pokemonManager));
		commandRegistry.registerCommand(new CommandStop(pokemonManager));
		
		try {
			jda.awaitReady();
		} catch (InterruptedException e) {
			e.printStackTrace(); //not sure when this happens. just in case
			System.exit(0);
		}
		
		jda.addEventListener(messageListener);
	}
}
