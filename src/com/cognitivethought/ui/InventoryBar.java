package com.cognitivethought.ui;

import java.io.FileNotFoundException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.cognitivethought.inventory.Inventory;
import com.cognitivethought.inventory.Item;

public class InventoryBar {
	Inventory i;
	
	public InventoryBar(String invFile) {
		i = new Inventory();
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
		for (int i = 0; i < this.i.getItems().size(); i++) {
			int xDisplacement = i*150;
			float y = c.position.y+425;
			float x = c.position.x + xDisplacement;
			
			b.end();
			ShapeRenderer sp = new ShapeRenderer();
			sp.setProjectionMatrix(c.combined);
			sp.begin(ShapeType.Filled);
			Gdx.gl20.glEnable(GL20.GL_BLEND_SRC_ALPHA);
			sp.setColor(new Color(0.1f, 0.1f, 0.1f, 0.1f));
			sp.rect(x, y, 100, 100);
			sp.setColor(new Color(1f, 1f, 1f, 1f));
			sp.rectLine(x, y, x, y+100, 3);
			sp.rectLine(x, y+100, x+100, y+100, 3);
			sp.rectLine(x+100, y+100, x+100, y, 3);
			sp.rectLine(x, y, x+100, y, 3);
			Gdx.gl20.glDisable(GL20.GL_BLEND_SRC_ALPHA);
			sp.end();
			b.begin();
			b.draw(Item.getTexture(this.i.getItems().get(i).getId()), x, y, 100, 100);
		}
	}
}