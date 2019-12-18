package com.cognitivethought.inventory;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class CraftingGrid {
	
	public boolean shown;
	
	public Rectangle craftButton;
	
	Slot[] slots = new Slot[4];
	
	public CraftingGrid(File recipes) {
		slots[0] = new Slot(new Item(Item.NONE, 0, 0));
		slots[1] = new Slot(new Item(Item.NONE, 0, 0));
		slots[2] = new Slot(new Item(Item.NONE, 0, 0));
		slots[3] = new Slot(new Item(Item.NONE, 0, 0));
	}
	
	public void open() {
		shown = true;
	}
	
	public void close() {
		shown = false;
	}
	
	public boolean update(int mx, int my, boolean click) {
		if (!shown) return true;
		
		for (Slot s : slots) {
			if (!s.update(mx, my, click)) return false;
		}
		
		return true;
	}
	
	public void render(Batch b, OrthographicCamera c, float relativeX, float relativeY, float size, BitmapFont font) {
		if (!shown) {
			return;
		}
		
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
		sp.end();
		sp.begin(ShapeType.Line);
		sp.setColor(new Color(1f, 1f, 1f, 1f));
		sp.rect(relativeX, relativeY, size, size);
		sp.end();
		// Disable transparency blending
		Gdx.gl.glDisable(GL20.GL_BLEND);
		b.begin();
		
		slots[0].render(b, c, relativeX + (size / 2) - 200, relativeY + (size / 2), 100, font);
		slots[1].render(b, c, relativeX + (size / 2) - 100, relativeY + (size / 2), 100, font);
		slots[2].render(b, c, relativeX + (size / 2) - 200, relativeY + (size / 2) - 100, 100, font);
		slots[3].render(b, c, relativeX + (size / 2) - 100, relativeY + (size / 2) - 100, 100, font);
	}
}