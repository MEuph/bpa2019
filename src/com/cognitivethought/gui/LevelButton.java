package com.cognitivethought.gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.graphics.Texture;
import com.cognitivethought.main.Main;
import com.cognitivethought.resources.Resources;

//Object class fot the level buttons
public class LevelButton {

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // gets the screen size

	public ImageButton level; // initializes an image button

	private int buttonWidth = 287; // sets the button width
	private int buttonHeight = buttonWidth * 133 / 287; // sets the button height based on a pixel ratio to the button
														// width
	private int buttonX = 0; // initializes the button's x value
	private int buttonY = 500; // initializes the button's y value

	private int levelNum = 0;

	public void addButton(int levelNum) { // method to set the different textures ad other properties of the button
		Texture texture;

		this.levelNum = levelNum;

		switch (levelNum) { // based on the button number in the list it changes the texture of the buttons
		case (0):
			texture = Main.levelsPassed >= this.levelNum ? Resources.LVL1_POS : Resources.LVL1_NEG;
			break;
		case (1):
			texture = Main.levelsPassed >= this.levelNum ? Resources.LVL2_POS : Resources.LVL2_NEG;
			break;
		case (2):
			texture = Main.levelsPassed >= this.levelNum ? Resources.LVL3_POS : Resources.LVL3_NEG;
			break;
		case (3):
			texture = Main.levelsPassed >= this.levelNum ? Resources.LVL4_POS : Resources.LVL4_NEG;
			break;
		case (4):
			texture = Main.levelsPassed >= this.levelNum ? Resources.LVL5_POS : Resources.LVL5_NEG;
			break;
		default:
			texture = Main.levelsPassed >= this.levelNum ? Resources.LVL1_POS : Resources.LVL1_NEG;
			break;
		}

		buttonX = screenSize.width / 50 + (levelNum * screenSize.width / 5); // sets the button's x value based on the
																				// screen size and which button it is in
																				// the sequence

		level = new ImageButton(texture, buttonX, buttonY, buttonWidth, buttonHeight); // sets level to the image button
																						// with all the properties.
	}

	public void updateTexture(int levelNum) {
		Texture texture;

		this.levelNum = levelNum;

		switch (levelNum) { // based on the button number in the list it changes the texture of the buttons
		case (0):
			texture = Main.levelsPassed >= this.levelNum ? Resources.LVL1_POS : Resources.LVL1_NEG;
			break;
		case (1):
			texture = Main.levelsPassed >= this.levelNum ? Resources.LVL2_POS : Resources.LVL2_NEG;
			break;
		case (2):
			texture = Main.levelsPassed >= this.levelNum ? Resources.LVL3_POS : Resources.LVL3_NEG;
			break;
		case (3):
			texture = Main.levelsPassed >= this.levelNum ? Resources.LVL4_POS : Resources.LVL4_NEG;
			break;
		case (4):
			texture = Main.levelsPassed >= this.levelNum ? Resources.LVL5_POS : Resources.LVL5_NEG;
			break;
		default:
			texture = Main.levelsPassed >= this.levelNum ? Resources.LVL1_POS : Resources.LVL1_NEG;
			break;
		}

		buttonX = screenSize.width / 50 + (levelNum * screenSize.width / 5); // sets the button's x value based on the
																				// screen size and which button it is in
																				// the sequence

		level.getSkin().setTexture(texture);
	}
}
