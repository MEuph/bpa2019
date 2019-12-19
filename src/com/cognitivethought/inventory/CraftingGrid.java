package com.cognitivethought.inventory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.cognitivethought.resources.Resources;

public class CraftingGrid {

	public boolean shown;
	public boolean clickInGrid;
	public boolean highlighted;

	public Rectangle craftButton = new Rectangle(0, 0, 0, 0);

	private int x, y, size;

	Slot[] slots = new Slot[4];

	ArrayList<Recipe> possibleRecipes = new ArrayList<Recipe>();

	public CraftingGrid(File recipes) {
		slots[0] = new Slot(new Item(Item.NONE, 0, 0));
		slots[1] = new Slot(new Item(Item.NONE, 0, 0));
		slots[2] = new Slot(new Item(Item.NONE, 0, 0));
		slots[3] = new Slot(new Item(Item.NONE, 0, 0));

		ArrayList<String> lines = new ArrayList<String>();
		int[][] data = new int[0][0];

		try {
			Scanner sc = new Scanner(recipes);
			while (sc.hasNextLine()) {
				lines.add(sc.nextLine());
			}
			data = new int[lines.size()][];
			for (int i = 0; i < lines.size(); i++) {
				data[i] = new int[lines.get(i).split(";").length];
				String[] dataToActOn = lines.get(i).split(";");
				for (int j = 0; j < dataToActOn.length; j++) {
					data[i][j] = Integer.parseInt(dataToActOn[j]);
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				System.out.print(data[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();

		for (int i = 0; i < data.length; i++) {
			Recipe recipe = null;
			switch (data[i][0]) {
			case 1:
				recipe = new Recipe(new Item(data[i][3], data[i][4], 0), new Item(data[i][1], data[i][2], 0));
				break;
			case 2:
				recipe = new Recipe(new Item(data[i][3], data[i][4], 0), new Item(data[i][5], data[i][6], 0),
						new Item(data[i][1], data[i][2], 0));
				break;
			case 3:
				recipe = new Recipe(new Item(data[i][3], data[i][4], 0), new Item(data[i][5], data[i][6], 0),
						new Item(data[i][7], data[i][8], 0), new Item(data[i][1], data[i][2], 0));
				break;
			case 4:
				recipe = new Recipe(new Item(data[i][3], data[i][4], 0), new Item(data[i][5], data[i][6], 0),
						new Item(data[i][7], data[i][8], 0), new Item(data[i][9], data[i][10], 0),
						new Item(data[i][1], data[i][2], 0));
				break;
			}
			possibleRecipes.add(recipe);
		}
	}

	public void open() {
		shown = true;
	}

	public void close() {
		shown = false;
	}

	public void updateClick(int mx, int my) {
		if (!shown)
			return;
		
		clickInGrid = new Rectangle(x, y, size, size).contains(mx, my);
		
		slots[0].updateClick(mx, my);
		slots[1].updateClick(mx, my);
		slots[2].updateClick(mx, my);
		slots[3].updateClick(mx, my);
	}

	public void updateHighlight(int mx, int my) {
		if (!shown)
			return;

		clickInGrid = new Rectangle(x, y, size, size).contains(mx, my);

		slots[0].updateHighlight(mx, my);
		slots[1].updateHighlight(mx, my);
		slots[2].updateHighlight(mx, my);
		slots[3].updateHighlight(mx, my);
	}

	public void render(Batch b, OrthographicCamera c, float relativeX, float relativeY, float size, BitmapFont font) {
		if (!shown) {
			return;
		}

		x = (int) relativeX;
		y = (int) relativeY;
		size = (int) size;

		craftButton.x = relativeX + (size / 2) + 100;
		craftButton.y = relativeY + (size / 2) - 50;

		craftButton.width = 100;
		craftButton.height = 100;

		b.end();
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		ShapeRenderer sp = new ShapeRenderer();
		sp.setProjectionMatrix(c.combined);
		sp.begin(ShapeType.Filled);
		sp.setColor(new Color(0.1f, 0.1f, 0.1f, 0.9f));
		sp.rect(relativeX, relativeY, size, size);
		sp.end();
		sp.begin(ShapeType.Line);
		sp.setColor(new Color(1f, 1f, 1f, 1f));
		sp.rect(relativeX, relativeY, size, size);
		sp.end();
		sp.begin(ShapeType.Filled);
		sp.setColor(new Color(0.1f, 0.1f, 0.1f, 0.9f));
		sp.rect(relativeX, relativeY, size, size);
		if (highlighted)
			sp.setColor(new Color(0.5f, 0.5f, 0.5f, 0.9f));
		else
			sp.setColor(new Color(0, 0, 0, 1f));
		sp.rect(craftButton.x, craftButton.y, craftButton.width, craftButton.height);
		sp.end();
		sp.begin(ShapeType.Line);
		sp.setColor(new Color(1f, 1f, 1f, 1f));
		sp.rect(craftButton.x, craftButton.y, craftButton.width, craftButton.height);
		sp.rect(relativeX, relativeY, size, size);
		sp.end();
		// Disable transparency blending
		Gdx.gl.glDisable(GL20.GL_BLEND);
		b.begin();

		b.draw(Resources.CHECK, craftButton.x, craftButton.y, craftButton.width, craftButton.height);

		slots[0].render(b, c, relativeX + (size / 2) - 200, relativeY + (size / 2), 100, font);
		slots[1].render(b, c, relativeX + (size / 2) - 100, relativeY + (size / 2), 100, font);
		slots[2].render(b, c, relativeX + (size / 2) - 200, relativeY + (size / 2) - 100, 100, font);
		slots[3].render(b, c, relativeX + (size / 2) - 100, relativeY + (size / 2) - 100, 100, font);
	}

	public void craft() {
		if (!shown) return;
		
		int slotsFilled = 0;
		int[] slotIds = {5, 5, 5, 5};
		int[] slotAmts = {0, 0, 0, 0};
		for (int i = 0; i < slots.length; i++) {
			slotIds[i] = slots[i].getHeldItem().getId();
			slotAmts[i] = slots[i].getHeldItem().getQuantity();
		}
		
		for (int i = 0; i < slotIds.length; i++) {
			if(slots[i].getHeldItem().getId() != Item.NONE) {
				slotsFilled++;
			}
		}
		
		ArrayList<Recipe> currentRecipes = new ArrayList<Recipe>();
		for (int i = 0; i < possibleRecipes.size(); i++) {
			currentRecipes.add(possibleRecipes.get(i));
		}
		
		System.out.println(currentRecipes.size());
		
		for (int i = 0; i < currentRecipes.size(); i++) {
			if (currentRecipes.get(i).ids.length != slotsFilled) {
				currentRecipes.remove(i);
			}
		}
		
		for (Recipe r : currentRecipes) {
			if (r.canCraft(slots)) 
				r.craft(slots);
		}
	}
}