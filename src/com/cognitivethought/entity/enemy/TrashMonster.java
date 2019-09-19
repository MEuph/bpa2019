package com.cognitivethought.entity.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.cognitivethought.level.Level;
import com.cognitivethought.level.parts.Platform;

public class TrashMonster extends Enemy {
	
	private Platform toBeOn;
	
	boolean movingLeft, movingRight;
	
	float left, right;
	float pauseTimer;
	
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
	void update(Level l) {
		
	}

	@Override
	void attack(Level l) {
		
	}

	@Override
	void render(Batch b) {
		
	}

}
