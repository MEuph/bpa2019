package com.cognitivethought.screens;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cognitivethought.main.Main;
import com.cognitivethought.resources.Resources;


public class TeamsScreen implements Screen{
	
	int timer = 200;
	SpriteBatch batch = new SpriteBatch();
	Texture background = new Texture("Company and Team/ctm_placeholder.jpg"); // background texture
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
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
	public void render(float arg0) {
		timer -= 1;
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0f, 0.1f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		if (timer == 0) {
			background = Resources.AXEL_DEATH;
		} else if (timer <= -200) {
			Main.main.setScreen(Main.main.menuScreen);
		}
		batch.draw(background, 0, 0, screenSize.width, screenSize.height);
		batch.end();
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
