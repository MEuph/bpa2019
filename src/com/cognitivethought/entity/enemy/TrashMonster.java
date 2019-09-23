package com.cognitivethought.entity.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.cognitivethought.level.Level;
import com.cognitivethought.level.parts.Platform;
import com.cognitivethought.ui.HealthBar;

public class TrashMonster extends Enemy {
	
	Platform toBeOn;
	
	int jumps;
	int health;
	
	boolean movingLeft, movingRight;
	boolean flashing;
	
	float left, right;
	float pauseTimer;
	float flashTimer;
	float attackTimer;
	float dx, dy;
	
	public TrashMonster(float damageValue, Texture texture) {
		super(Behavior.EDGE_TO_EDGE, Behavior.MELEE, damageValue, texture);
	}

	@Override
	void move(Level l) {
		if (toBeOn == null) {
			for (Platform p : l.getPlatforms()) {
				if (p.getBoundingRectangle().overlaps(getBoundingRectangle())) {
					toBeOn = p;
					left = p.getX();
					right = p.getX() - getWidth();
					break;
				}
			}
		}
		
		if (getX() == left || getX() == right) {
			pauseTimer = 1f;
			if (getX() == left) {
				movingLeft = false;
				movingRight = true;
			}
			if (getX() == right) {
				movingLeft = true;
				movingRight = false;
			}
		}
		
		if (pauseTimer > 0f) {
			pauseTimer -= 0.005f;
		} else {
			pauseTimer = 0f;
		}
		
		if (pauseTimer == 0f) {
			translateX(movingLeft ? -speed : movingRight ? speed : 0f);
		}
	}

	@Override
	void update(HealthBar hb, Level l) {
		for (Platform plat : l.getPlatforms()) {
			if (new Rectangle(plat.getX()+1f, plat.getY()+1f, plat.getWidth()-2f, plat.getHeight()-2f).overlaps(getBoundingRectangle()) && dy < 0 && getY() >= plat.getY() + (plat.getHeight() / 2) + dy && plat.collideTop) {
				dy = 0;
				setY(plat.getY() + plat.getHeight() - 2f); // Reset y position to the top of the platform
				jumps = 0;
				if (plat.canHarm && !this.flashing) {
					health--;
					this.flashing = true;
					this.flashTimer = 500f;
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

			if (this.flashing && flashTimer > 0f) {
				flashTimer -= 0.05f; 
			}
			else {
				flashTimer = 500f;
				flashing = false;
			}
		}
		
		attack(hb, l);
	}

	@Override
	void attack(HealthBar hb, Level l) {
		boolean canAttack =
					new Rectangle(getX() - attackRange, getY() - attackRange, getWidth() + attackRange * 2, getHeight() + attackRange * 2).overlaps(l.getSpawnpoint().getPlayer().getBoundingRectangle());
		
		if (canAttack) {
			if (attackTimer <= 0) {
				if (hb.bark >= 0) {
					hb.bark-=damageValue;
					attackTimer=1000f;
				} else {
					hb.health-=damageValue;
					attackTimer=1000f;
				}
			} else {
				attackTimer -= 1f;
			}
		}
	}
}