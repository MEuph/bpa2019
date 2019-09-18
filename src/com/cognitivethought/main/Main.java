package com.cognitivethought.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.cognitivethought.gui.LoginScreen;
import com.cognitivethought.screens.GameScreen;

public class Main extends Game implements ApplicationListener {
	
	// Used for static access of otherwise non-static items
	public static Main main;
	
	// The screen used for game-play
	public GameScreen gameScreen;
	
	// The screen used for logging in
	public LoginScreen loginScreen;
	
	@Override
	public void create() {
		Main.main = this;
		gameScreen = new GameScreen();
		loginScreen= new LoginScreen();
		main.setScreen(loginScreen);
		
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