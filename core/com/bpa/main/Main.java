package com.bpa.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bpa.entity.Player;

public class Main implements ApplicationListener {

	SpriteBatch batch;
	Texture t;
	Sprite s;
	BitmapFont font;
	Player p;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		t = new Texture("badlogic.jpg");
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		p = new Player(t);
		p.setPosition(0, 0);
	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
		t.dispose();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		font.draw(batch, "Test", 20, 20, 200, 0, false);
		p.update();
		p.draw(batch);
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