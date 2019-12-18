package com.cognitivethought.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class Slot {
	
	private Item heldItem;
	
	private float x, y, size;
	
	private boolean highlighted = false;
	
	public Slot(Item heldItem) {
		this.heldItem = heldItem;
		
		if (this.heldItem == null) heldItem = new Item(Item.NONE, 0, 0);
	}
	
	public void setHeldItem(Item heldItem) {
		this.heldItem = heldItem;
	}
	
	public Item getHeldItem() {
		return heldItem;
	}
	
	public void updateHighlight(int mx, int my) {
		highlighted = new Rectangle(this.x, this.y, this.size, this.size).contains(mx, my);
	}
	
	public boolean updateClick(int mx, int my) {
		if(!highlighted) return false;
		
		Item temp = InventoryBar.currentlyHeldItem;
		InventoryBar.currentlyHeldItem = heldItem;
		heldItem = temp;
		
		return true;
	}
	
	public void render(Batch b, OrthographicCamera c, float x, float y, float size, BitmapFont font) {
		this.x = x;
		this.y = y;
		this.size = size;
		
		if (b.isDrawing()) b.end();
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		ShapeRenderer sp = new ShapeRenderer();
		sp.setProjectionMatrix(c.combined);
		sp.begin(ShapeType.Filled);
		sp.setColor(highlighted ? new Color(0.5f, 0.5f, 0.5f, 1f) : new Color(0f, 0f, 0f, 1f));
		sp.rect(x, y, size, size);
		sp.end();
		sp.begin(ShapeType.Line);
		sp.setColor(new Color(1f, 1f, 1f, 1f));
		sp.rect(x, y, size, size);
		sp.end();
		// Disable transparency blending
		Gdx.gl.glDisable(GL20.GL_BLEND);
		if (!b.isDrawing()) b.begin();
		
		if (heldItem.getId() != Item.NONE) {
			b.draw(Item.getTexture(heldItem.getId()), x + 5, y + 5, size - 10, size - 10);
			font.draw(b, heldItem.getQuantity() > 0 ? Integer.toString(heldItem.getQuantity()) : "", x+5, y+90);
		}
	}

}