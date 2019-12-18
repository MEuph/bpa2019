package com.cognitivethought.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Slot {
	
	private int id;
	private int quantity;
	
	public Slot(int id, int quantity) {
		this.id = id;
		this.quantity = quantity;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void render(Batch b, OrthographicCamera c, float x, float y, float size, BitmapFont font) {
		if (b.isDrawing()) b.end();
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		ShapeRenderer sp = new ShapeRenderer();
		sp.setProjectionMatrix(c.combined);
		sp.begin(ShapeType.Filled);
		sp.setColor(new Color(0f, 0f, 0f, 1f));
		sp.rect(x, y, size, size);
		sp.end();
		sp.begin(ShapeType.Line);
		sp.setColor(new Color(1f, 1f, 1f, 1f));
		sp.rect(x, y, size, size);
		sp.end();
		// Disable transparency blending
		Gdx.gl.glDisable(GL20.GL_BLEND);
		if (!b.isDrawing()) b.begin();
		
		// TODO: MAKE IT SO THAT WHEN YOU CLICK ON ONE OF THESE SLOTS, THE ITEM GETS PUT INTO THE SLOT!!!
		
		font.draw(b, this.quantity > 0 ? Integer.toString(this.quantity) : "", x+5, y+90);
	}
}