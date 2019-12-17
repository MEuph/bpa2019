package com.cognitivethought.ui;

import java.io.FileNotFoundException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.cognitivethought.inventory.Inventory;
import com.cognitivethought.inventory.Item;
import com.cognitivethought.resources.Resources;

public class InventoryBar {
	
	public static Inventory i = new Inventory();
	
	public BitmapFont font;
	public BitmapFont smallFont;
	
	public int selected;
	
	public float timer = 0f;
	public float timer2 = 0f;
	
	public CraftingGrid grid;
	
	public InventoryBar(String invFile) {
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(new FileHandle("assets/Fonts/times-new-roman.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 40;
		font = generator.generateFont(parameter);
		parameter.size = 20;
		smallFont = generator.generateFont(parameter);
		generator.dispose();
		try {
			i.read(invFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for (Item item : i.getItems()) {
			System.out.println(Item.getName(item.getId()) + "(x" + item.getQuantity() + ") at slot " + item.getPosition());
		}
	}
	
	public void render(Batch b, OrthographicCamera c) {
		float y = c.position.y - (c.viewportHeight / 2) + 800;
		float x = c.position.x - (c.viewportWidth / 2) + 10;
		for (int i = 0; i < this.i.getItems().size(); i++) {
			int yDisplacement = -i*100;
			y = c.position.y - (c.viewportHeight / 2) + yDisplacement + 800;
			
//			this.i.getItems().get(i).setX((int)x);
//			this.i.getItems().get(i).setY((int)y);
			
			b.end();
			ShapeRenderer sp = new ShapeRenderer();
			sp.setProjectionMatrix(c.combined);
			sp.begin(ShapeType.Filled);
			Gdx.gl20.glEnable(GL20.GL_BLEND_SRC_ALPHA);
			
			if (timer <= 0f) {
				if (Gdx.input.isKeyJustPressed(Keys.R)) {
					selected -= 1;
					System.out.println(selected);
					timer = 5f;
					timer2 = 300f;
				}
				
				if (Gdx.input.isKeyJustPressed(Keys.F)) {
					selected += 1;
					System.out.println(selected);
					timer = 5f;
					timer2 = 300f;
				}
			} else {
				timer--;
			}
			
			if (selected > InventoryBar.i.getItems().size() - 1) {
				selected = InventoryBar.i.getItems().size() - 1;
				System.out.println(selected);
			} else if (selected < 0) {
				selected = 0;
				System.out.println(selected);
			}
			
			if (selected == i) {
				sp.setColor(new Color(0.5f, 0.5f, 0.5f, 1f));
			} else {
				sp.setColor(new Color(0.1f, 0.1f, 0.1f, 1f));
			}
			
			sp.rect(x, y, 100, 100);
			sp.setColor(new Color(1f, 1f, 1f, 1f));
			sp.rectLine(x, y, x, y+100, 3);
			sp.rectLine(x, y+100, x+100, y+100, 3);
			sp.rectLine(x+100, y+100, x+100, y, 3);
			sp.rectLine(x, y, x+100, y, 3);
			Gdx.gl20.glDisable(GL20.GL_BLEND_SRC_ALPHA);
			sp.end();
			b.begin();
			if (InventoryBar.i.getItems().get(i).getQuantity() > 0) {
				b.draw(Item.getTexture(this.i.getItems().get(i).getId()), x+10, y+10, 80, 80);
				font.draw(b, "" + Integer.toString(this.i.getItems().get(i).getQuantity()), x+5, y+90);
				if (selected == i && timer2 > 0f) {
					font.draw(b, Item.getName(InventoryBar.i.getItems().get(i).getId()), x + 105 ,y + 60);
				} else if(timer2 > 0f) {
					timer2--;
				}
			} else {
				InventoryBar.i.getItems().get(i).updateItem();
			}
		}

		y -= 100;
		
		b.end();
		ShapeRenderer sp = new ShapeRenderer();
		sp.setColor(new Color(0.1f, 0.1f, 0.1f, 1f));
		sp.setProjectionMatrix(c.combined);
		sp.begin(ShapeType.Filled);
		Gdx.gl20.glEnable(GL20.GL_BLEND_SRC_ALPHA);
		sp.rect(x, y, 100, 100);
		sp.setColor(new Color(1f, 1f, 1f, 1f));
		sp.rectLine(x, y, x, y+100, 3);
		sp.rectLine(x, y+100, x+100, y+100, 3);
		sp.rectLine(x+100, y+100, x+100, y, 3);
		sp.rectLine(x, y, x+100, y, 3);
		Gdx.gl20.glDisable(GL20.GL_BLEND_SRC_ALPHA);
		sp.end();
		b.begin();
		b.draw(Resources.UI_CRAFTING, x + 27, y + 7, 8 * 5, 17 * 5);
		
		smallFont.draw(b, "Press [C] to Make Apples", x + 5, y - 20);
		smallFont.draw(b, "(x3 Organic Matter, x1 Seed)", x + 5, y - 40);
		
		if (Gdx.input.isKeyJustPressed(Keys.C)) {
			int m = 0;
			for (int i = 0; i < InventoryBar.i.getItems().size(); i++) {
				if (InventoryBar.i.getItems().get(i).getId() == Item.ORGANIC_MATTER) {
					m = InventoryBar.i.getItems().get(i).getQuantity();
				}
			}
			System.out.println(m);
			
			int s = 0;
			for (int i = 0; i < InventoryBar.i.getItems().size(); i++) {
				if (InventoryBar.i.getItems().get(i).getId() == Item.SEED) {
					s = InventoryBar.i.getItems().get(i).getQuantity();
				}
			}
			System.out.println(s);
			
			if (m >= 3 && s >= 1) {
				for (int i = 0; i < InventoryBar.i.getItems().size(); i++) {
					if (InventoryBar.i.getItems().get(i).getId() == Item.SEED) {
						InventoryBar.i.getItems().get(i).decrement();
					}
				}
				
				for (int i = 0; i < InventoryBar.i.getItems().size(); i++) {
					if (InventoryBar.i.getItems().get(i).getId() == Item.ORGANIC_MATTER) {
						InventoryBar.i.getItems().get(i).decrement();
						InventoryBar.i.getItems().get(i).decrement();
						InventoryBar.i.getItems().get(i).decrement();
					}
				}
				
				for (int j = 0; j < i.getItems().size(); j++) {
					if (i.getItems().get(j).getId() == Item.APPLE) {
						i.getItems().get(j).increment();
						return;
					} else continue;
				}
				
				int pos = 0;
				for (; pos < i.getItems().size(); pos++) {
					if (i.getItems().get(pos).getId() == Item.NONE) break;
				}
				
				i.getItems().set(pos, new Item(Item.APPLE, 1, pos));
			}
		}
		
	}
}