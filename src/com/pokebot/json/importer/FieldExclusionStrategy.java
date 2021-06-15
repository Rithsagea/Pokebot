package com.pokebot.json.importer;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class FieldExclusionStrategy implements ExclusionStrategy {

	public String field;
	
	public FieldExclusionStrategy(String field) {
		this.field = field;
	}
	
	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		return f.getName().equals(field);
	}

	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
		return false;
	}

}
