package com.cognitivethought.ui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.cognitivethought.entity.enemy.Enemy;
import com.cognitivethought.entity.enemy.TrashMonster;

public class HealthBar {
	
	public int health = 3;
	public int bark = 2;
	
	private Sprite heartImg;
	private Sprite barkImg;
	private Sprite monsterHeart;
	
	private Enemy tm = null;
	
	private float size;
	
	public HealthBar() {
		heartImg = new Sprite(new Texture("assets/UI/heart.png"));
		barkImg = new Sprite(new Texture("assets/UI/bark.png"));
	}
	
	public HealthBar(Enemy trashMonster, int initialHealth) {
		this.tm = trashMonster;
		heartImg = new Sprite(new Texture("assets/UI/heart.png"));
		monsterHeart = new Sprite(new Texture("assets/UI/monsterHeart.png"));
		this.health = initialHealth;
		
		size = (tm.getWidth() / health) + 10;
	}

	public void render(Batch b, OrthographicCamera c) {
		if (health <= 0) {
			health = 0;
		}
		
		if (tm == null) {
			for (int i = 0; i < health; i++) {
				if (i <= bark) {
					b.draw(barkImg, (c.position.x + 32 + (60*i) - c.viewportWidth / 2) / c.zoom, (c.position.y + 32 - c.viewportHeight / 2) / c.zoom, 64, 64);
				} else {
					b.draw(heartImg, (c.position.x + 32 + (60*i) - c.viewportWidth / 2) / c.zoom, (c.position.y + 32 - c.viewportHeight / 2) / c.zoom, 64, 64);
				}
			}
		} else {
			for (int i = 0; i < health; i++) {
				if (health > 0) {
					b.draw(monsterHeart, (tm.getX() + size + ((size + 2) * i)) - size, (tm.getY() + tm.getHeight() + 10), size, size);
				}
			}
		}
	}
}