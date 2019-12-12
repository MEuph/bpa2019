package com.cognitivethought.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.cognitivethought.resources.Resources;
import com.cognitivethought.screens.DeathScreen;
import com.cognitivethought.screens.GameScreen;
import com.cognitivethought.screens.LevelSelectScreen;
import com.cognitivethought.screens.MenuScreen;

public class Main extends Game implements ApplicationListener {
	
	// Used for static access of otherwise non-static items
	public static Main main;
	
	// The screen used for game-play
	public GameScreen gameScreen;
	
	// The screen used for logging in
	public MenuScreen menuScreen;
	
	// The screen used to select levels
	public LevelSelectScreen levelSelectScreen;
	
	public DeathScreen deathScreen;
	
	@Override
	public void create() {
		Resources.loadTextures();

		main = this;
		gameScreen 			= new GameScreen();
		menuScreen			= new MenuScreen();
		levelSelectScreen	= new LevelSelectScreen();
		deathScreen 		= new DeathScreen(null);
		setScreen(menuScreen);
	}

	@Override
	public void dispose() {
		main.getScreen().dispose();
	}

	@Override
	public void render() {
		main.getScreen().render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void resize(int width, int height) {
		main.getScreen().resize(width, height);
	}

	@Override
	public void pause() {
		main.getScreen().pause();
	}

	@Override
	public void resume() {
		main.getScreen().resume();
	}
}