package com.cognitivethought.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.cognitivethought.screens.LevelSelect;
import com.cognitivethought.screens.MenuScreen;

public class LevelButton {

	public ImageButton level;
	private int buttonWidth = 70;
	private int buttonHeight = buttonWidth*197/280;
	private int buttonX = LevelSelect.screenSize.width/2;
	private int buttonY = LevelSelect.screenSize.width/2;

	public void button() {
		Texture cloudTexture = new Texture("assets/UI/cloud.png.png");
		
		level = new ImageButton(new TextureRegionDrawable(new TextureRegion(cloudTexture)));
		level.setSize(buttonWidth, buttonHeight);
		level.setPosition(buttonX, buttonY);
		
		
	//	MenuScreen.stage.addActor(level);
	}
}
