package com.cognitivethought.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cognitivethought.level.Level;
import com.cognitivethought.level.parts.Platform;

public class Player extends Sprite  {
	
	// The velocity of the player
	private float dx, dy;
	
	// Gravitational constant
	private final float g = 0.198f;
	
	private final float speed = 2f;
	
	// How many times the player has jumped in a given jump-cycle
	private int jumps = 0;
	
	// Whether the sprite is facing right (flipped)
	private boolean facingRight;
	
	// Instantiates a new Player in the scene
	public Player(Texture t) {
		super(t);
		setSize(getWidth()*2.5f, getHeight()*2.5f);
	}
	
	// Controls player physics and movement
	public void update(Level l) {
		dy = dy > -15f ? (dy - g) : dy;
		
		float vxChange = .5f;
		float maxSpeed = 5f;
		
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			dx += dx > -maxSpeed ? -vxChange : 0;
			dx = dx < -maxSpeed ? -maxSpeed : dx;
			facingRight = false;
		} else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			dx += dx < maxSpeed ? vxChange : 0;
			dx = dx > maxSpeed ? maxSpeed : dx;
			facingRight = true;
		} else {
			dx = dx != 0 ? (dx < 0 ? dx + vxChange : dx - vxChange) : 0;
			dx = dx > -1 && dx < 1 ? 0 : dx;
		}
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			jump();
			jumps++;
		}
		
		// If colliding with platform top
		for (Platform p : l.getPlatforms()) {
			if (super.getBoundingRectangle().overlaps(p.getBoundingRectangle()) && dy < 0 && getY() > p.getY()) {
				dy = 0;
				setY(p.getY() + p.getHeight());
				jumps = 0;
				break;
			}
		}
		
		setX(getX() + dx * speed);
		setY(getY() + dy * speed);
	}
	
	void jump() {
		if (jumps >= 2) return;
		dy = 4f;
		System.out.println("line 103");
	}
	
	public void render(SpriteBatch sb) {
		sb.draw(getTexture(), !facingRight ? getX() : getX() + getWidth(), getY(), !facingRight ? getWidth() : -getWidth(), getHeight());
	}
}