package com.cognitivethought.entity;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.cognitivethought.entity.enemy.Enemy;
import com.cognitivethought.level.parts.Platform;

public class Projectile extends Sprite {
	
	public float life, size, dx, dy;
	
	public Vector2 target;
	
	public Projectile(Texture t, float x, float y, float vx, float vy, float life, float size) {
		super(t);
		setX(x);
		setY(y);
		this.dx = vx;
		this.dy = vy;
		this.life = life;
		this.size = size;
	}
	
	public void update() {
		translate(dx, dy);
		
	//	dy -= 9.81 * Gdx.graphics.getDeltaTime();
		
		if (dy < -9.81*2) {
			dy = -9.81f*2;
		}
		
		life -= Gdx.graphics.getDeltaTime();
	}
	
	public boolean checkHit(Enemy e) {
		if (e.getBoundingRectangle().contains(this.getBoundingRectangle()) && !(e.deathThreadTime > 0)) {
			e.die();
			return true;
		}
		return false;
	}
	
	public void render(Batch b) {
		this.draw(b);
	}

	public boolean hitWall(ArrayList<Platform> platforms) {
		for(Platform p : platforms) {
			return new Rectangle(p.getX(), p.getY(), p.getWidth(), p.getHeight()).overlaps(getBoundingRectangle());
		}
		return false;
	}
}