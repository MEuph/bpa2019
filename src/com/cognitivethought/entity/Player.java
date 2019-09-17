package com.cognitivethought.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.cognitivethought.level.Level;
import com.cognitivethought.level.parts.Platform;
import com.cognitivethought.ui.HealthBar;

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
	
	private boolean left, right;
	
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
	public void update(Level l, HealthBar hb) {
		if (dy > -15f)
			dy -= g; // Simulate gravity constantly, with terminal velocity set to 15f

		float vxChange = .5f; // How much to increment horizontal movement during smooth-movement calculations
		float maxSpeed = 5f; // The maximum absolute value that the horizontal velocity can ever be
		
		if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.NUMPAD_4)) {
			left = true;
			right = false;
		} else if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.NUMPAD_6)) {
			right = true;
			left = false;
		} else {
			left = false;
			right = false;
		}
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.W) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.UP) || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_4)) {
			jump();
			jumps++;
		}

		// If colliding with platform top
		// SOON TO BE OBSOLETE. DO NOT RELY ON THIS CODE
		for (Platform plat : l.getPlatforms()) {
			if (new Rectangle(plat.getX()+1f, plat.getY()+1f, plat.getWidth()-2f, plat.getHeight()-2f).overlaps(getBoundingRectangle()) && dy < 0 && getY() >= plat.getY() + (plat.getHeight() / 2) + dy && plat.collideTop) {
				dy = 0;
				setY(plat.getY() + plat.getHeight() - 2f); // Reset y position to the top of the platform
				jumps = 0;
				if (plat.canHarm) {
					if (hb.bark > 0) {
						hb.bark--;
					}
				}
			}
			
			if (new Rectangle(plat.getX()+1f, plat.getY()+1f, plat.getWidth()-2f, plat.getHeight()-2f).overlaps(getBoundingRectangle()) && dy > 0 && getY() + getHeight() >= plat.getY() + plat.getHeight()  && plat.collideBottom) {
				System.out.println(getY() + getHeight() + " " + plat.getY());
				dy = 0;
				setY(plat.getY() - getHeight() + plat.getHeight() + 2f); // Reset y position to the bottom of the platform
				break;
			}
			
			Rectangle leftOfPlatform = new Rectangle(plat.getX(), plat.getY(), 2f, plat.getHeight());
			if (leftOfPlatform.overlaps(getBoundingRectangle()) && dx > 0 && getX() + getWidth() + dx >= leftOfPlatform.getX() && plat.collideLeft && !(getY()>(plat.getY()+plat.getHeight())-4)) {
				dx = 0;
				setX(plat.getX() - getWidth()); // Reset x position to the left of the platform
			}
			
			Rectangle rightOfPlatform = new Rectangle(plat.getX()+plat.getWidth()-2f, plat.getY(), 2f, plat.getHeight());
			if (rightOfPlatform.overlaps(getBoundingRectangle()) && dx < 0 && getX() <= plat.getX() + plat.getWidth() && plat.collideRight && !(getY()>(plat.getY()+plat.getHeight())-4)) {
				dx = 0;
				setX(plat.getX() + plat.getWidth()); // Reset x position to the right of the platform
			}
		}
		
		if (left) {
			dx += dx > -maxSpeed ? -vxChange : 0; // if dx has not yet reached maximum speed, increment it
			dx = dx < -maxSpeed ? -maxSpeed : dx; // cap dx at its maximum speed
			facingRight = false;
		} else if (right) {
			dx += dx < maxSpeed ? vxChange : 0; // if dx has not yet reached maximum speed, increment it
			dx = dx > maxSpeed ? maxSpeed : dx; // cap dx at its maximum speed
			facingRight = true;
		} else {
			dx = dx != 0 ? (dx < 0 ? dx + vxChange : dx - vxChange) : 0; // normalize dx to 0 by using vxChange
			dx = dx > -1 && dx < 1 ? 0 : dx; // if dx is between -1 and 1, just set it to 0. This makes it so vxChange
		}

		setX(getX() + dx * speed); // Could use translate, but this works too
		setY(getY() + dy * speed);
	}

	/**
	 * Makes the player jump
	 */
	boolean jump() {
		if (jumps >= 2)
			return false;
		dy = 4f; // Literally all jump does is set vertical velocity to +4 instantly.
		translateY(1);
		return true;
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