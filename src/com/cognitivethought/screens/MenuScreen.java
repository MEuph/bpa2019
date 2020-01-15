package com.cognitivethought.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.cognitivethought.gui.ImageButton;
import com.cognitivethought.main.Main;
import com.cognitivethought.resources.Resources;
import com.cognitivethought.sound.Sounds;

//Main title screen

public class MenuScreen implements Screen {
	
	OrthographicCamera c;
	
	ImageButton playButton = new ImageButton(new Texture("assets/UI/PlayButton.png"), 100, 550);
	ImageButton optnButton = new ImageButton(new Texture("assets/UI/optionsbutton.png"), 100, 400);
	ImageButton credsButton = new ImageButton(new Texture("assets/UI/CreditsButton.png"), 100, 250);
	ImageButton quitButton = new ImageButton(new Texture("assets/UI/QuitButton.png"), 100, 100);
	
	SpriteBatch batch = new SpriteBatch(); //initializing the spritebatch
	
	float y;
	float fade;
	
	float credY = -6752;
	
	boolean showCredits = false;
	
	Sprite credits = new Sprite(new Texture("Cutscenes/credits.png"));
	
	public MenuScreen() {  //main function for the Menu screen
		c = new OrthographicCamera();
		c.setToOrtho(false, 1920, 1080); // Create camera, and set size to window size
		
		Sounds.intro_music_id = Sounds.intro_music.play(SettingsScreen.VOL_MUSIC);
		
		Texture t = new Texture("assets/cursor.png");
		TextureData td = t.getTextureData();
		if (!td.isPrepared()) td.prepare();
		Cursor customCursor = Gdx.graphics.newCursor(td.consumePixmap(), 0, 0);
		Gdx.graphics.setCursor(customCursor);
		
		playButton.setClickListener(new ClickListener() { //sets the actions to perform if the buttons are clicked
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Main.main.setScreen(Main.main.levelSelectScreen);
			}
		});
		
		quitButton.setClickListener(new ClickListener() { //sets the actions to perform if the buttons are clicked
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.exit(0);
			}
		});
		
		optnButton.setClickListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				SettingsScreen.toResetTo = Main.main.menuScreen;
				Main.main.setScreen(Main.main.settingsScreen);
			}
		});
		
		credsButton.setClickListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				credY = -6752;
				showCredits = true;
			}
		});
		
		credits.setSize(1920, 6752);
		
		optnButton.getSkin().setSize(playButton.getSkin().getWidth(), playButton.getSkin().getHeight());
		credsButton.getSkin().setSize(playButton.getSkin().getWidth(), playButton.getSkin().getHeight());
	}
	
	@Override
	public void dispose() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void render(float delta) { //what to do when the screen needs to be rendered
		Gdx.gl.glClearColor(0f, 0.1f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		batch.setProjectionMatrix(c.combined);
		c.update();
		
		batch.draw(Resources.UI_BACKGROUND, 0, y, 1920, 1920);
		batch.draw(Resources.TITLE, 900, y + 1250, 800, 800);
		playButton.render(batch);
		optnButton.render(batch);
		quitButton.render(batch);
		credsButton.render(batch);
		
		batch.end();
		
		if (y >= -800) { //scrolls up the screen y value
			y-=2;
		}
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			fade = 0;
			y = -800;
		}
		
		fade = 1-((float)y / -800f);
		
		if (fade <= 0f && !showCredits) {
			playButton.checkIfClicked(Gdx.input.getX(), Math.abs(1080-Gdx.input.getY()));
			optnButton.checkIfClicked(Gdx.input.getX(), Math.abs(1080-Gdx.input.getY()));
			quitButton.checkIfClicked(Gdx.input.getX(), Math.abs(1080-Gdx.input.getY()));
			credsButton.checkIfClicked(Gdx.input.getX(), Math.abs(1080-Gdx.input.getY()));
		}
		
		// Enable transparency blending
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		// If still fading, then draw the black fade rectangle
		if (fade > 0f) {
			ShapeRenderer sp = new ShapeRenderer();
			sp.begin(ShapeType.Filled);
			sp.setColor(new Color(0, 0, 0, fade));
			sp.rect(0f, 0f, 1920f, 1080f);
			sp.end();
		}
		// Disable transparency blending
		Gdx.gl.glDisable(GL20.GL_BLEND);

		if (showCredits) {
			ShapeRenderer sp = new ShapeRenderer();
			sp.begin(ShapeType.Filled);
			sp.setColor(new Color(0, 0, 0, 1));
			sp.rect(0f, 0f, 1920f, 1080f);
			sp.end();
			batch.begin();
			credits.draw(batch);
			credits.setY(credY);
			credY += 5;
			if (credits.getY() >= 1800) {
				showCredits = false;
			}
			batch.end();
		}
	}

	@Override
	public void resize(int arg0, int arg1) {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void show() {
		Sounds.intro_music.resume(Sounds.intro_music_id);
	}
	
}