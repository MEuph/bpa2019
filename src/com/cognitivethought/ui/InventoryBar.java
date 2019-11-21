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
	}
	
	public void render(Batch b, OrthographicCamera c) {
		for (int i = 0; i < this.i.getItems().size(); i++) {
			b.end();
			ShapeRenderer sp = new ShapeRenderer();
			sp.setProjectionMatrix(c.combined);
			sp.begin(ShapeType.Filled);
			Gdx.gl20.glEnable(GL20.GL_BLEND_SRC_ALPHA);
			sp.setColor(new Color(0.1f, 0.1f, 0.1f, 0.1f));
			sp.rect(c.position.x+(i*100), c.position.y+c.viewportHeight-200, 100, 100);
			sp.setColor(new Color(1f, 1f, 1f, 1f));
			sp.rectLine(c.position.x+(i*100), c.position.y+c.viewportHeight-200, c.position.x+(i*100) + 100, c.position.y+c.viewportHeight-100, 2);
			Gdx.gl20.glDisable(GL20.GL_BLEND_SRC_ALPHA);
			sp.end();
			b.begin();
			b.draw(Item.getTexture(this.i.getItems().get(i).getId()), c.position.x+(i*100), c.position.y+c.viewportHeight-200, 100, 100);
		}
	}
}