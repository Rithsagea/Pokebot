package com.rithsagea.pokebot.test;

import java.io.File;

import com.rithsagea.pokebot.Config;

public class ConfigTest {
	public static void main(String[] args) {
		Config conf = new Config(new File("pokebot.config"));
		conf.loadConfig();
		conf.saveConfig();
		System.out.println(conf.getDiscordSecret());
	}
}
