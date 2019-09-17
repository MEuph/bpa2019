package com.cognitivethought.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.cognitivethought.gui.LoginScreen;
import com.cognitivethought.screens.GameScreen;

public class Main extends Game implements ApplicationListener {

	// The screen used for gameplay
	GameScreen g;
	LoginScreen l;
	@Override
	public void create() {
		g = new GameScreen();
		l= new LoginScreen();
		setScreen(l);
		
	}

	@Override
	public void dispose() {
		l.dispose();
	}

	@Override
	public void render() {
		l.render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void resize(int width, int height) {
		l.resize(width, height);
	}

	@Override
	public void pause() {
		l.pause();
	}

	@Override
	public void resume() {
		l.resume();
	}
}