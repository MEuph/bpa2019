package com.cognitivethought.inventory;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;

public class Item {
	
	public static final int SEED = 0;
	public static final int APPLE = 1;
	public static final int ORGANIC_MATTER = 2;
	
	private static HashMap<Integer, String> nameKey = new HashMap<Integer, String>();
	static {
		nameKey.put(SEED, "Seed");
		nameKey.put(APPLE, "Apple");
		nameKey.put(ORGANIC_MATTER, "Organic Matter");
	}
	
	private static HashMap<Integer, Texture> textureKey = new HashMap<Integer, Texture>();
	static {
		textureKey.put(SEED, new Texture("assets/Inventory/Items/apple.png"));
		textureKey.put(APPLE, new Texture("assets/Inventory/Items/seed.png"));
		textureKey.put(ORGANIC_MATTER, new Texture("assets/Inventory/Items/apple.png"));
	}
	
	private int id;
	private int quantity;
	private int position;
	
	public Item(int id, int quantity, int position) {
		this.id = id;
		this.quantity = quantity;
		this.position = position;
	}
	
	public int getId() {
		return id;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
	
	public void increment() {
		this.quantity++;
	}
	
	public void decrement() {
		this.quantity-= this.quantity > 0 ? 1 : 0;
	}
	
	public static String getName(int id) {
		return nameKey.get(id);
	}
	
	public static Texture getTexture(int id) {
		return textureKey.get(id);
	}
}