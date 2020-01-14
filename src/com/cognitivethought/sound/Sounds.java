package com.cognitivethought.sound;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

public class Sounds {
	
	public static Sound chainsaw;
	public static Sound intro_music;
	public static Sound player_melee;
	public static Sound player_moving;
	
	public static long chainsaw_id;
	public static long intro_music_id;
	public static long player_melee_id;
	public static long player_moving_id;
	
	public static void load() {
		chainsaw = Gdx.audio.newSound(new FileHandle(new File("assets/Sounds/chainsaw.mp3")));
		intro_music = Gdx.audio.newSound(new FileHandle(new File("assets/Sounds/intro_music.mp3")));
		player_melee = Gdx.audio.newSound(new FileHandle(new File("assets/Sounds/player_melee.mp3")));
		player_moving = Gdx.audio.newSound(new FileHandle(new File("assets/Sounds/player_moving.mp3")));
	}
}