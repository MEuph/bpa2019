package com.cognitivethought.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ItemDrop extends Sprite {
	
	public Texture t;
	
	public float x, y, w, h;
	
	public ItemDrop(String path) {
		t = new Texture(path);
	}
	
	public void update (TreePlayer p) {
		
	}
	
	public void draw(SpriteBatch b) {
		b.draw(t, x, y, w, h);
	}
}