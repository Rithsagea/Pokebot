package com.pokebot.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.pokebot.discord.EmbedUtil;
import com.pokebot.json.PokemonAdapter;
import com.pokebot.types.Language;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class PokemonManager {
	private HashMap<MessageChannel, Pokemon> activePokemon;
	private HashMap<Long, PokemonUser> users;
	
	private Gson g;
	
	public PokemonManager(File file) {
		activePokemon = new HashMap<>();
		
		g = new GsonBuilder()
				.registerTypeAdapter(new TypeToken<Pokemon>() {}.getType(), new PokemonAdapter())
				.setPrettyPrinting()
				.create();
		
		load(file);
	}
	
	public void load(File file) {
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				users = g.fromJson(new FileReader(file), new TypeToken<HashMap<Long, PokemonUser>>() {}.getType());
			} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
				e.printStackTrace();
				System.err.println("Error reading user data");
				System.exit(0); //error with save data
			}
		}
		if(users == null) users = new HashMap<>();
	}
	
	public void save(File file) {
		try {
			FileWriter writer = new FileWriter(file);
			g.toJson(users, writer);
			writer.flush();
			writer.close();
		} catch (JsonIOException | IOException e) {
			e.printStackTrace();
			System.err.println("Error saving user data");
		}
	}
	
	public void spawnPokemon(MessageChannel chann, Pokemon p) {
		activePokemon.put(chann, p);
		chann.sendMessage(EmbedUtil.generateWildEmbed(p)).queue();
	}
	
	public void spawnPokemon(MessageChannel chann) {
		spawnPokemon(chann, PokemonGenerator.generateWildPokemon());
	}
	
	public Pokemon getPokemon(MessageChannel chann) {
		return activePokemon.get(chann);
	}
	
	public void catchPokemon(MessageChannel chann, User sender) {
		PokemonUser user = users.get(sender.getIdLong());
		if(user == null) {
			chann.sendMessage("You need to pick a starter before catching pokemon, " + sender.getAsMention() + ". Use p!start to begin.").queue();
		} else {
			Pokemon p = activePokemon.remove(chann);
			user.party.add(p);
			chann.sendMessage("Congratulations " + sender.getAsMention() + "! You caught a level " + p.level + " " + p.getName(Language.en) + "!").queue();
		}
	}
	
	public PokemonUser createUser(long id) {
		PokemonUser user = new PokemonUser();
		user.party = new ArrayList<>();
		users.put(id, user);
		
		return user;
	}
	
	public PokemonUser getUser(long id) {
		return users.get(id);
	}
}
