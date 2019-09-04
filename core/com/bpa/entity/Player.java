package com.bpa.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player extends Sprite {
	
	// The placeholder position of the player. Used because it's easier than writing getX(), setX(), etc.
	private float x, y;
	
	// The velocity of the player
	private float dx, dy;
	
	// How many times the player has jumped since their initial jump
	// Used for double-jump calculations
	private int jumps;
	
	// Whether or not the player is mid-jump
	private boolean jumping;
	
	// Instantiates a new Player in the scene. Required for the game to function
	public Player(Texture t) {
		super(t);
	}
	
	public void update() {
		translateX(dx);
		translateY(dy);
		
		x = getX();
		y = getY();
		
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			dx = -1;
		} else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			dx = 1;
		} else {
			dx = 0;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			jump();
		}
		
		if (jumping) {
			dy -= dy > -5f ? 0.1f : 0;
			System.out.println(dy);
		}
		
		// TODO: Fix jump code
		
		if (collideWithGround()) {
			dy = 0;
		}
		
		setX(x);
		setY(y);
	}
	
	public boolean collideWithGround() {
		return getY() < 0;
	}
	
	public void jump() {
		if (jumps < 2 && !jumping) {
			System.out.println("jump");
			dy = 5f;
			jumping = true;
			jumps++;
		}
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}