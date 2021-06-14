package com.pokebot.json;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class ClassExclusionStrategy implements ExclusionStrategy {
	
	private Class<?> clazz;
	
	public ClassExclusionStrategy(Class<?> clazz) {
		this.clazz = clazz;
	}

	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		return false;
	}

	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
		return this.clazz == clazz;
	}

}
