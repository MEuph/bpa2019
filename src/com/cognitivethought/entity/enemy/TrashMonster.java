package com.cognitivethought.entity.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.cognitivethought.level.Level;
import com.cognitivethought.level.parts.Platform;
import com.cognitivethought.ui.HealthBar;

public class TrashMonster extends Enemy {
	
	Platform toBeOn;				// The platform the monster should be on
	
	int health;						// The health this monster has
	
	boolean movingLeft, movingRight;// Whether or not the monster is moving right or left
	
	float leftBound, rightBound;	// The left bound and right bound of the monster's movement
	
	float pauseTimer;				// How long to pause in between movements
	
	float dx, dy;					// The velocity of the monster
	
	boolean facingRight;			// Whether the monster is facing right or not
	
	private final float g = 0.198f; // Gravitational constant
	
	/**
	 * The first monster the player will encounter. A heaping mass of garbage that is thankfully
	 *  - for the sake of the player's nose - contained in a trash can.
	 * @param damageValue
	 * 		How much damage this particular trash monster will do
	 * @param texture
	 * 		The appearance of this particular trash monster
	 */
	public TrashMonster(float damageValue, Texture texture) {
		super(Behavior.EDGE_TO_EDGE, Behavior.MELEE, damageValue, texture);
		this.speed = 1f;	// Default speed to 1f
		this.dx = -speed;	// Default movement to the left
	}
	
	/**
	 * @see com.cognitivethought.entity.enemy.Enemy.move
	 */
	@Override
	void move(Level l) {
		// Check what platform the monster is on if the platform to be on is null
		if (toBeOn == null) {
			for (Platform p : l.getPlatforms()) {
				if (p.getBoundingRectangle().overlaps(getBoundingRectangle())) {
					toBeOn = p;
					leftBound = p.getX() - p.getWidth();
					rightBound = p.getX() + p.getWidth() * 2;
					System.out.println(leftBound + ", " + rightBound);
					System.out.println(getX() + ", "  + getY());
					break;
				}
			}
		}
		
		// If the monster hits either the left bound or the right bound, just flip it
		if (getX() <= leftBound || getX() >= rightBound) {
			dx *= -1;
			this.flip(true, false);
		}
	}
	
	/**
	 * Update the monster
	 */
	@Override
	public void update(HealthBar hb, Level l) {
		if (dy > -15f)
			dy -= g; // Simulate gravity constantly, with terminal velocity set to 15f
		
		// Same collision detection code as the player
		for (Platform plat : l.getPlatforms()) {
			if (new Rectangle(plat.getX()+1f, plat.getY()+1f, plat.getWidth()-2f, plat.getHeight()-2f).overlaps(getBoundingRectangle()) && dy < 0 && getY() >= plat.getY() + (plat.getHeight() / 2) + dy && plat.collideTop) {
				dy = 0;
				setY(plat.getY() + plat.getHeight() - 2f); // Reset y position to the top of the platform
			}
			
			if (new Rectangle(plat.getX()+1f, plat.getY()+1f, plat.getWidth()-2f, plat.getHeight()-2f).overlaps(getBoundingRectangle()) && dy > 0 && getY() + getHeight() >= plat.getY() + plat.getHeight()  && plat.collideBottom) {
				System.out.println(getY() + getHeight() + " " + plat.getY());
				dy = 0;
				setY(plat.getY() - getHeight() + plat.getHeight() + 2f); // Reset y position to the bottom of the platform
				break;
			}
			
			Rectangle leftOfPlatform = new Rectangle(plat.getX(), plat.getY(), 2f, plat.getHeight());
			if (leftOfPlatform.overlaps(getBoundingRectangle()) && dx > 0 && getX() + getWidth() + dx >= leftOfPlatform.getX() && plat.collideLeft && !(getY()>(plat.getY()+plat.getHeight())-4)) {
				dx *= -1;
				this.flip(true, false);
			}
			
			Rectangle rightOfPlatform = new Rectangle(plat.getX()+plat.getWidth()-2f, plat.getY(), 2f, plat.getHeight());
			if (rightOfPlatform.overlaps(getBoundingRectangle()) && dx < 0 && getX() <= plat.getX() + plat.getWidth() && plat.collideRight && !(getY()>(plat.getY()+plat.getHeight())-4)) {
				dx *= -1;
				this.flip(true, false);
			}
		}
		
		move(l); // Do movement code
		
		translateY(dy); // Do translations
		translateX(dx);
		
		attack(hb, l); // Do attacking
	}

	/**
	 * Process monster attacking
	 */
	@Override
	void attack(HealthBar hb, Level l) {
		attackRange = 5f;
		// if the player is in range, the monster can attack
		boolean canAttack =
			new Rectangle(getX() - attackRange, getY() - attackRange, getWidth() + (attackRange * 2), getHeight() + 
			(attackRange * 2)).overlaps(l.getSpawnpoint().getPlayer().getBoundingRectangle());
		
		// attack if the monster can attack
		if (canAttack) {
			if (!l.getSpawnpoint().getPlayer().flashing) {
				if (hb.bark >= 0) {
					hb.bark-=damageValue;
					attackTimer=100f;
				} else {
					hb.health-=damageValue;
					attackTimer=100;
				}
				l.getSpawnpoint().getPlayer().flashing = true;
				l.getSpawnpoint().getPlayer().flashTimer = 500f;
			}
		}
	}
	
	/**
	 * Draw the monster
	 */
	@Override
	public void draw(Batch batch) {
		super.draw(batch);
		
		if (facingRight) this.setFlip(true, false);
	}
}