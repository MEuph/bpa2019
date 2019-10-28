package com.cognitivethought.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.cognitivethought.entity.enemy.Enemy;

public class Projectile extends Sprite {
	
	public float dx, dy, life, size;
	
	public Projectile(Texture t, float x, float y, float dx, float dy, float life, float size) {
		super(t);
		this.dx = dx;
		this.dy = dy;
		this.life = life;
		this.size = size;
	}
	
	public void update() {
		translate(dx, dy);
		
		life -= Gdx.graphics.getDeltaTime();
	}
	
	public void onHit(Enemy e) {
		if (e.getBoundingRectangle().contains(this.getBoundingRectangle())) {
			e.die();
		}
	}
	
	public void render(Batch b) {
		this.draw(b);
	}
}