package com.cognitivethought.level.parts;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Platform extends Sprite {
	
	public boolean collideTop;
	public boolean collideBottom;
	public boolean collideRight;
	public boolean collideLeft;
	public boolean canHarm;
	
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
		this.collideTop = true;
		this.collideLeft = false;
		this.collideRight = false;
		this.collideBottom = false;
	}
	
	/**
	 * Basically just a placeholder of values for the player to interact with
	 * 
	 * @param t The texture to draw when rendering the platform
	 * @param x The horizontal position
	 * @param y The vertical position
	 * @param w The horizontal size
	 * @param h The vertical size
	 * @param collideTop Whether or not the player can collide with the top of the platform
	 * @param collideLeft Whether or not the player can collide with the left of the platform
	 * @param collideRight Whether or not the player can collide with the right of the platform
	 * @param collideBottom Whether or not the player can collide with the bottom of the platform
	 */
	public Platform(Texture t, float x, float y, float w, float h, boolean collideTop, boolean collideLeft, boolean collideRight, boolean collideBottom) {
		super(t);
		super.setX(x);
		super.setY(y);
		super.setSize(w, h);
		this.collideTop = collideTop;
		this.collideLeft = collideLeft;
		this.collideRight = collideRight;
		this.collideBottom = collideBottom;
	}
	
	/**
	 * Basically just a placeholder of values for the player to interact with
	 * 
	 * @param t The texture to draw when rendering the platform
	 * @param x The horizontal position
	 * @param y The vertical position
	 * @param w The horizontal size
	 * @param h The vertical size
	 * @param collideTop Whether or not the player can collide with the top of the platform
	 * @param collideLeft Whether or not the player can collide with the left of the platform
	 * @param collideRight Whether or not the player can collide with the right of the platform
	 * @param collideBottom Whether or not the player can collide with the bottom of the platform
	 * @param canHarm Whether or not the player can be hurt by this platform
	 */
	public Platform(Texture t, float x, float y, float w, float h, boolean collideTop, boolean collideLeft, boolean collideRight, boolean collideBottom, boolean canHarm) {
		super(t);
		super.setX(x);
		super.setY(y);
		super.setSize(w, h);
		this.collideTop = collideTop;
		this.collideLeft = collideLeft;
		this.collideRight = collideRight;
		this.collideBottom = collideBottom;
		this.canHarm = canHarm;
	}
}