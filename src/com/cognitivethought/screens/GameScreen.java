package com.cognitivethought.screens;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.cognitivethought.entity.Player;
import com.cognitivethought.level.Level;

public class GameScreen implements Screen {
	
	SpriteBatch batch;
	
	Player player;
	Level level;
	
	BitmapFont font;
	
	OrthographicCamera c;
	
	final float smoothCamera = .1f;
	
	String fps = "FPS:";
	float timer = 0;
	
	float fade = 1f;
	
	@Override
	public void show() {
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
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				c.position.set(new Vector3(c.position.x, c.position.y+100, c.position.z));
				while (fade > 0f) {
					fade -= .01f;
					System.out.println(fade);
					c.translate(0,-1,0);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	public GameScreen() {
		
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
	public void render(float arg0) {
		Gdx.gl.glClearColor(95f/255f,205f/255f,228f/255f,1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		Vector3 position = c.position;
		
		if (!(fade > 0)) {
			position.x += (player.getX() - position.x) * smoothCamera;
			position.y += (player.getY() - position.y) * smoothCamera;
		}
		c.update();
		
		batch.setProjectionMatrix(c.combined);

		batch.begin();
		
		level.render(batch);
		
		if (!(fade > 0)) {
			player.update(level);
		}
		player.render(batch);
		
		timer += Gdx.graphics.getDeltaTime();
		
		if(timer > .5f) {
			timer = 0f;
			fps = "FPS: " + (int)(1 / Gdx.graphics.getDeltaTime());
		}
		
		font.draw(batch, fps, c.position.x - (c.viewportWidth / 2), c.position.y - (c.viewportHeight / 2) + 20f);
		
		batch.end();
		Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		if (fade > 0f) {
			ShapeRenderer sp = new ShapeRenderer();
			sp.setProjectionMatrix(c.combined);
			sp.begin(ShapeType.Filled);
			sp.setColor(new Color(0,0,0,fade));
			sp.rect(c.position.x - (c.viewportWidth / 2), c.position.y - (c.viewportHeight / 2), 1300, 800);
			sp.end();
		}
		Gdx.gl.glDisable(GL20.GL_BLEND);
	}

	@Override
	public void resize(int arg0, int arg1) {
		
	}

	@Override
	public void resume() {
		
	}
}