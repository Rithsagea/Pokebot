package com.pokebot.json;

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
				data.add(construct(JsonParser.parseReader(new FileReader(f)).getAsJsonObject()));
			} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		Collections.sort(data);
		
		Gson g = new GsonBuilder().setPrettyPrinting().create();
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
