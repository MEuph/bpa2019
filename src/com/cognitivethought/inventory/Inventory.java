package com.cognitivethought.inventory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Inventory {
	
	ArrayList<Item> items;
	
	public Inventory() {
		
	}
	
	public void read(String s) throws FileNotFoundException {
		ArrayList<Integer> data = new ArrayList<>();
		
		File f = new File(s);
		Scanner sc = new Scanner(f);
		sc.useDelimiter(Pattern.compile(";"));
		
		while (sc.hasNext()) {
			data.add(Integer.parseInt(sc.next()));
		}
		
		for (int i = 0; i < data.size(); i+=2) {
			items.add(new Item(data.get(i), data.get(i+1)));
		}
		
		sc.close();
	}
	
	public void save(String toSaveTo) {
		
	}
	
}