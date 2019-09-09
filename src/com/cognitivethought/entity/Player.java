package com.cognitivethought.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cognitivethought.level.Level;
import com.cognitivethought.level.parts.Platform;

public class Player extends Sprite {

	// The velocity of the player
	private float dx, dy;

	// Gravitational constant
	private final float g = 0.198f;

	// Multiplied by the velocity when moving the player
	private final float speed = 2f;

	// How many times the player has jumped in a given jump-cycle
	private int jumps = 0;

	// Whether the sprite is facing right (flipped)
	private boolean facingRight;

	/**
	 * Instantiates a new Player in the scene
	 * 
	 * @param t The image of the player
	 */
	public Player(Texture t) {
		super(t);
		setSize(getWidth() * 2.5f, getHeight() * 2.5f); // Make sure the player isn't incredibly small
	}

	/**
	 * Controls player physics and movement
	 * 
	 * @param l Used for collision detection purposes
	 */
	public void update(Level l) {
		if (dy > -15f)
			dy -= g; // Simulate gravity constantly, with terminal velocity set to 15f

		float vxChange = .5f; // How much to increment horizontal movement during smooth-movement calculations
		float maxSpeed = 5f; // The maximum absolute value that the horizontal velocity can ever be

		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			dx += dx > -maxSpeed ? -vxChange : 0; // if dx has not yet reached maximum speed, increment it
			dx = dx < -maxSpeed ? -maxSpeed : dx; // cap dx at its maximum speed
			facingRight = false; // player is not facing right
		} else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			dx += dx < maxSpeed ? vxChange : 0; // if dx has not yet reached maximum speed, increment it
			dx = dx > maxSpeed ? maxSpeed : dx; // cap dx at its maximum speed
			facingRight = true; // player is not facing right
		} else {
			dx = dx != 0 ? (dx < 0 ? dx + vxChange : dx - vxChange) : 0; // normalize dx to 0 by using vxChange
			dx = dx > -1 && dx < 1 ? 0 : dx; // if dx is between -1 and 1, just set it to 0. This makes it so vxChange
												// can be any value
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			jump();
			jumps++;
		}

		// If colliding with platform top
		// SOON TO BE OBSOLETE. DO NOT RELY ON THIS CODE
		for (Platform p : l.getPlatforms()) {
			// If the platform overlaps the player, and the player is moving down, and the
			// player's y is greater than the platform's y
			if (super.getBoundingRectangle().overlaps(p.getBoundingRectangle()) && dy < 0 && getY() > p.getY()) {
				dy = 0; // Stop all vertical movement
				setY(p.getY() + p.getHeight()); // Make sure y is correctly set
				jumps = 0; // Reset jump counter
				break;
			}
		}

		setX(getX() + dx * speed); // Could use translate, but this is cooler
		setY(getY() + dy * speed);
	}

	/**
	 * Makes the player jump
	 */
	void jump() {
		if (jumps >= 2)
			return;
		dy = 4f; // Literally all jump does is set vertical velocity to +4 instantly.
	}

	/**
	 * Draws the player
	 * 
	 * @param sb The same SpriteBatch that is rendering other objects in the Screen
	 */
	public void render(SpriteBatch sb) {
		// Facing right explanation: if the player is facing right, then draw the player
		// at an increased x value, but with a negative width,
		// otherwise just draw player normally
		sb.draw(getTexture(), !facingRight ? getX() : getX() + getWidth(), getY(),
				!facingRight ? getWidth() : -getWidth(), getHeight());
	}
}