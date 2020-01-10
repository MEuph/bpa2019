package com.cognitivethought.screens;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cognitivethought.main.desktop.DesktopLauncher;

public class Cutscene {
	
	public Sprite[] images;
	public int[] times;
	
	int currentFrame;
	
	boolean completed = false;
	
	public Cutscene(int[] times, Sprite[] images) {
		this.times = times;
		this.images = images;
		
		for (int i = 0; i < images.length; i++) {
			images[i].setPosition(0, 0);
			images[i].setSize(1920, 1080);
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
		try {
			Thread.sleep(currentFrame < images.length ? times[currentFrame] : 0);
		} catch (InterruptedException e) {
			DesktopLauncher.log();
			e.printStackTrace();
		}
		currentFrame++;
		
		if (currentFrame >= images.length) {
			completed = true;
			return;
		}
		
		images[currentFrame].draw(b);
	}
}