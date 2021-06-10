package com.rithsagea.pokebot.test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import com.opencsv.CSVReader;

public class CSVTest {
	public static void main(String[] args) throws IOException {
		CSVReader r = new CSVReader(new FileReader(new File("resources/csv/table/pokemon.csv")));
		for(String[] line : r.readAll()) {
			System.out.println(Arrays.toString(line));
		}
	}
}
