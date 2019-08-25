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

public class Main implements ApplicationListener {

	SpriteBatch batch;
	Texture t;
	Sprite s;
	BitmapFont font;

	@Override
	public void create() {
		batch = new SpriteBatch();
		t = new Texture("badlogic.jpg");
		s = new Sprite(t);
		s.setPosition(0, 0);
		font = new BitmapFont();
		font.setColor(Color.WHITE);
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
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			s.translateX(-1f);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			s.translateX(1f);
		}
		batch.begin();
		font.draw(batch, "Test", 20, 20, 200, 0, false);
		s.draw(batch);
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