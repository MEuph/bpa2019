package com.cognitivethought.ui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class HealthBar {
	
	public int health = 1;
	public int bark = -1;
	
	private Sprite heartImg;
	private Sprite barkImg;
	
	public HealthBar() {
		heartImg = new Sprite(new Texture("assets/UI/heart.png"));
		barkImg = new Sprite(new Texture("assets/UI/bark.png"));
	}
	
	public void render(Batch b, OrthographicCamera c) {
		for (int i = 0; i < health; i++) {
			if (i <= bark) {
				b.draw(barkImg, (c.position.x + 32 + (60*i) - c.viewportWidth / 2) / c.zoom, (c.position.y + 32 - c.viewportHeight / 2) / c.zoom, 64, 64);
			} else {
				b.draw(heartImg, (c.position.x + 32 + (60*i) - c.viewportWidth / 2) / c.zoom, (c.position.y + 32 - c.viewportHeight / 2) / c.zoom, 64, 64);
			}
		}
	}
}