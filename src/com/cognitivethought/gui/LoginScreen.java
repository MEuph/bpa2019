package com.cognitivethought.gui;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.cognitivethought.main.Main;
import com.cognitivethought.screens.GameScreen;

public class LoginScreen implements Screen {
	public static Stage stage;
	private Image background;
	private ImageButton play;
	private ImageButton quit;
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private Image cloud;
	private Image cloud2;
	private Image cloud3;
	private int cloudXPos = -1;
	private Random random = new Random();
	private int cloudYPos = random.nextInt(screenSize.height);
	private int cloudSpeed = 1 + random.nextInt(1);
	private int cloudWidth = 70 + random.nextInt(100);
	private int cloudHeight = cloudWidth*197/280;
	
	public LoginScreen() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		Texture backgroundTexture = new Texture("index.jpg");
		Texture playTexture = new Texture("PlayButton.png");
		Texture quitTexture = new Texture("QuitButton.png");
		background = new Image(new TextureRegionDrawable(new TextureRegion(backgroundTexture)));
		play = new ImageButton(new TextureRegionDrawable(new TextureRegion(playTexture)));
		quit = new ImageButton(new TextureRegionDrawable(new TextureRegion(quitTexture)));
		
		background.setPosition(0, 0);
		background.setSize(screenSize.width, screenSize.height);
		play.setPosition(screenSize.width/2-144, screenSize.height/2-200);
		play.setSize(287, 143);
		quit.setPosition(screenSize.width/2-144, screenSize.height/2-344);
		quit.setSize(287, 143);
		
		stage.addActor(background);
		cloud(1);
		cloud(2);
		cloud(3);
		stage.addActor(play);
		stage.addActor(quit);
		
	}
	
	public void cloud(int x) {
		Texture cloudTexture = new Texture("cloud.png.png");
		if(x == 1) {
		cloud = new Image(new TextureRegionDrawable(new TextureRegion(cloudTexture)));
		cloud.setSize(cloudWidth, cloudHeight);
		}
		if(x == 2) {
			cloud2 = new Image(new TextureRegionDrawable(new TextureRegion(cloudTexture)));
			cloud2.setSize(cloudWidth, cloudHeight);
		}
		if(x == 3) {
			cloud3 = new Image(new TextureRegionDrawable(new TextureRegion(cloudTexture)));
			cloud3.setSize(cloudWidth, cloudHeight);
		}
		
		
	}
	
	public void animateBackground(Image x) {
		if(cloudXPos <= 0) {
			stage.addActor(x);
		}
		cloudXPos += cloudSpeed;
		x.setPosition(cloudXPos, cloudYPos);
		if(cloudXPos >= screenSize.width) {
			cloudWidth = 140 + random.nextInt(50);
			cloudHeight = cloudWidth*197/280;
			
			x.setSize(cloudWidth, cloudHeight);
			cloudYPos = random.nextInt(screenSize.height);
			cloudXPos = 0 - cloudWidth*2;
			cloudSpeed = 1 + random.nextInt(1);
		}
		
		
	}
	
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		stage.dispose();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0f,0.1f,0f,1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		animateBackground(cloud);
		animateBackground(cloud2);
		animateBackground(cloud3);
		
		stage.act(delta);
		stage.draw();
		
		
		if (play.isPressed()) Main.main.setScreen(Main.main.gameScreen);
		if (quit.isPressed()) System.exit(0);
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

}
