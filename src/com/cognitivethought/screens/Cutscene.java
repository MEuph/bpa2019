package com.cognitivethought.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Holds cutscene data
 */
public class Cutscene {
	
	public Sprite[] images;
	public int[] times;
	
	int currentFrame = 0;
	
	boolean completed = false;
	
	public Screen toAdvanceTo;
	
	int timer = 0;
	
	public Cutscene(int[] times, Sprite[] images) {
		this.times = times;
		this.images = images;
		
		for (int i = 0; i < images.length; i++) {
			images[i].setSize(1080, 1080);
			images[i].setPosition(1920 / 2 - (images[i].getWidth() / 2), 1080 / 2 - (images[i].getHeight() / 2));
		}
	}
	
	public boolean isCompleted() {
		return completed;
	}
	
	public void reset() {
		currentFrame = 0;
		completed = false;
	}
	
	public void draw(SpriteBatch b) {
		System.out.println(timer);
		
		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			completed = true;
		}
		
		if (currentFrame >= times.length) return;
		
		if (timer >= times[currentFrame]) {
			timer = 0;
			currentFrame++;
		}
		
		timer += 17;
		
		if (currentFrame >= images.length) {
			completed = true;
			return;
		}
		
		images[currentFrame].draw(b);
	}
}