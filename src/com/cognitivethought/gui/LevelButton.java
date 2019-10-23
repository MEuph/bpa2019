package com.cognitivethought.gui;

import com.badlogic.gdx.graphics.Texture;

public class LevelButton {

	public ImageButton level;
	private int buttonWidth = 70;
	private int buttonHeight = buttonWidth*197/280;
	private int buttonX = 0;
	private int buttonY = 500;

	public void addButton(int levelNum) {
		
		Texture texture = new Texture("assets/UI/PlayButton.png");
		buttonX = levelNum * 250;
				
		
		level = new ImageButton(texture, buttonX, buttonY, buttonWidth, buttonHeight);
	}
}
