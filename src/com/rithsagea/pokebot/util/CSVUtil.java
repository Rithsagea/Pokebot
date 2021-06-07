package com.rithsagea.pokebot.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class CSVUtil {
	public static List<String[]> readFile(String path) {
		List<String[]> res = null;
		
		try {
			CSVReader reader = new CSVReader(Files.newBufferedReader(new File(path).toPath()));
			res = reader.readAll();
			reader.close();
		} catch (IOException e) {
			//e.printStackTrace();
			//do nothing, just return null
		}
		
		return res;
	}
	
	public static void writeFile(String path, List<String[]> lines) {
		try {
			CSVWriter writer = new CSVWriter(Files.newBufferedWriter(new File(path).toPath()));
			writer.writeAll(lines);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
