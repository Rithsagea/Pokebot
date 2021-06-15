package com.pokebot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Config {
	
	private static final String DISCORD_TOKEN = "discordToken";
	private static final String BOT_PREFIX = "botPrefix";
	
	private Properties prop;
	private File file;
	
	public Config(File file) {
		this.file = file;
		prop = new Properties();
	}
	
	private void checkProperty(String key, String def) {
		if(prop.getProperty(key) == null)
			prop.setProperty(key, def);
	}
	
	public void loadConfig() {
		try {
			if(!file.exists())
				file.createNewFile();
			
			InputStream is = new FileInputStream(file);
			prop.load(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		checkProperty(DISCORD_TOKEN, "");
		checkProperty(BOT_PREFIX, "p!");
	}
	
	public void saveConfig() {
		try {
			OutputStream os = new FileOutputStream(file);
			prop.store(os, "Config File for Pokebot");
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getDiscordToken() {
		return prop.getProperty(DISCORD_TOKEN);
	}
	
	public String getBotPrefix() {
		return prop.getProperty(BOT_PREFIX);
	}
}