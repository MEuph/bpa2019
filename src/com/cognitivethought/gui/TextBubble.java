package com.cognitivethought.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.Align;
import com.cognitivethought.entity.TreePlayer;
import com.cognitivethought.resources.Resources;

public class TextBubble extends Sprite {
	
	BitmapFont font;
	String textToDisplay;
	boolean isVisible;
	
	public TextBubble(String toDisplay, float x, float y, float w, float h) {
		setX(x);
		setY(y);
		setSize(w, h);
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(new FileHandle("assets/Fonts/times-new-roman.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 20;
		font = generator.generateFont(parameter);
		generator.dispose();
	}
	
	public void activate() {
		isVisible = true;
	}
	
	public void disable() {
		isVisible = false;
	}
	
	public void whileVisible(TreePlayer p) {
		if (!isVisible) return;
		
		p.left = p.right = false;
		p.dx = 0;
		p.dy = 0;
		
		if (Gdx.input.isKeyJustPressed(Keys.ANY_KEY)) {
			disable();
		}
	}
	
	public void setTextToDisplay(String textToDisplay) {
		this.textToDisplay = textToDisplay;
	}
	
	public void render(Batch b) {
		font.draw(b, textToDisplay, getX() - 100, getY() - getHeight(), getWidth() + 100, Align.center, true);
		b.draw(Resources.BIRD, getX(), getY(), 39 * 2, 32 * 2);
	}
}