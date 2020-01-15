package com.cognitivethought.sound;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

public class Sounds {
	
	public static Sound city_music;
	public static Sound intro_music;
	public static Sound sequoia_council_music;
	public static Sound level5_music;
	
	public static Sound player_melee;
	public static Sound player_apple;
	public static Sound player_seed;
	
	public static Sound boss_run;
	public static Sound boss_smash;
	public static Sound boss_swipe;
	
	public static Sound trashcan_attack;
	public static Sound trash_attack;
	public static Sound chainsaw;
	public static Sound intro_narration;
	public static Sound sequoia_narration;
	public static Sound axel_exposition;
	public static Sound tyrone_exposition;
	
	public static long chainsaw_id;
	public static long intro_music_id;
	public static long city_music_id;
	public static long player_melee_id;
	public static long player_moving_id;
	public static long sequoia_council_music_id;
	public static long level5_music_id;
	
	public static void load() {
		city_music = Gdx.audio.newSound(new FileHandle(new File("assets/Sounds/city_music.mp3")));
		intro_music = Gdx.audio.newSound(new FileHandle(new File("assets/Sounds/intro_music.mp3")));
		level5_music = Gdx.audio.newSound(new FileHandle(new File("assets/Sounds/level5_music.mp3")));
		sequoia_council_music = Gdx.audio.newSound(new FileHandle(new File("assets/Sounds/sequoia_council.mp3")));
		
		intro_narration = Gdx.audio.newSound(new FileHandle(new File("assets/Sounds/intro_narration.mp3")));
		sequoia_narration = Gdx.audio.newSound(new FileHandle(new File("assets/Sounds/sequoia_narration.mp3")));
		axel_exposition = Gdx.audio.newSound(new FileHandle(new File("assets/Sounds/axel_exposition.mp3")));
		tyrone_exposition = Gdx.audio.newSound(new FileHandle(new File("assets/Sounds/tyrone_exposition.mp3")));
		
		player_melee = Gdx.audio.newSound(new FileHandle(new File("assets/Sounds/player_melee.mp3")));
		player_apple = Gdx.audio.newSound(new FileHandle(new File("assets/Sounds/player_apple.mp3")));
		player_seed = Gdx.audio.newSound(new FileHandle(new File("assets/Sounds/player_seed.mp3")));
		
		boss_run = Gdx.audio.newSound(new FileHandle(new File("assets/Sounds/BossRun.mp3")));
		boss_smash = Gdx.audio.newSound(new FileHandle(new File("assets/Sounds/BossSmash.mp3")));
		boss_swipe = Gdx.audio.newSound(new FileHandle(new File("assets/Sounds/BossSwipe.mp3")));
		
		chainsaw = Gdx.audio.newSound(new FileHandle(new File("assets/Sounds/chainsaw.mp3")));
		trashcan_attack = Gdx.audio.newSound(new FileHandle(new File("assets/Sounds/can_attack.mp3")));
		trash_attack = Gdx.audio.newSound(new FileHandle(new File("assets/Sounds/trash_attack.mp3")));
	}
}