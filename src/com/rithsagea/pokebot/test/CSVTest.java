package com.rithsagea.pokebot.test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.rithsagea.pokebot.Resources;

public class CSVTest {
	public static void main(String[] args) throws IOException {
		CSVReader r = new CSVReader(new FileReader(new File(Resources.POKE_MOVES_CSV)));
		List<String[]> data = r.readAll(); r.close();
		String[] header = data.remove(0);
	}
}
