package com.rithsagea.pokebot.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.rithsagea.pokebot.util.CSVUtil;

public class PokemonCSVGen {
	private static class Pokemon {
		int[] base = new int[6];
		int[] effort = new int[6];
		
		int type1 = 0;
		int type2 = 0;
	}
	
	public static void main(String[] args) {
		HashMap<Integer, Pokemon> map = new HashMap<>();
		List<String[]> data;
		
		data = CSVUtil.readFile("veekun/csv/pokemon_stats.csv");
		data.remove(0); // header
		
		int id, stat;
		for(String[] line : data) {
			id = Integer.parseInt(line[0]);
			stat = Integer.parseInt(line[1]) - 1;
			if(!map.containsKey(id))
				map.put(id, new Pokemon());
			
			map.get(id).base[stat] = Integer.parseInt(line[2]);
			map.get(id).effort[stat] = Integer.parseInt(line[3]);
		}
		
		data = CSVUtil.readFile("veekun/csv/pokemon_types.csv");
		data.remove(0);
		
		for(String[] line : data) {
			id = Integer.parseInt(line[0]);
			if(line[2].equals("1"))
				map.get(id).type1 = Integer.parseInt(line[1]);
			else
				map.get(id).type2 = Integer.parseInt(line[1]);
		}
		
		List<String[]> res = new ArrayList<>();
		res.add(new String[] {"base_hp", "base_atk", "base_def", "base_spa", "base_spd", "base_spe",
							"effort_hp", "effort_atk", "effort_def", "effort_spa", "effort_spd", "effort_spe",
							"type1", "type2"});
		for(int key : map.keySet()) {
			Pokemon p = map.get(key);
			res.add(new String[] {
					"" + p.base[0], "" + p.base[1], "" + p.base[2], "" + p.base[3], "" + p.base[4], "" + p.base[5],
					"" + p.effort[0], "" + p.effort[1], "" + p.effort[2], "" + p.effort[3], "" + p.base[4], "" + p.base[5],
					"" + p.type1, "" + p.type2});
		}
		
		CSVUtil.writeFile("pokestat.csv",res);
	}
}
