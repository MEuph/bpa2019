package com.cognitivethought.screens;

import java.io.IOException;

import javax.imageio.ImageIO;

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
import com.cognitivethought.level.Level;
import com.cognitivethought.main.Main;

public class CompleteScreen implements Screen {

Texture background; //the background texture
	
	ImageButton retryButton; //initializing the buttons
	ImageButton nextButton;
	
	Animation<TextureRegion> retryAnimation;
	Animation<TextureRegion> quitAnimation;
	
	SpriteBatch batch; //initializing the spritebatch
	
	public Screen toResetTo;
	
	public CompleteScreen(Screen toRestartTo) {  //main function for the Death screen
		this.toResetTo = toRestartTo;
		
		retryAnimation = new Animation<TextureRegion>(0, new TextureRegion[0]);
		quitAnimation = new Animation<TextureRegion>(0, new TextureRegion[0]);
		
		background = new Texture("assets/UI/placeholderbackground.png");
		retryButton = new ImageButton(new Texture("assets/UI/Retry Button-1.png.png"), new Texture("assets/UI/Retry Button-2.png.png"), (1920 / 2) - (250 / 2), 500, 250, 250);
		nextButton = new ImageButton(new Texture("assets/UI/Level Select-1.png.png"), new Texture("assets/UI/Level Select-2.png.png"), (1920 / 2) - (250 / 2), 150, 250, 250);
		
		batch = new SpriteBatch();
		
		retryButton.setClickListener(new ClickListener() { //sets the actions to perform if the buttons are clicked
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					if (LevelSelectScreen.levelNumber == 1) {
						Main.main.gameScreen.level = new Level(ImageIO.read(GameScreen.class.getResourceAsStream("/Levels/Development Level/level1.png")), Main.main.gameScreen);
						Main.main.setScreen(toResetTo);
						}
					if (LevelSelectScreen.levelNumber == 2) {
							Main.main.gameScreen.level = new Level(ImageIO.read(GameScreen.class.getResourceAsStream("/Levels/Development Level/level2.png")), Main.main.gameScreen);
							Main.main.setScreen(toResetTo);
						}
					if (LevelSelectScreen.levelNumber == 3) {
							Main.main.gameScreen.level = new Level(ImageIO.read(GameScreen.class.getResourceAsStream("/Levels/Development Level/level3.png")), Main.main.gameScreen);
							Main.main.setScreen(toResetTo);
						}
					if (LevelSelectScreen.levelNumber == 4) {
							Main.main.gameScreen.level = new Level(ImageIO.read(GameScreen.class.getResourceAsStream("/Levels/Development Level/tutoriallevel.png")), Main.main.gameScreen);
							Main.main.setScreen(toResetTo);
						}
					if (LevelSelectScreen.levelNumber == 5) {
							Main.main.gameScreen.level = new Level(ImageIO.read(GameScreen.class.getResourceAsStream("/Levels/Development Level/tutoriallevel.png")), Main.main.gameScreen);
							Main.main.setScreen(toResetTo);
						}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
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
		retryButton.render(batch);
		nextButton.render(batch);
		batch.end();
		
		retryButton.checkIfClicked(Gdx.input.getX(), Math.abs(1080-Gdx.input.getY()));
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
		// TODO Auto-generated method stub
		
	}
	
}