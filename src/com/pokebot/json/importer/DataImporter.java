package com.pokebot.json.importer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.pokebot.json.LanguageStringAdapter;
import com.pokebot.json.StatDataAdapter;
import com.pokebot.types.LanguageString;
import com.pokebot.types.data.StatData;

public abstract class DataImporter<T extends Comparable<T>> {
	
	private File path;
	private List<T> data;
	
	public DataImporter(File path) {
		this.path = path;
		data = new ArrayList<>();
	}
	
	public void run(File output) {
		for(File f : path.listFiles()) {
			try {
				T t = construct(JsonParser.parseReader(new FileReader(f)).getAsJsonObject());
				if(t != null) data.add(t);
			} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		Collections.sort(data);
		
		Gson g = new GsonBuilder()
				.registerTypeAdapter(new TypeToken<LanguageString>() {}.getType(), new LanguageStringAdapter())
				.registerTypeAdapter(new TypeToken<StatData>() {}.getType(), new StatDataAdapter())
				.setPrettyPrinting()
				.create();
		FileWriter f;
		
		try {
			f = new FileWriter(output);
			g.toJson(data, f);
			f.flush(); f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected abstract T construct(JsonObject o);
}
