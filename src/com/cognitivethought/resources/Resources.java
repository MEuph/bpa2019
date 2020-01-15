package com.cognitivethought.resources;

import java.io.File;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.cognitivethought.main.desktop.DesktopLauncher;

/**
 * Makes it so that many resources only have to be loaded once
 */
public class Resources {

	public static Texture BARK_ITEM;
	public static Texture APPLE;
	public static Texture ORGANIC_MATTER;
	public static Texture SEED;
	public static Texture STICK;
	public static Texture COIN;
	public static Texture NONE;
	public static Texture FERTILIZER;

	public static Texture TRASH_ATTACK;
	public static Texture TRASH_DEATH;
	public static Texture TRASH_JUMP;
	public static Texture TRASH_MONSTER;
	
	public static Texture AXEL_MOVE;
	public static Texture AXEL_ATTACK;
	public static Texture AXEL_MAJOR;
	public static Texture AXEL_DEATH;
	
	public static Texture TYRONE_MOVE;
	public static Texture TYRONE_ATTACK;
	public static Texture TYRONE_MAJOR;
	public static Texture TYRONE_DEATH;
	
	

	public static Texture PLAYER_ATTACK;
	public static Texture PLAYER_BASE;
	public static Texture PLAYER_DEATH;
	public static Texture PLAYER_IDLE;
	public static Texture PLAYER_THROW;

	public static Texture CHECK;

	public static File INV_1;
	public static File CRAFTING_FILE;
	public static File LVL1_SHOP_FILE;

	public static Texture BIRD;

	public static Texture BARK;
	public static Texture EXIT;
	public static Texture EXIT1;
	public static Texture EXIT2;
	public static Texture HEART;
	public static Texture LVL1_POS;
	public static Texture LVL2_POS;
	public static Texture LVL3_POS;
	public static Texture LVL4_POS;
	public static Texture LVL5_POS;
	public static Texture LVL1_NEG;
	public static Texture LVL2_NEG;
	public static Texture LVL3_NEG;
	public static Texture LVL4_NEG;
	public static Texture LVL5_NEG;
	public static Texture MONSTER_HEART;
	public static Texture UI_BACKGROUND;
	public static Texture PLAY_BUTTON;
	public static Texture QUIT_BUTTON;
	public static Texture RETRY;
	public static Texture RETRY1;
	public static Texture RETRY2;
	public static Texture TITLE;
	public static Texture UI_CRAFTING;

	public static Texture WHITE_DOLLAR;
	public static Texture GRAY_DOLLAR;

	public static Texture BG;
	public static Texture BGCITY;
	public static Texture X;
	public static Texture FIRE_BG;
	public static Texture TREE_BG;
	public static Texture SMOKE_BG;

