package com.cognitivethought.screens;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
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
import com.cognitivethought.gui.Cloud;
import com.cognitivethought.main.Main;

public class LoginScreen implements Screen {
	public static Stage stage;
	private Image background;
	private Image title;
	private ImageButton play;
	private ImageButton quit;
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	private ArrayList<Cloud> clouds;
	
	public LoginScreen() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		clouds = new ArrayList<>();
		
		for (int i = 0; i < new Random().nextInt(20) + 10; i++) {
			clouds.add(new Cloud());
		}
		
		Texture title = new Texture("assets/UI/placeholdertitle.png");
		Texture backgroundTexture = new Texture("assets/UI/placeholderbackground.png");
		Texture playTexture = new Texture("assets/UI/PlayButton.png");
		Texture quitTexture = new Texture("assets/UI/QuitButton.png");
		this.title = new Image(new TextureRegionDrawable(new TextureRegion(title)));
		background = new Image(new TextureRegionDrawable(new TextureRegion(backgroundTexture)));
		play = new ImageButton(new TextureRegionDrawable(new TextureRegion(playTexture)));
		quit = new ImageButton(new TextureRegionDrawable(new TextureRegion(quitTexture)));
		
		this.title.setPosition(screenSize.width/2-this.title.getWidth()/2, screenSize.height-150);
		background.setPosition(0, 0);
		background.setSize(screenSize.width, screenSize.height);
		play.setPosition(screenSize.width/2-144, screenSize.height/2-200);
		play.setSize(287, 143);
		quit.setPosition(screenSize.width/2-144, screenSize.height/2-344);
		quit.setSize(287, 143);
		
		
		stage.addActor(background);
		for(Cloud c : clouds) {
			c.cloud();
		}
		stage.addActor(play);
		stage.addActor(quit);
		stage.addActor(this.title);
		
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

		for (Cloud c : clouds) {
			c.animateBackground(c.cloud);
		}
		
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
