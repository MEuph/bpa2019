package com.cognitivethought.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ItemDrop {
	
	public Texture t;
	
	public float x, y, w, h;
	
	public ItemDrop(String path) {
		t = new Texture(path);
	}
	
	public void draw(SpriteBatch b) {
		b.draw(t, x, y, w, h);
	}
	
}