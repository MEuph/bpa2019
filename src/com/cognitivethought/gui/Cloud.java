package com.cognitivethought.gui;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.cognitivethought.screens.LoginScreen;

public class Cloud {

	public Image cloud;
	private Random random = new Random();
	private int cloudWidth = 70 + random.nextInt(100);
	private int cloudHeight = cloudWidth*197/280;

	private int cloudYPos = random.nextInt((LoginScreen.screenSize.height-cloudHeight)-(LoginScreen.screenSize.height/2)+1)+(LoginScreen.screenSize.height/2);
	private double cloudSpeed = 1 + random.nextInt(3);
	

	private int cloudXPos = -1;
	

	
	public void cloud() {
		Texture cloudTexture = new Texture("cloud.png.png");
		
		cloud = new Image(new TextureRegionDrawable(new TextureRegion(cloudTexture)));
		cloud.setSize(cloudWidth, cloudHeight);
		
		cloudXPos = random.nextInt(LoginScreen.screenSize.width);
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

			cloudYPos = random.nextInt((LoginScreen.screenSize.height-cloudHeight)-(LoginScreen.screenSize.height/2)+1)+(LoginScreen.screenSize.height/2);
			cloudXPos = 0 - cloudWidth*2;
			cloudSpeed =  1 + random.nextInt(3);
		}
		
		
	}
	
		
}
