package com.cognitivethought.gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.graphics.Texture;
import com.cognitivethought.screens.LevelSelectScreen;
//Object class fot the level buttons
public class LevelButton {
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //gets the screen size
	public ImageButton level; //initializes an image button
	private int buttonWidth = 287; //sets the button width
	private int buttonHeight = buttonWidth*133/287; //sets the button height based on a pixel ratio to the button width
	private int buttonX = 0; //initializes the button's x value
	private int buttonY = 500; //initializes the button's y value

	public void addButton(int levelNum) { //method to set the different textures ad other properties of the button
		Texture texture; //initializes a texture for the button
		switch (levelNum) { //based on the button number in the list it changes the texture of the buttons
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
		
		buttonX =  screenSize.width/50 + (levelNum * screenSize.width/5); //sets the button's x value based on the screen size and which button it is in the sequence
				
		
		level = new ImageButton(texture, buttonX, buttonY, buttonWidth, buttonHeight); //sets level to the image button with all the properties.
	}
}
