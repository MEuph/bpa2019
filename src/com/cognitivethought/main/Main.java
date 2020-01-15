package com.cognitivethought.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.cognitivethought.main.desktop.DesktopLauncher;
import com.cognitivethought.resources.Resources;
import com.cognitivethought.resources.Strings;
import com.cognitivethought.screens.CompleteScreen;
import com.cognitivethought.screens.CutsceneScreen;
import com.cognitivethought.screens.DeathScreen;
import com.cognitivethought.screens.GameScreen;
import com.cognitivethought.screens.LevelSelectScreen;
import com.cognitivethought.screens.MenuScreen;
import com.cognitivethought.screens.SettingsScreen;
import com.cognitivethought.screens.TeamsScreen;
import com.cognitivethought.sound.Sounds;

/**
 * The class where the main game loop is
 */
public class Main extends Game implements ApplicationListener {
	
	public static int levelsPassed = 0;
	
	// Used for static access of otherwise non-static items
	public static Main main;
	
	// The screen used for game-play
	public GameScreen gameScreen;
	
	// The screen used for logging in
	public MenuScreen menuScreen;
	
	// The screen used to select levels
	public LevelSelectScreen levelSelectScreen;
	
	public DeathScreen deathScreen;
	
	public CompleteScreen completeScreen;
	
	public CutsceneScreen cutsceneScreen;
	
	public SettingsScreen settingsScreen;
	
	public TeamsScreen teamsScreen;
	
	@SuppressWarnings("resource")
	@Override
	public void create() {
		Resources.loadTextures();
		Sounds.load();
		
		main = this;
		gameScreen 			= new GameScreen();
		menuScreen			= new MenuScreen();
		levelSelectScreen	= new LevelSelectScreen();
		deathScreen 		= new DeathScreen(null);
		completeScreen 		= new CompleteScreen(null);
		cutsceneScreen		= new CutsceneScreen();
		settingsScreen 		= new SettingsScreen();
		teamsScreen 		= new TeamsScreen();
		
		setScreen(teamsScreen);
		
		try {
			levelsPassed = Integer.parseInt(new Scanner(new File(Strings.LEVELS_DIR + "leveldata.txt")).nextLine());
		} catch (NumberFormatException | FileNotFoundException e) {
			DesktopLauncher.log();
			e.printStackTrace();
		}
	}

	public static void save() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(Strings.LEVELS_DIR + "leveldata.txt"));
		writer.write(Integer.toString(Main.levelsPassed));
		writer.close();
	}
	
	@Override
	public void dispose() {
		main.getScreen().dispose();
	}

	@Override
	public void render() {
		main.getScreen().render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void resize(int width, int height) {
		main.getScreen().resize(width, height);
	}

	@Override
	public void pause() {
		main.getScreen().pause();
	}

	@Override
	public void resume() {
		main.getScreen().resume();
	}
}