package com.cognitivethought.inventory;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.cognitivethought.resources.Resources;

public class Item {
	/**
	 * Handles assigning names and textures of each item
	 * 
	 * 
	 */
	public static final int SEED = 0;
	public static final int APPLE = 1;
	public static final int ORGANIC_MATTER = 2;
	public static final int STICK = 3;
	public static final int COIN = 4;
	public static final int NONE = 5;
	public static final int FERTILIZER = 6;
	public static final int BARK = 7;
	
	int x, y;
	
	private static HashMap<Integer, String> nameKey = new HashMap<Integer, String>();
	static {
		nameKey.put(SEED, "Seed");
		nameKey.put(APPLE, "Apple");
		nameKey.put(ORGANIC_MATTER, "Organic Matter");
		nameKey.put(STICK, "Stick");
		nameKey.put(COIN, "Coin");
		nameKey.put(NONE, "");
		nameKey.put(FERTILIZER, "Fertilizer");
		nameKey.put(BARK, "Bark");
	}
	
	private static HashMap<Integer, Texture> textureKey = new HashMap<Integer, Texture>();
	static {
		textureKey.put(SEED, Resources.SEED);
		textureKey.put(APPLE, Resources.APPLE);
		textureKey.put(ORGANIC_MATTER, Resources.ORGANIC_MATTER);
		textureKey.put(STICK, Resources.STICK);
		textureKey.put(COIN, Resources.COIN);
		textureKey.put(NONE, Resources.NONE);
		textureKey.put(FERTILIZER, Resources.FERTILIZER);
		textureKey.put(BARK, Resources.FERTILIZER);
	}
	
	private int id;
	private int quantity;
	private int position;
	
	public Item(int id, int quantity, int position) {
		this.id = id;
		this.quantity = quantity;
		this.position = position;
	}
	
	public void updateItem() {
		if (quantity <= 0) {
			id = NONE;
			quantity = 0;
		}
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
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}