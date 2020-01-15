package com.cognitivethought.screens;

import java.io.IOException;

import javax.imageio.ImageIO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.cognitivethought.gui.ImageButton;
import com.cognitivethought.gui.LevelButton;
import com.cognitivethought.level.Level;
import com.cognitivethought.main.Main;
import com.cognitivethought.main.desktop.DesktopLauncher;
import com.cognitivethought.sound.Sounds;

//screen to select the level
public class LevelSelectScreen implements Screen {
	OrthographicCamera c;

	LevelButton[] levels = new LevelButton[5]; // list that stores the level buttons
	public static int levelNumber = 0;
	Texture background = new Texture("assets/UI/placeholderbackground.png"); // background texture
	ImageButton quitButton = new ImageButton(new Texture("assets/UI/backbutton.png"), 100, 100);

	SpriteBatch batch = new SpriteBatch(); // spritebatch initialization

	float y;
	float fade;

	public LevelSelectScreen() { // main method of the level selection

		c = new OrthographicCamera();
		c.setToOrtho(false, 1920, 1080); // Create camera, and set size to window size
		
		for (int i = 0; i < levels.length; i++) { // adds buttons to the list and sets the properties of each
			levels[i] = new LevelButton();
			levels[i].addButton(i);
			switch (i) { // switch to determine which level the button takes you to
			case 0:
				levels[i].level.setClickListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) { // assigns the action that happens when
																				// the button is clicked for level 1
						try {
							levelNumber = 1;
							Main.main.gameScreen.shouldReset = true;
							Main.main.cutsceneScreen.currentCutscene = CutsceneScreen.LEVEL_1;
							Main.main.gameScreen.level = new Level(
									ImageIO.read(GameScreen.class
											.getResourceAsStream("/Levels/Development Level/level1.png")),
									Main.main.gameScreen);
							Main.main.setScreen(Main.main.cutsceneScreen);
							Sounds.intro_music.stop();
						} catch (Exception e) {
							DesktopLauncher.log();
							e.printStackTrace();
						}
					}
				});
				break;
			case 1:
				levels[i].level.setClickListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) { // assigns the action that happens when
																				// the button is clicked for level 2

						if (Main.levelsPassed >= 1) {
							try {
								levelNumber = 2;
								Main.main.gameScreen.shouldReset = true;
								Main.main.gameScreen.level = new Level(
										ImageIO.read(GameScreen.class
												.getResourceAsStream("/Levels/Development Level/level2.png")),
										Main.main.gameScreen);
								Main.main.cutsceneScreen.currentCutscene = CutsceneScreen.SEQUOIA;
								Main.main.setScreen(Main.main.cutsceneScreen);
								Sounds.intro_music.stop();
							} catch (IOException e) {
								DesktopLauncher.log();
								e.printStackTrace();
							}
						}
					}
				});
				break;
			case 2:
				levels[i].level.setClickListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) { // assigns the action that happens when
																				// the button is clicked for level 3
						if (Main.levelsPassed >= 2) {
							try {
								levelNumber = 3;
								Main.main.gameScreen.level = new Level(
										ImageIO.read(GameScreen.class
												.getResourceAsStream("/Levels/Development Level/level3.png")),
										Main.main.gameScreen);
								Main.main.setScreen(Main.main.gameScreen);
								Main.main.gameScreen.shouldReset = true;
								Sounds.intro_music.stop();
							} catch (IOException e) {
								DesktopLauncher.log();
								e.printStackTrace();
							}
						}
					}
				});
				break;
			case 3:
				levels[i].level.setClickListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) { // assigns the action that happens when
																				// the button is clicked for level 4
						if (Main.levelsPassed >= 3) {
							try {
								levelNumber = 4;
								Main.main.gameScreen.shouldReset = true;
								Main.main.gameScreen.level = new Level(
										ImageIO.read(GameScreen.class
												.getResourceAsStream("/Levels/Development Level/level4.png")),
										Main.main.gameScreen);
								Main.main.cutsceneScreen.currentCutscene = CutsceneScreen.ROAD;
								Main.main.setScreen(Main.main.cutsceneScreen);
								Sounds.intro_music.stop();
							} catch (IOException e) {
								DesktopLauncher.log();
								e.printStackTrace();
							}
						}
					}
				});
				break;
			case 4:
				levels[i].level.setClickListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) { // assigns the action that happens when
																				// the button is clicked for level 5
						if (Main.levelsPassed >= 4) {
							try {
								levelNumber = 5;
								Main.main.gameScreen.shouldReset = true;
								Main.main.gameScreen.level = new Level(
										ImageIO.read(GameScreen.class
												.getResourceAsStream("/Levels/Development Level/level5.png")),
										Main.main.gameScreen);
								Main.main.setScreen(Main.main.gameScreen);
								Sounds.intro_music.stop();
							} catch (IOException e) {
								DesktopLauncher.log();
								e.printStackTrace();
							}
						}
					}
				});
				break;
			default:
				levels[i].level.setClickListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) { // assigns the action that happens when
																				// the button is clicked as a default
						System.exit(0);
					}
				});
				break;
			}
			quitButton.setClickListener(new ClickListener() { // sets the actions to perform if the buttons are clicked
				@Override
				public void clicked(InputEvent event, float x, float y) {
					Main.main.setScreen(Main.main.menuScreen);
				}
			});

		}
		
		quitButton.getSkin().setSize(levels[0].level.getSkin().getWidth(), levels[0].level.getSkin().getHeight());
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
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0.1f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.setProjectionMatrix(c.combined);
		c.update();

		batch.draw(background, 0, -805, 1920, 1920);

		for (int i = 0; i < levels.length; i++) {
			levels[i].level.render(batch);
		}
		quitButton.render(batch);

		batch.end();
		if (fade <= 0f) {
			for (int i = 0; i < levels.length; i++) {
				levels[i].level.checkIfClicked(Gdx.input.getX(), Math.abs(1080 - Gdx.input.getY())); // checks if the
																										// button has
																										// been clicked
																										// per frame
				quitButton.checkIfClicked(Gdx.input.getX(), Math.abs(1080 - Gdx.input.getY()));
			}
		}

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
		for (int i = 0; i < levels.length; i++) {
			levels[i].updateTexture(i);
		}
	}

}