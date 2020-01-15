package com.cognitivethought.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.cognitivethought.gui.ImageButton;
import com.cognitivethought.main.Main;
import com.cognitivethought.sound.Sounds;

public class CompleteScreen implements Screen {

Texture background; //the background texture
	
	ImageButton nextButton;
	
	Animation<TextureRegion> quitAnimation;
	
	SpriteBatch batch; //initializing the spritebatch
	
	public Screen toResetTo;
	
	public CompleteScreen(Screen toRestartTo) {  //main function for the Death screen
		this.toResetTo = toRestartTo;
		
		quitAnimation = new Animation<TextureRegion>(0, new TextureRegion[0]);
		
		background = new Texture("assets/UI/placeholderbackground.png");
		nextButton = new ImageButton(new Texture("assets/UI/Level Select-1.png.png"), new Texture("assets/UI/Level Select-2.png.png"), (1920 / 2) - (250 / 2), 150, 250, 250);
		
		batch = new SpriteBatch();
		nextButton.setClickListener(new ClickListener() { //sets the actions to perform if the buttons are clicked
			@Override
			public void clicked(InputEvent event, float x, float y) {
					Main.main.setScreen(Main.main.levelSelectScreen);
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
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
//		batch.draw(background, 0, 0, 1920, 1920);
		nextButton.render(batch);
		batch.end();
		
		nextButton.checkIfClicked(Gdx.input.getX(), Math.abs(1080-Gdx.input.getY()));
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
		Sounds.intro_music.pause(Sounds.intro_music_id);
	}
	
}