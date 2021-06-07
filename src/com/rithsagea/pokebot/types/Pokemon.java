package com.rithsagea.pokebot.types;

import com.rithsagea.pokebot.types.registry.RegistryField;

public class Pokemon {
	@RegistryField
	public int id;
	
	@RegistryField
	public String identifier;
	
	@RegistryField
	public int species_id;
	
	@RegistryField
	public int height;
	
	@RegistryField
	public int weight;
	
	@RegistryField
	public int base_experience;
	
	@RegistryField
	public int order;
	
	@RegistryField
	public boolean is_default;
}
