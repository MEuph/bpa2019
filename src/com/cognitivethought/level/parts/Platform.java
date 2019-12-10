package com.cognitivethought.level.parts;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Platform extends Sprite {
	
	public boolean collideTop;
	public boolean collideBottom;
	public boolean collideRight;
	public boolean collideLeft;
	public boolean canHarm;
	public boolean endsLevel;
	
	private int posX, posY;
	
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
	 * @param endsLevel Whether or not the player can end the level with this platform
	 */
	public Platform(Texture t, float x, float y, float w, float h, boolean collideTop, boolean collideLeft, boolean collideRight, boolean collideBottom, boolean canHarm, boolean endsLevel) {
		super(t);
		super.setX(x);
		super.setY(y);
		super.setSize(w, h);
		this.collideTop = collideTop;
		this.collideLeft = collideLeft;
		this.collideRight = collideRight;
		this.collideBottom = collideBottom;
		this.canHarm = canHarm;
		this.endsLevel = endsLevel;
	}
	
	public Platform(Texture t, float x, float y, float w, float h, int posX, int posY) {
		super(t);
		super.setX(x);
		super.setY(y);
		super.setSize(w, h);
		this.posX = posX;
		this.posY = posY;
	}

	public void updateTexture(ArrayList<Platform> platforms, int width) {
		ArrayList<Platform> temp = new ArrayList<Platform>();
		Platform[][] change = new Platform[platforms.size() / width][width];
		
		int pos = platforms.indexOf(this);
		boolean dirt = this.getTexture() == new Texture("assets/Tilesets/Tutorial Tileset/filledplat.png");
		boolean grass = this.getTexture() == new Texture("assets/Tilesets/Tutorial Tileset/filledtopplat.png");
		
		boolean top, bottom, left, right;
		top = bottom = left = right = false;
		
		if (dirt) {
			if (platforms.get(above(pos, width)).getTexture() == new Texture("assets/Tilesets/Tutorial Tileset/filledplat.png")) {
				top = true;
			}
			if (platforms.get(below(pos, width)).getTexture() == new Texture("assets/Tilesets/Tutorial Tileset/filledplat.png")) {
				bottom = true;
			}
			if (platforms.get(left(pos, width)).getTexture() == new Texture("assets/Tilesets/Tutorial Tileset/filledplat.png")) {
				left = true;
			}
			if (platforms.get(right(pos, width)).getTexture() == new Texture("assets/Tilesets/Tutorial Tileset/filledplat.png")) {
				right = true;
			}
			
			if (top && right && left && !bottom) {
				
			}
		} else if (grass) {
			if (platforms.get(above(pos, width)).getTexture() == new Texture("assets/Tilesets/Tutorial Tileset/filledtopplat.png")) {
				top = true;
			}
			if (platforms.get(below(pos, width)).getTexture() == new Texture("assets/Tilesets/Tutorial Tileset/filledtopplat.png")) {
				bottom = true;
			}
			if (platforms.get(left(pos, width)).getTexture() == new Texture("assets/Tilesets/Tutorial Tileset/filledtopplat.png")) {
				left = true;
			}
			if (platforms.get(right(pos, width)).getTexture() == new Texture("assets/Tilesets/Tutorial Tileset/filledtopplat.png")) {
				right = true;
			}
		} else {
			return;
		}
		
		for (int i = 0; i < change.length; i++) {
			for (int j = 0; j < change[i].length; j++) {
				temp.add(change[i][j]);
			}
		}
	}
	
	int above(int pos, int width) {
		return pos - width;
	}
	
	int below(int pos, int width) {
		return pos + width;
	}
	
	int left(int pos, int width) {
		return pos - 1;
	}
	
	int right(int pos, int width) {
		return pos + 1;
	}

}
