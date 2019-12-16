package com.cognitivethought.gui;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class TextBubble extends Sprite {
	
	BitmapFont font;
	String textToDisplay;
	
	public TextBubble(float x, float y, float w, float h) {
		setX(x);
		setY(y);
		setSize(w, h);
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(new FileHandle("assets/Fonts/times-new-roman.ttf"));
		
	}
	
	public void render(Batch b) {
		
	}
}