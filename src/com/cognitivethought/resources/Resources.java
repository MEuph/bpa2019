package com.cognitivethought.resources;

import java.io.File;

import com.badlogic.gdx.graphics.Texture;

public class Resources {

	public static Texture APPLE;
	public static Texture SEED;

	public static Texture TRASH_ATTACK;
	public static Texture TRASH_DEATH;
	public static Texture TRASH_JUMP;
	public static Texture TRASH_MONSTER;

	public static Texture PLAYER_ATTACK;
	public static Texture PLAYER_BASE;
	public static Texture PLAYER_DEATH;
	public static Texture PLAYER_IDLE;
	public static Texture PLAYER_THROW;

	public static File INV_1;
	
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
	public static Texture UI_TITLE;
	public static Texture PLAY_BUTTON;
	public static Texture QUIT_BUTTON;
	public static Texture RETRY;
	public static Texture RETRY1;
	public static Texture RETRY2;
	
	public static Texture BG;
	
	public static void loadTextures() {
		APPLE = new Texture(Strings.ITEMS_DIR + "apple.png");
		SEED = new Texture(Strings.ITEMS_DIR + "seed.png");

		TRASH_ATTACK = new Texture(Strings.TRASH_MONSTER_DIR + "attack.png");
		TRASH_DEATH = new Texture(Strings.TRASH_MONSTER_DIR + "death.png");
		TRASH_JUMP = new Texture(Strings.TRASH_MONSTER_DIR + "jump.png");
		TRASH_MONSTER = new Texture(Strings.TRASH_MONSTER_DIR + "trashmonster.png");

		PLAYER_ATTACK = new Texture(Strings.PLAYER_DIR + "attack.png");
		PLAYER_BASE = new Texture(Strings.PLAYER_DIR + "base.png");
		PLAYER_DEATH = new Texture(Strings.PLAYER_DIR + "death.png");
		PLAYER_IDLE = new Texture(Strings.PLAYER_DIR + "idle.png");
		PLAYER_THROW = new Texture(Strings.PLAYER_DIR + "throw.png");
		
		INV_1 = new File(Strings.INV_DIR + "inv1.txt");
	}
}