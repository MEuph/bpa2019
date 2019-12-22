package com.cognitivethought.inventory;

import java.util.ArrayList;

import com.cognitivethought.main.Main;

public class Recipe {
	
	int[] ids;
	int[] amounts;
	
	Item result;
	
	public Recipe(Item ing1, Item result) {
		ids = new int[1];
		amounts = new int[1];
		ids[0] = ing1.getId();
		amounts[0] = ing1.getQuantity();
		
		System.out.println(Item.getName(ing1.getId()) + "(x" + ing1.getQuantity() + ") = " + 
				Item.getName(result.getId()) + "(x" + result.getQuantity() + ")");
		
		ids[0] = ing1.getId();
		amounts[0] = ing1.getQuantity();
		
		this.result = result;
	}
	
	public Recipe(Item ing1, Item ing2, Item result) {
		ids = new int[2];
		amounts = new int[2];
		ids[0] = ing1.getId();
		ids[1] = ing2.getId();
		amounts[0] = ing1.getQuantity();
		amounts[1] = ing2.getQuantity();
		
		System.out.println(Item.getName(ing1.getId()) + "(x" + ing1.getQuantity() + ") + " + 
				Item.getName(ing2.getId()) + "(x" + ing2.getQuantity() + ") = " + 
				Item.getName(result.getId()) + "(x" + result.getQuantity() + ")");
		
		ids[0] = ing1.getId();
		ids[1] = ing2.getId();
		amounts[0] = ing1.getQuantity();
		amounts[1] = ing2.getQuantity();
		
		System.out.println(ing1.getId() + " " + ing2.getId());
		System.out.println(ids[0] + " " + ids[1]);
		
		this.result = result;
	}
	
	public Recipe(Item ing1, Item ing2, Item ing3, Item result) {
		ids = new int[2];
		amounts = new int[2];
		ids[0] = ing1.getId();
		ids[1] = ing2.getId();
		ids[2] = ing3.getId();
		amounts[0] = ing1.getQuantity();
		amounts[1] = ing2.getQuantity();
		amounts[2] = ing3.getQuantity();
		this.result = result;
	}
	
	public Recipe(Item ing1, Item ing2, Item ing3, Item ing4, Item result) {
		ids = new int[2];
		amounts = new int[2];
		ids[0] = ing1.getId();
		ids[1] = ing2.getId();
		ids[2] = ing3.getId();
		ids[3] = ing4.getId();
		amounts[0] = ing1.getQuantity();
		amounts[1] = ing2.getQuantity();
		amounts[2] = ing3.getQuantity();
		amounts[3] = ing4.getQuantity();
		this.result = result;
	}
	
	public boolean canCraft(Slot[] slots) {
		if (InventoryBar.currentlyHeldItem.getId() != Item.NONE) {
			if (InventoryBar.currentlyHeldItem.getId() != result.getId()) {
				return false;
			}
		}
		
		int emptySlots = 0;
		
		ArrayList<Integer> expectedItems = new ArrayList<Integer>();
		
		for (int i = 0; i < ids.length; i++) {
			expectedItems.add(ids[i]);
		}
		
		for (Slot s : slots) {
			for (int i = 0; i < ids.length; i++) {
				if (s.getHeldItem().getId() == Item.NONE) {
					emptySlots++;
					continue;
				}
				if (!expectedItems.contains(s.getHeldItem().getId())) {
					System.out.println(ids[i] + ", " + s.getHeldItem().getId());
					System.out.println("Incorrect items, " + Item.getName(ids[i]) + " vs " + Item.getName(s.getHeldItem().getId()));
					return false;
				}
			}
		}
		
		if (emptySlots > (ids.length * 4) - (expectedItems.size() * ids.length)) {
			System.out.println("Too many empty slots: " + emptySlots + " vs expected " + ((ids.length * 4) - (expectedItems.size() * ids.length)));
			System.out.println("Debug info: " + Item.getName(result.getId()));
			return false;
		}
		
		// TODO: Note to self: When making sticks for a second time, there are too many empty slots. Fix it; FURTHER NOTE: It's detecting the wrong recipe
		
		System.out.println("Correct items");
		
		// Items are correct, but are quantities?
		
		for (Slot s : slots) {
			for (int i = 0; i < amounts.length; i++) {
				if (s.getHeldItem().getId() == Item.NONE) continue;
				if (s.getHeldItem().getQuantity() < amounts[i]) {
					System.out.println("Inorrect quantities" + amounts[i] + " vs " + s.getHeldItem().getQuantity());
					return false;
				}
			}
		}
		
		System.out.println("Correct quantities");
		
		return true;
	}
	
	public void craft(Slot[] slots) {
		if (Main.main.getScreen() != Main.main.gameScreen) return;
		
		if (InventoryBar.currentlyHeldItem.getId() == result.getId()) {
			for (int i = 0; i < result.getQuantity(); i++) {
				InventoryBar.currentlyHeldItem.increment();
			}
		} else {
			int pos = -1;
			for (int i = 0; i < InventoryBar.i.getItems().size(); i++) {
				if (InventoryBar.i.getItems().get(i).getId() == result.getId()) {
					pos = i;
					break;
				}
			}
			
			if (pos == -1) {
				int emptyPos = 0;
				for (; emptyPos < InventoryBar.i.getItems().size(); emptyPos++) {
					if (InventoryBar.i.getItems().get(emptyPos).getId() == Item.NONE)
						break;
				}
			}
			
			InventoryBar.currentlyHeldItem = new Item(result.getId(), result.getQuantity(), pos);
		}
		
		for (int i = 0; i < slots.length; i++) {
			for (int j = 0; j < ids.length; j++) {
				if (slots[i].getHeldItem().getId() == ids[j]) {
					System.out.println(slots[i].getHeldItem().getQuantity());
					System.out.println(amounts[j]);
					for(int k = 0; k < amounts[j]; k++) {
						slots[i].getHeldItem().decrement();
					}
				}
			}
		}
		
		for (int i = 0; i < slots.length; i++) {
			if (slots[i].getHeldItem().getQuantity() <= 0) {
				slots[i].getHeldItem().setId(Item.NONE);
				slots[i].getHeldItem().setQuantity(0);
			}
		}
	}
}