package com.cognitivethought.entity.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.cognitivethought.level.Level;
import com.cognitivethought.level.parts.Platform;
import com.cognitivethought.ui.HealthBar;

public class TrashMonster extends Enemy {
	
	Platform toBeOn;
	
	int health;
	
	boolean movingLeft, movingRight;
	
	float left, right;
	
	float pauseTimer;
	
	float dx, dy;
	
	boolean facingRight;
	
	private final float g = 0.198f;
	
	public TrashMonster(float damageValue, Texture texture) {
		super(Behavior.EDGE_TO_EDGE, Behavior.MELEE, damageValue, texture);
		this.speed = 1f;
		this.dx = -speed;
	}

	@Override
	void move(Level l) {
		if (toBeOn == null) {
			for (Platform p : l.getPlatforms()) {
				if (p.getBoundingRectangle().overlaps(getBoundingRectangle())) {
					toBeOn = p;
					left = p.getX() - p.getWidth();
					right = p.getX() + p.getWidth() * 2;
					System.out.println(left + ", " + right);
					System.out.println(getX() + ", "  + getY());
					break;
				}
			}
		}
		
		if (getX() <= left || getX() >= right) {
			dx *= -1;
			this.flip(true, false);
		}
	}

	@Override
	public void update(HealthBar hb, Level l) {
		if (dy > -15f)
			dy -= g; // Simulate gravity constantly, with terminal velocity set to 15f
		
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
		
		move(l);
		
		translateY(dy);
		translateX(dx);
		
		attack(hb, l);
	}

	@Override
	void attack(HealthBar hb, Level l) {
		attackRange = 5f;
		boolean canAttack =
					new Rectangle(getX() - attackRange, getY() - attackRange, getWidth() + (attackRange * 2), getHeight() + (attackRange * 2)).overlaps(l.getSpawnpoint().getPlayer().getBoundingRectangle());
		
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
	
	@Override
	public void draw(Batch batch) {
		super.draw(batch);
		
		if (facingRight) this.setFlip(true, false);
	}
}