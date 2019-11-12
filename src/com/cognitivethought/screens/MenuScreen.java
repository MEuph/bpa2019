package com.cognitivethought.screens;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.cognitivethought.gui.Cloud;
import com.cognitivethought.gui.ImageButton;
import com.cognitivethought.main.Main;

//Main title screen

public class MenuScreen implements Screen {
	
	ArrayList<Cloud> clouds = new ArrayList<Cloud>(); //arraylist for clouds
	
	Texture background = new Texture("assets/UI/placeholderbackground.png"); //the background texture
	
	ImageButton playButton = new ImageButton(new Texture("assets/UI/PlayButton.png"), 100, 250); //initializing the buttons
	ImageButton quitButton = new ImageButton(new Texture("assets/UI/QuitButton.png"), 100, 100);
	
	SpriteBatch batch = new SpriteBatch(); //initializing the spritebatch
	
	float y; //
	float fade;
	
	public MenuScreen() {  //main function for the Menu screen
		for (int i = 0; i < new Random().nextInt(20) + 10; i++) { //adds a random amount of clouds to the ArrayList
			clouds.add(new Cloud()); 
		}
		
		playButton.setClickListener(new ClickListener() { //sets the actions to perform if the buttons are clicked
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Main.main.setScreen(Main.main.levelselect);
			}
		});
		
		quitButton.setClickListener(new ClickListener() { //sets the actions to perform if the buttons are clicked
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.exit(0);
			}
		});
		
		for (Cloud c : clouds) { //assigns the clouds with textures and size
			c.cloud();
		}
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
		batch.draw(background, 0, y, 1920, 1920);
		playButton.render(batch);
		quitButton.render(batch);
//		for (Cloud c : clouds) {
//			c.animateBackground(c.cloud);
//			c.cloud.setY((y+c.cloud.getY()) + 1920 / 2 - 100);
//			c.cloud.draw(batch, delta);
//		}
		batch.end();

		
		if (y >= -800) { //scrolls up the screen y value
			y-=8;
		}
		
		fade = 1-((float)y / -800f);
		
		if (fade <= 0f) {
			playButton.checkIfClicked(Gdx.input.getX(), Math.abs(1080-Gdx.input.getY()));
			quitButton.checkIfClicked(Gdx.input.getX(), Math.abs(1080-Gdx.input.getY()));
		}
		// Enable transparency blending
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		// If still fading, then draw the black fade rectangle
		if (fade > 0f) {
			ShapeRenderer sp = new ShapeRenderer();
			sp.begin(ShapeType.Filled);
			sp.setColor(new Color(0, 0, 0, fade));
			sp.rect(0f, 0f, 1920f, 1080f);
			sp.end();
		}
		// Disable transparency blending
		Gdx.gl.glDisable(GL20.GL_BLEND);
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