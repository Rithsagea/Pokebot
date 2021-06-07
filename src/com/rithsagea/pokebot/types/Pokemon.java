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
	
	@RegistryField
	public int base_hp;
	
	@RegistryField
	public int base_atk;
	
	@RegistryField
	public int base_def;
	
	@RegistryField
	public int base_spa;
	
	@RegistryField
	public int base_spd;
	
	@RegistryField
	public int base_spe;
	
	@RegistryField
	public int effort_hp;
	
	@RegistryField
	public int effort_atk;
	
	@RegistryField
	public int effort_def;
	
	@RegistryField
	public int effort_spa;
	
	@RegistryField
	public int effort_spd;
	
	@RegistryField
	public int effort_spe;
	
	@RegistryField
	public int type1;
	
	@RegistryField
	public int type2;
}
