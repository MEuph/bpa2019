package com.cognitivethought.gui;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Cloud {

	public Sprite cloud;
	private Random random = new Random();
	private int cloudWidth = 70 + random.nextInt(100);
	private int cloudHeight = cloudWidth*197/280;

	//private int cloudYPos = random.nextInt((MenuScreen.screenSize.height-cloudHeight)-(MenuScreen.screenSize.height/2)+1)+(MenuScreen.screenSize.height/2);
	private double cloudSpeed = 1.0 + Math.random() + (double)random.nextInt(2);
	


	private float cloudXPos = -1;

	public void cloud() {
		Texture cloudTexture = new Texture("assets/UI/cloud.png.png");
		
		cloud = new Sprite(cloudTexture);
		cloud.setSize(cloudWidth, cloudHeight);
		
	//	cloudXPos = random.nextInt(MenuScreen.screenSize.width);
	//	MenuScreen.stage.addActor(cloud);
	}
	
	public void animateBackground(Sprite x) {
		if(cloudXPos <= 0) {
		//	MenuScreen.stage.addActor(x);
		}
		//if(cloudXPos >= MenuScreen.screenSize.width) {
			
			cloudSpeed = 1.0 + Math.random() + (double)random.nextInt(2);
			cloudWidth = 140 + random.nextInt(50);
			cloudHeight = cloudWidth*197/280;
			
			x.setSize(cloudWidth, cloudHeight);
		//	cloudYPos = random.nextInt((MenuScreen.screenSize.height-cloudHeight)-(MenuScreen.screenSize.height/2)+1)+(MenuScreen.screenSize.height/2);
			cloudXPos = 0 - cloudWidth*2;
	//	}
		
		cloudXPos += cloudSpeed;
		
		//x.setPosition(cloudXPos, cloudYPos);
		
		
		
		
	}
	
		
}
