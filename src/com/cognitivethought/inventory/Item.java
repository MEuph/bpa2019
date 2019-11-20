package com.cognitivethought.inventory;

public class Item {
	
	public static final int SEED = 0;
	public static final int APPLE = 1;
	public static final int ORGANIC_MATTER = 2;
	
	int id;
	int quantity;
	
	public Item(int id, int quantity) {
		
	}
	
	public int getId() {
		return id;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void increment() {
		this.quantity++;
	}
	
	public void decrement() {
		this.quantity-= this.quantity > 0 ? 1 : 0;
	}
}