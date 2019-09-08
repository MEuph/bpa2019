package com.cognitivethought.main;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cognitivethought.entity.Player;
import com.cognitivethought.level.Level;

public class Main implements ApplicationListener {
	
	SpriteBatch batch;
	
	Player player;
	Level level;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		
		try {
			level = new Level("/testlevel.level");
		} catch (FileNotFoundException | URISyntaxException e) {
			e.printStackTrace();
		}
		
		player = new Player(new Texture("base.png"));
		player.setPosition(0, 0);
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		player.update(level);
		player.render(batch);
		
		level.render(batch);
		
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}
}