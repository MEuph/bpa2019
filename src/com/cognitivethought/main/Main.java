package com.cognitivethought.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.cognitivethought.screens.GameScreen;
import com.cognitivethought.screens.LevelSelect;
import com.cognitivethought.screens.MenuScreen;

public class Main extends Game implements ApplicationListener {
	
	// Used for static access of otherwise non-static items
	public static Main main;
	
	// The screen used for game-play
	public GameScreen gameScreen;
	
	// The screen used for logging in
	public MenuScreen loginScreen;
	
	// The screen used to select levels
	public LevelSelect levelselect;
	
	@Override
	public void create() {
		main = this;
		gameScreen = new GameScreen();
		loginScreen= new MenuScreen();
		levelselect= new LevelSelect();
		setScreen(loginScreen);
		
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