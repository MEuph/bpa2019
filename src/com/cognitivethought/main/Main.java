package com.cognitivethought.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.cognitivethought.screens.GameScreen;

public class Main extends Game implements ApplicationListener {

	// The screen used for gameplay
	GameScreen g;

	@Override
	public void create() {
		g = new GameScreen();
		setScreen(g);
	}

	@Override
	public void dispose() {
		g.dispose();
	}

	@Override
	public void render() {
		g.render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void resize(int width, int height) {
		g.resize(width, height);
	}

	@Override
	public void pause() {
		g.pause();
	}

	@Override
	public void resume() {
		g.resume();
	}
}