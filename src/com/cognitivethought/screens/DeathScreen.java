package com.cognitivethought.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.cognitivethought.gui.ImageButton;
import com.cognitivethought.main.Main;

//Main title screen

public class DeathScreen implements Screen {
	
	Texture background; //the background texture
	
	ImageButton retryButton; //initializing the buttons
	ImageButton quitButton;
	
	SpriteBatch batch; //initializing the spritebatch
	
	public Screen toResetTo;
	
	public DeathScreen(Screen toRestartTo) {  //main function for the Death screen
		this.toResetTo = toRestartTo;
		
		background = new Texture("assets/UI/placeholderbackground.png");
		retryButton = new ImageButton(new Texture("assets/UI/PlayButton.png"), 100, 250);
		quitButton = new ImageButton(new Texture("assets/UI/QuitButton.png"), 100, 100);
		
		batch = new SpriteBatch();
		
		retryButton.setClickListener(new ClickListener() { //sets the actions to perform if the buttons are clicked
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Main.main.setScreen(toResetTo);
			}
		});
		
		quitButton.setClickListener(new ClickListener() { //sets the actions to perform if the buttons are clicked
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.exit(0);
			}
		});
		
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
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
	public void render(float delta) { //what to do when the screen needs to be rendered
		Gdx.gl.glClearColor(0f, 0.1f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		batch.draw(background, 0, 0, 1920, 1920);
		retryButton.render(batch);
		quitButton.render(batch);
		batch.end();
		
		retryButton.checkIfClicked(Gdx.input.getX(), Math.abs(1080-Gdx.input.getY()));
		quitButton.checkIfClicked(Gdx.input.getX(), Math.abs(1080-Gdx.input.getY()));
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