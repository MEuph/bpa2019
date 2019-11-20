package com.cognitivethought.screens;

import java.io.IOException;

import javax.imageio.ImageIO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.cognitivethought.inventory.Inventory;
import com.cognitivethought.level.Level;
import com.cognitivethought.main.Main;
import com.cognitivethought.ui.HealthBar;

public class InventoryScreen implements Screen{

	SpriteBatch batch; // The batch renderer that helps to render sprites faster than usual
	Inventory inv;
	
	HealthBar hb = new HealthBar();
	
	float smoothCamera = .1f; // How much to smooth the camera's movement by
	float timer = 0; // Timer for updating FPS counter
	float fade = 1f; // Timer/Opacity for screen fade-in



	public InventoryScreen() {
		batch = new SpriteBatch();

		
		try {
			inv = new Inventory(ImageIO.read(GameScreen.class.getResourceAsStream("/Tilesets/Development Tileset/inventory.png.png")), this); // Initialize level with 'testlevel.level'
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}


	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {

	}

	@Override
	public void render(float deltaTime) {
		Gdx.gl.glClearColor(0f,0.1f,0f,1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		batch.begin();
		inv.render(batch, hb);
		batch.end();

		// Enable transparency blending
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		// If still fading, then draw the black fade rectangle
		
		// Disable transparency blending
		Gdx.gl.glDisable(GL20.GL_BLEND);
		

		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) { 
			Main.main.setScreen(Main.main.levelSelectScreen);
		}
	}

	@Override
	public void resize(int w, int h) {
		// c.setToOrtho(false, w, h);
	}

	@Override
	public void resume() {

	}


	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

}
