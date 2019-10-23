package com.cognitivethought.gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.graphics.Texture;

public class LevelButton {
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public ImageButton level;
	private int buttonWidth = 287;
	private int buttonHeight = buttonWidth*133/287;
	private int buttonX = 0;
	private int buttonY = 500;

	public void addButton(int levelNum) {
		Texture texture;
		switch (levelNum) {
			case (0):
				texture = new Texture("assets/UI/LeveloneButton.png.png");
				break;
			case (1):
				texture = new Texture("assets/UI/LeveltwoButton.png.png");
				break;
			case (2):
				texture = new Texture("assets/UI/LevelthreeButton.png.png");
				break;
			case (3):
				texture = new Texture("assets/UI/LevelfourButton.png.png");
				break;
			case (4):
				texture = new Texture("assets/UI/LevelfiveButton.png.png");
				break;
			default:
				texture = new Texture("assets/UI/LeveloneButton.png.png");
				break;
			
		}
		
		buttonX =  screenSize.width/50 + (levelNum * screenSize.width/5);
				
		
		level = new ImageButton(texture, buttonX, buttonY, buttonWidth, buttonHeight);
	}
}
