package com.cognitivethought.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.cognitivethought.resources.Resources;

public class ShopSlot {

	public Item toBuy = new Item(Item.NONE, 0, 0);
	
	private boolean highlighted = false;
	
	public float x, y, size;
	public int mx, my;
	public int cost, stock;
	
	GlyphLayout gl;
	
	public ShopSlot(Item toBuy, int cost, int stock) {
		this.toBuy = toBuy;
		this.cost = cost;
		this.stock = stock;
		
		gl = new GlyphLayout();
	}
	
	public void updateHighlight(int mx, int my) {
		highlighted = new Rectangle(this.x, this.y, this.size, this.size).contains(mx, my);
		this.mx = mx;
		this.my = my;
	}
	
	public boolean updateClick(int mx, int my) {
		if(!highlighted) return false;
		
		if (!(InventoryBar.currentlyHeldItem.getId() == Item.NONE || InventoryBar.currentlyHeldItem.getId() == toBuy.getId())) return false;
		if (stock <= 0) return false;
		
		int coins = 0;
		for (int i = 0; i < InventoryBar.i.getItems().size(); i++) {
			if (InventoryBar.i.getItems().get(i).getId() == Item.COIN) {
				coins = InventoryBar.i.getItems().get(i).getQuantity();
			}
		}
		if (coins < cost) return false;
		if (InventoryBar.currentlyHeldItem.getId() == Item.NONE) {
			Item i = new Item(toBuy.getId(), toBuy.getQuantity(), 0);
			InventoryBar.currentlyHeldItem = i;
		} else {
			InventoryBar.currentlyHeldItem.increment();
		}
		
		stock--;
		for (int i = 0; i < InventoryBar.i.getItems().size(); i++) {
			if (InventoryBar.i.getItems().get(i).getId() == Item.COIN) {
				for (int j = 0; j < cost; j++) {
					InventoryBar.i.getItems().get(i).decrement();
				}
			}
		}
		
		
		return true;
	}
	
	public void render(Batch b, OrthographicCamera c, int x, int y, int size, BitmapFont font,
			ShapeRenderer sp) {
		this.x = x;
		this.y = y;
		this.size = size;

		if (b.isDrawing())
			b.end();
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		sp.setProjectionMatrix(c.combined);
		if (!sp.isDrawing())
			sp.begin(ShapeType.Filled);
		sp.setColor(highlighted ? new Color(0.5f, 0.5f, 0.5f, 1f) : new Color(0f, 0f, 0f, 1f));
		sp.rect(x, y, size, size);
		sp.end();
		sp.begin(ShapeType.Line);
		sp.setColor(new Color(1f, 1f, 1f, 1f));
		sp.rect(x, y, size, size);
		if (sp.isDrawing())
			sp.end();
		// Disable transparency blending
		Gdx.gl.glDisable(GL20.GL_BLEND);
		if (!b.isDrawing())
			b.begin();

		b.draw(Item.getTexture(toBuy.getId()), x + 5, y + 5, size - 10, size - 10);
		if (stock <= 0) b.draw(Resources.X, x, y, size, size);
		gl.setText(font, stock > 0 ? Integer.toString(stock) : "None!");
		font.draw(b, stock > 0 ? Integer.toString(stock) : "None!", x + (size / 2) - (gl.width / 2), y + size + gl.height + 5);
		gl.setText(font, "$" + Integer.toString(cost));
		font.draw(b, "$" + Integer.toString(cost), x + (size / 2) - (gl.width / 2), y + size + (gl.height * 2) + 15);
	}
}