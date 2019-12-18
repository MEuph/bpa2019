package com.cognitivethought.resources;

import java.io.File;

import com.badlogic.gdx.graphics.Texture;

public class Resources {

	public static Texture APPLE;
	public static Texture ORGANIC_MATTER;
	public static Texture SEED;
	public static Texture STICK;
	public static Texture COIN;
	public static Texture NONE;
	
	public static Texture TRASH_ATTACK;
	public static Texture TRASH_DEATH;
	public static Texture TRASH_JUMP;
	public static Texture TRASH_MONSTER;

	public static Texture PLAYER_ATTACK;
	public static Texture PLAYER_BASE;
	public static Texture PLAYER_DEATH;
	public static Texture PLAYER_IDLE;
	public static Texture PLAYER_THROW;
	
	public static Texture CHECK;
	
	public static File INV_1;
	public static File CRAFTING_FILE;
	
	public static Texture BIRD;
	
	public static Texture BARK;
	public static Texture EXIT;
	public static Texture EXIT1;
	public static Texture EXIT2;
	public static Texture HEART;
	public static Texture LVL1;
	public static Texture LVL2;
	public static Texture LVL3;
	public static Texture LVL4;
	public static Texture LVL5;
	public static Texture MONSTER_HEART;
	public static Texture UI_BACKGROUND;
	public static Texture PLAY_BUTTON;
	public static Texture QUIT_BUTTON;
	public static Texture RETRY;
	public static Texture RETRY1;
	public static Texture RETRY2;
	public static Texture TITLE;
	public static Texture UI_CRAFTING;
	
	public static Texture BG;
	
	public static void loadTextures() {
		APPLE = new Texture(Strings.ITEMS_DIR + "apple.png");
		SEED = new Texture(Strings.ITEMS_DIR + "seed.png");
		ORGANIC_MATTER = new Texture(Strings.ITEMS_DIR + "organic_matter.png");
		STICK = new Texture(Strings.ITEMS_DIR + "stick.png");
		COIN = new Texture(Strings.ITEMS_DIR + "coin.png");
		NONE = new Texture(Strings.ITEMS_DIR + "none.png");

		TRASH_ATTACK = new Texture(Strings.TRASH_MONSTER_DIR + "attack.png");
		TRASH_DEATH = new Texture(Strings.TRASH_MONSTER_DIR + "death.png");
		TRASH_JUMP = new Texture(Strings.TRASH_MONSTER_DIR + "jump.png");
		TRASH_MONSTER = new Texture(Strings.TRASH_MONSTER_DIR + "trashmonster.png");

		PLAYER_ATTACK = new Texture(Strings.PLAYER_DIR + "attack.png");
		PLAYER_BASE = new Texture(Strings.PLAYER_DIR + "base.png");
		PLAYER_DEATH = new Texture(Strings.PLAYER_DIR + "death.png");
		PLAYER_IDLE = new Texture(Strings.PLAYER_DIR + "idle.png");
		PLAYER_THROW = new Texture(Strings.PLAYER_DIR + "throw.png");
		
		BARK = new Texture(Strings.UI_DIR + "bark.png");
		EXIT = new Texture(Strings.UI_DIR + "ExitButton.png");
		EXIT1 = new Texture(Strings.UI_DIR + "Exit Button-1.png.png");
		EXIT2 = new Texture(Strings.UI_DIR + "Exit Button-2.png.png");
		HEART = new Texture(Strings.UI_DIR + "heart.png");
		LVL1 = new Texture(Strings.UI_DIR + "LeveloneButton.png.png");
		LVL2 = new Texture(Strings.UI_DIR + "LeveltwoButton.png.png");
		LVL3 = new Texture(Strings.UI_DIR + "LevelthreeButton.png.png");
		LVL4 = new Texture(Strings.UI_DIR + "LevelfourButton.png.png");
		LVL5 = new Texture(Strings.UI_DIR + "LevelfiveButton.png.png");
		MONSTER_HEART = new Texture(Strings.UI_DIR + "monsterHeart.png");
		UI_BACKGROUND = new Texture(Strings.UI_DIR + "placeholderbackground.png");
		PLAY_BUTTON = new Texture(Strings.UI_DIR + "PlayButton.png");
		QUIT_BUTTON = new Texture(Strings.UI_DIR + "QuitButton.png");
		RETRY = new Texture(Strings.UI_DIR + "RetryButton.png");
		RETRY1 = new Texture(Strings.UI_DIR + "Retry Button-1.png.png");
		RETRY2 = new Texture(Strings.UI_DIR + "Retry Button-2.png.png");
		TITLE = new Texture(Strings.UI_DIR + "title.png");
		UI_CRAFTING = new Texture(Strings.UI_DIR + "craftingicon.png");
		CHECK = new Texture(Strings.UI_DIR + "check.png");
		
		BG = new Texture(Strings.UI_DIR + "background.png");
		
		BIRD = new Texture(Strings.DEV_LEVEL_DIR + "tutbird.png");
		
		INV_1 = new File(Strings.INV_DIR + "inv1.txt");
		CRAFTING_FILE = new File(Strings.INV_DIR + "recipes.txt");
	}
}