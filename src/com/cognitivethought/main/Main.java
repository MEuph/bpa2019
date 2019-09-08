package com.cognitivethought.main;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.cognitivethought.entity.Player;
import com.cognitivethought.level.Level;

public class Main implements ApplicationListener {
	
	SpriteBatch batch;
	
	Player player;
	Level level;
	
	BitmapFont font;
	
	OrthographicCamera c;
	
	final float smoothCamera = .1f;
	
	String fps = "FPS:";
	float timer = 0;
	
	@Override
	public void create() {
		
		batch = new SpriteBatch();
		
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		
		try {
			level = new Level("/testlevel.level");
		} catch (FileNotFoundException | URISyntaxException e) {
			e.printStackTrace();
		}
		
		c = new OrthographicCamera();
		c.setToOrtho(false, 1280, 720);
		c.position.set(0f,0f,0f);
		
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
		
		Vector3 position = c.position;
		position.x += (player.getX() - position.x) * smoothCamera;
		position.y += (player.getY() - position.y) * smoothCamera;
		c.update();
		
		batch.setProjectionMatrix(c.combined);
		batch.begin();
		
		
		level.render(batch);
		
		player.update(level);
		player.render(batch);
		
		timer += Gdx.graphics.getDeltaTime();
		
		if(timer > .5f) {
			timer = 0f;
			fps = "FPS: " + (int)(1 / Gdx.graphics.getDeltaTime());
		}
		
		font.draw(batch, fps, c.position.x - (c.viewportWidth / 2), c.position.y - (c.viewportHeight / 2) + 20f);
		
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