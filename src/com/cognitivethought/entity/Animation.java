package com.cognitivethought.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Animation {
	/**
	 * 
	 */
	
	public Texture spriteSheet; 
	
	float timer;
	
	int currentSet;
	int currentFrame;
	
	public int tileW, tileH;
	
	public Animation(int tileW, int tileH, Image toSplit) {
		this.tileW = tileW;
		this.tileH = tileH;
	}
	
	public void update(float time) {
		timer += Gdx.graphics.getDeltaTime();
		
		if (timer >= time) {
			timer = 0;
			currentFrame++;
			if (currentFrame > spriteSheet.getWidth() / tileW) {
				currentFrame = 0;
			}
		}
	}
	
	public void drawCurrentFrame(Batch b, float x, float y, float w, float h, boolean flippedX, boolean flippedY) {
		b.draw(spriteSheet, x, y, w, h, currentFrame*tileW, 0, tileW, tileH, flippedX, flippedY);
	}
}