	public static void loadTextures() {
		try {
			APPLE = new Texture(Strings.ITEMS_DIR + "apple.png");
			SEED = new Texture(Strings.ITEMS_DIR + "seed.png");
			ORGANIC_MATTER = new Texture(Strings.ITEMS_DIR + "organic_matter.png");
			STICK = new Texture(Strings.ITEMS_DIR + "stick.png");
			COIN = new Texture(Strings.ITEMS_DIR + "coin.png");
			NONE = new Texture(Strings.ITEMS_DIR + "none.png");
			FERTILIZER = new Texture(Strings.ITEMS_DIR + "fertilizer.png");
			BARK_ITEM = new Texture(Strings.ITEMS_DIR + "bark.png");
	
			TRASH_ATTACK = new Texture(Strings.TRASH_MONSTER_DIR + "attack.png");
			TRASH_DEATH = new Texture(Strings.TRASH_MONSTER_DIR + "death.png");
			TRASH_JUMP = new Texture(Strings.TRASH_MONSTER_DIR + "jump.png");
			TRASH_MONSTER = new Texture(Strings.TRASH_MONSTER_DIR + "trashmonster.png");
	
			PLAYER_ATTACK = new Texture(Strings.PLAYER_DIR + "attack.png");
			PLAYER_BASE = new Texture(Strings.PLAYER_DIR + "base.png");
			PLAYER_DEATH = new Texture(Strings.PLAYER_DIR + "death.png");
			PLAYER_IDLE = new Texture(Strings.PLAYER_DIR + "idle.png");
			PLAYER_THROW = new Texture(Strings.PLAYER_DIR + "throw.png");
			
			AXEL_MOVE = new Texture(Strings.AXEL_DIR + "Axel Walk.png");
			AXEL_ATTACK = new Texture(Strings.AXEL_DIR + "Axel Basic Attack.png");
			AXEL_MAJOR = new Texture(Strings.AXEL_DIR + "Axel Major Attack.png");
			AXEL_DEATH = new Texture(Strings.AXEL_DIR + "Axel Death.png");
			
			TYRONE_MOVE = new Texture(Strings.TYRONE_DIR + "Tyrone Walk.png");
			TYRONE_ATTACK = new Texture(Strings.TYRONE_DIR + "Tyrone Basic Attack.png");
			TYRONE_MAJOR = new Texture(Strings.TYRONE_DIR + "Tyrone Major Attack.png");
			TYRONE_DEATH = new Texture(Strings.TYRONE_DIR + "Tyrone Death.png");
	
			BARK = new Texture(Strings.UI_DIR + "bark.png");
			EXIT = new Texture(Strings.UI_DIR + "ExitButton.png");
			EXIT1 = new Texture(Strings.UI_DIR + "Exit Button-1.png.png");
			EXIT2 = new Texture(Strings.UI_DIR + "Exit Button-2.png.png");
			HEART = new Texture(Strings.UI_DIR + "heart.png");
			LVL1_POS = new Texture(Strings.BUTTONS_DIR + "1pos.png");
			LVL2_POS = new Texture(Strings.BUTTONS_DIR + "2pos.png");
			LVL3_POS = new Texture(Strings.BUTTONS_DIR + "3pos.png");
			LVL4_POS = new Texture(Strings.BUTTONS_DIR + "4pos.png");
			LVL5_POS = new Texture(Strings.BUTTONS_DIR + "5pos.png");
			LVL1_NEG = new Texture(Strings.BUTTONS_DIR + "1neg.png");
			LVL2_NEG = new Texture(Strings.BUTTONS_DIR + "2neg.png");
			LVL3_NEG = new Texture(Strings.BUTTONS_DIR + "3neg.png");
			LVL4_NEG = new Texture(Strings.BUTTONS_DIR + "4neg.png");
			LVL5_NEG = new Texture(Strings.BUTTONS_DIR + "5neg.png");
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
			X = new Texture(Strings.UI_DIR + "no.png");
			GRAY_DOLLAR = new Texture(Strings.UI_DIR + "graydollar.png");
			WHITE_DOLLAR = new Texture(Strings.UI_DIR + "whitedollar.png");
	
			BG = new Texture(Strings.UI_DIR + "background.png");
			BGCITY = new Texture(Strings.UI_DIR + "CityBackround.png");
	
			BIRD = new Texture(Strings.DEV_LEVEL_DIR + "tutbird.png");
	
			INV_1 = new File(Strings.INV_DIR + "inv1.txt");
			CRAFTING_FILE = new File(Strings.INV_DIR + "recipes.txt");
			LVL1_SHOP_FILE = new File(Strings.INV_DIR + "lvl1shop.txt");
			
			SMOKE_BG = new Texture(Strings.BG_DIR+ "smoke.png");
			TREE_BG = new Texture(Strings.BG_DIR + "trees.png");
			FIRE_BG = new Texture(Strings.BG_DIR + "firebg.png");
		} catch (GdxRuntimeException e) {
			e.printStackTrace();
			DesktopLauncher.log();
			System.exit(-1);
		}
	}
}