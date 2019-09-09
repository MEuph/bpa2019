package com.cognitivethought.level.parts;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Platform extends Sprite {

	/**
	 * Basically just a placeholder of values for the player to interact with
	 * 
	 * @param t The texture to draw when rendering the platform
	 * @param x The horizontal position
	 * @param y The vertical position
	 * @param w The horizontal size
	 * @param h The vertical size
	 */
	public Platform(Texture t, float x, float y, float w, float h) {
		super(t);
		super.setX(x);
		super.setY(y);
		super.setSize(w, h);
	}
}