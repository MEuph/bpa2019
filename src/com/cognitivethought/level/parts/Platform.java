package com.cognitivethought.level.parts;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Platform extends Sprite {
	
	public Platform(Texture t, float x, float y, float w, float h) {
		super(t);
		super.setX(x);
		super.setY(y);
		super.setSize(w, h);
	}
	
}