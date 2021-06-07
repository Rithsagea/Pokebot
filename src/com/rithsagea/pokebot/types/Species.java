package com.rithsagea.pokebot.types;

import com.rithsagea.pokebot.types.registry.RegistryField;

public class Species {
	@RegistryField
	public int id;
	
	@RegistryField
	public String identifier;
	
	@RegistryField
	public int generation_id;
	
	@RegistryField
	public int evolves_from_species_id;
	
	@RegistryField
	public int evolution_chain_id;
	
	@RegistryField
	public int color_id;
	
	@RegistryField
	public int shape_id;
	
	@RegistryField
	public int habitat_id;
	
	@RegistryField
	public int gender_rate;
	
	@RegistryField
	public int capture_rate;
	
	@RegistryField
	public int base_happiness;
	
	@RegistryField
	public boolean is_baby;
	
	@RegistryField
	public int hatch_counter;
	
	@RegistryField
	public boolean has_gender_differences;
	
	@RegistryField
	public int growth_rate_id;
	
	@RegistryField
	public boolean forms_switchable;
	
	@RegistryField
	public boolean is_legendary;
	
	@RegistryField
	public boolean is_mythical;
}
