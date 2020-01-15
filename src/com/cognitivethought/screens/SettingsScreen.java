package com.cognitivethought.screens;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.cognitivethought.gui.ImageButton;
import com.cognitivethought.main.Main;
import com.cognitivethought.sound.Sounds;

//Main title screen

public class SettingsScreen implements Screen {
	
	ImageButton decMusic = new ImageButton(new Texture("assets/UI/Buttons/left.png"), (1920 / 2) - 300, (1080 / 2) + 200); //initializing the buttons
	ImageButton incMusic = new ImageButton(new Texture("assets/UI/Buttons/right.png"), (1920 / 2) + 400, (1080 / 2) + 200);
	ImageButton decSounds = new ImageButton(new Texture("assets/UI/Buttons/left.png"), (1920 / 2) - 300, (1080 / 2) - 200); //initializing the buttons
	ImageButton incSounds = new ImageButton(new Texture("assets/UI/Buttons/right.png"), (1920 / 2) + 400, (1080 / 2) - 200);
	
	ImageButton save = new ImageButton(new Texture("assets/UI/Buttons/save.png"), 300, 100);
	ImageButton back = new ImageButton(new Texture("assets/UI/Buttons/back.png"), 100, 100);
	
	Sprite background = new Sprite(new Texture("assets/Background/bark.png"));
	
	BitmapFont font;
	BitmapFont bigFont;
	
	SpriteBatch batch = new SpriteBatch(); //initializing the spritebatch
	
	OrthographicCamera c;
	
	public static Screen toResetTo;
	
	public static int VOL_MUSIC;
	public static int VOL_SOUNDS;
	
	public static boolean changesApplied = false;
	
	public SettingsScreen() {  //main function for the Menu screen
		Texture t = new Texture("assets/cursor.png");
		TextureData td = t.getTextureData();
		if (!td.isPrepared()) td.prepare();
		Cursor customCursor = Gdx.graphics.newCursor(td.consumePixmap(), 0, 0);
		Gdx.graphics.setCursor(customCursor);
		
		load();
		
		c = new OrthographicCamera(1920, 1080);
		c.setToOrtho(false);
		
		background.setPosition(0, 0);
		background.setSize(2500, 2500);
		
		decMusic.getSkin().setSize(200, 200);
		incMusic.getSkin().setSize(200, 200);
		decSounds.getSkin().setSize(200, 200);
		incSounds.getSkin().setSize(200, 200);
		
		save.getSkin().setSize(100, 100);
		back.getSkin().setSize(100, 100);
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(new FileHandle("assets/Fonts/times-new-roman.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 40;
		font = generator.generateFont(parameter);
		parameter.size = 80;
		bigFont = generator.generateFont(parameter);
		
		incMusic.setClickListener(new ClickListener() { //sets the actions to perform if the buttons are clicked
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println(x + ", " + y);
				if (VOL_MUSIC < 10) {
					VOL_MUSIC++;
					changesApplied = false;
				}
			}
		});
		
		decMusic.setClickListener(new ClickListener() { //sets the actions to perform if the buttons are clicked
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println(x + ", " + y);
				if (VOL_MUSIC > 0) {
					VOL_MUSIC--;
					changesApplied = false;
				}
			}
		});
		
		incSounds.setClickListener(new ClickListener() { //sets the actions to perform if the buttons are clicked
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println(x + ", " + y);
				if (VOL_SOUNDS < 10) {
					VOL_SOUNDS++;
					changesApplied = false;
				}
			}
		});
		
		decSounds.setClickListener(new ClickListener() { //sets the actions to perform if the buttons are clicked
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println(x + ", " + y);
				if (VOL_SOUNDS > 0) {
					VOL_SOUNDS--;
					changesApplied = false;
				}
			}
		});
		
		save.setClickListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				save();
			}
		});
		
		back.setClickListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (toResetTo == Main.main.gameScreen) {
					Main.main.gameScreen.shouldReset = false;
				}
				Main.main.setScreen(toResetTo);
			}
		});
	}
	
	public static void save() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("assets/settings.txt"));
			writer.write(VOL_MUSIC + ";" + VOL_SOUNDS);
			writer.close();
			changesApplied = true;
			System.out.println("test");
			Sounds.intro_music.setVolume(Sounds.intro_music_id, (float)VOL_MUSIC / 10f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void load() {
		try {
			Scanner sc = new Scanner(new File("assets/settings.txt"));
			String[] splitData = sc.nextLine().split(";");
			VOL_MUSIC = Integer.parseInt(splitData[0]);
			VOL_SOUNDS = Integer.parseInt(splitData[1]);
			sc.close();
			changesApplied = true;
			Sounds.intro_music.setVolume(Sounds.intro_music_id, (float)VOL_MUSIC / 10f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) { //what to do when the screen needs to be rendered
		Gdx.gl.glClearColor(0f, 0.1f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(c.combined);
		
		batch.begin();
		
		background.draw(batch);
		
		incMusic.render(batch);
		decMusic.render(batch);
		incSounds.render(batch);
		decSounds.render(batch);
		
		save.render(batch);
		back.render(batch);
		
		font.draw(batch, "Music Volume: ", (1920 / 2) - 700, (1080 / 2) + 300);
		font.draw(batch, "Sounds Volume: ", (1920 / 2) - 700, (1080 / 2) - 100);
		if (changesApplied) font.draw(batch, "Changes Applied", (1920 / 2), 200);
		
		bigFont.draw(batch, Integer.toString(VOL_MUSIC), (1920 / 2) + 150, (1080 / 2) + 300);
		bigFont.draw(batch, Integer.toString(VOL_SOUNDS), (1920 / 2) + 150, (1080 / 2) - 100);
		
		batch.end();
		
		if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
			System.out.println(Gdx.input.getX() + ", " + (1080 - Math.abs(Gdx.input.getY())));
			System.out.println(incMusic.getSkin().getX() + ", " + incMusic.getSkin().getY());
			System.out.println();
		}
		
		incMusic.checkIfClicked(Gdx.input.getX(), 1080 -  Math.abs(Gdx.input.getY()));
		decMusic.checkIfClicked(Gdx.input.getX(), 1080 - Math.abs(Gdx.input.getY()));
		incSounds.checkIfClicked(Gdx.input.getX(), 1080 - Math.abs(Gdx.input.getY()));
		decSounds.checkIfClicked(Gdx.input.getX(), 1080 - Math.abs(Gdx.input.getY()));
		
		save.checkIfClicked(Gdx.input.getX(), 1080 - Math.abs(Gdx.input.getY()));
		back.checkIfClicked(Gdx.input.getX(), 1080 - Math.abs(Gdx.input.getY()));
	}

	@Override
	public void resize(int arg0, int arg1) {
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		
	}
	
}