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
import com.pokebot.discord.ServerData;
import com.pokebot.json.PokemonAdapter;
import com.pokebot.types.Language;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class PokemonManager {
	private HashMap<MessageChannel, Pokemon> activePokemon;
	private HashMap<Long, PokemonUser> users;
	private HashMap<Long, ServerData> servers;
	
	private JDA jda;
	private Gson g;
	
	public PokemonManager(File file, File serverData, JDA jda) {
		activePokemon = new HashMap<>();
		servers = new HashMap<>();
		
		g = new GsonBuilder()
				.registerTypeAdapter(new TypeToken<Pokemon>() {}.getType(), new PokemonAdapter())
				.setPrettyPrinting()
				.create();
		
		this.jda = jda;
		
		load(file, serverData);
	}
	
	public void load(File userData, File serverData) {
		if(!userData.exists()) {
			try {
				userData.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				users = g.fromJson(new FileReader(userData), new TypeToken<HashMap<Long, PokemonUser>>() {}.getType());
				servers = g.fromJson(new FileReader(serverData), new TypeToken<HashMap<Long, ServerData>>() {}.getType());
			} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
				e.printStackTrace();
				System.err.println("Error reading user data");
				System.exit(0); //error with save data
			}
		}
		if(users == null) users = new HashMap<>();
	}
	
	public void save(File userData, File serverData) {
		try {
			FileWriter writer;
			
			writer = new FileWriter(serverData);
			g.toJson(servers, writer);
			writer.flush();
			writer.close();
			
			writer = new FileWriter(userData);
			g.toJson(users, writer);
			writer.flush();
			writer.close();
			
		} catch (JsonIOException | IOException e) {
			e.printStackTrace();
			System.err.println("Error saving data");
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
	
	public void addGuild(long id) {
		if(!servers.containsKey(id))
		servers.put(id, new ServerData());
	}
	
	public void tickServer(long id, MessageChannel chann) {
		ServerData s = servers.get(id);
		s.counter++;
		
		//TODO replace with new equation
		if(Math.random() < (s.counter / 100f)) {
			s.counter = 0;
			TextChannel tChann = jda.getTextChannelById(s.spawnChannelID);
			if(tChann == null)
				spawnPokemon(chann);
			else
				spawnPokemon(tChann);
		}
	}
	
	public void tickUser(long id, MessageChannel chann) {
		PokemonUser u = users.get(id);
		if(u == null) return;
		Pokemon p = u.party.get(u.selectedPokemon);
		if(p == null) return;
		if(p.addExpPoints(100)) { //TODO: change this maybe
			chann.sendMessage(jda.getUserById(id).getAsMention() + ", your " + p.getName(Language.en) + " levelled up to level " + p.level).queue();;
		}
	}
	
	public void setSpawnChannel(long serverId, long channelId) {
		servers.get(serverId).spawnChannelID = channelId;
	}
	
	public TextChannel getPokeChannel(long id) {
		return jda.getTextChannelById(servers.get(id).spawnChannelID);
	}
}
