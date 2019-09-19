package com.cognitivethought.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Cloud {
	public Image cloud;
	private int cloudXPos = -1;
	private Random random = new Random();
	private int cloudYPos = random.nextInt(LoginScreen.screenSize.height);
	private double cloudSpeed = 1.0 + random.nextDouble()* 2;
	private int cloudWidth = 70 + random.nextInt(100);
	private int cloudHeight = cloudWidth*197/280;
	
	public void cloud() {
		Texture cloudTexture = new Texture("cloud.png.png");
		cloud = new Image(new TextureRegionDrawable(new TextureRegion(cloudTexture)));
		cloud.setSize(cloudWidth, cloudHeight);
		LoginScreen.stage.addActor(cloud);
	}
	
	public void animateBackground(Image x) {
		if(cloudXPos <= 0) {
			LoginScreen.stage.addActor(x);
		}
		cloudXPos += cloudSpeed;
		x.setPosition(cloudXPos, cloudYPos);
		if(cloudXPos >= LoginScreen.screenSize.width) {
			cloudWidth = 140 + random.nextInt(50);
			cloudHeight = cloudWidth*197/280;
			
			x.setSize(cloudWidth, cloudHeight);
			cloudYPos = random.nextInt(LoginScreen.screenSize.height);
			cloudXPos = 0 - cloudWidth*2;
			cloudSpeed = 1 + random.nextInt(1);
		}
		
		
	}
	
		
}
