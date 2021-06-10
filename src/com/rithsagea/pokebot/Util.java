package com.rithsagea.pokebot;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;

public class Util {
	public static List<String[]> readCsv(File file) {
		List<String[]> res = null;
		CSVReader r;
		try {
			r = new CSVReader(new FileReader(file));
			res = r.readAll(); r.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public static int parseInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return 0;
		}
	}
}
