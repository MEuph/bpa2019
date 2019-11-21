package com.cognitivethought.inventory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Inventory {
	
	ArrayList<Item> items = new ArrayList<Item>();
	
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
		
		for (int i = 0; i < data.size(); i+=3) {
			items.add(new Item(data.get(i), data.get(i+1), data.get(i+2)));
		}
		
		sc.close();
	}
	
	public void save(String toSaveTo) throws IOException {
		String data = "";
		
		for (Item i : items) {
			data += i.getId() + ";" + i.getQuantity() + ";" + i.getPosition() + ";";
		}
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(toSaveTo));
		writer.write(data);
		writer.close();
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}
}