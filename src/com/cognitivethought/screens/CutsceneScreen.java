package com.cognitivethought.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cognitivethought.main.Main;
import com.cognitivethought.sound.Sounds;

/**
 * The screen where all cutscenes are shown
 */
public class CutsceneScreen implements Screen {

	public SpriteBatch b;

	public static final Cutscene LEVEL_1 = new Cutscene(new int[] { 1416, 1245, 1245, 1245, 1245, 1, 1, 20951, 2939, 2939,
			1400, 1400, 2248, 2990, 2815, 2815, 1434, 1434, 1902, 1902, 1902, 2500},
			breakdown("Cutscenes/Level1/scene_", 22));
	public static final Cutscene FINAL = new Cutscene(
			new int[] { 864, 864, 864, 864, 864, 21876, 500, 500, 500, 500, 500, 2500},
			breakdown("Cutscenes/final/final_", 12));
	public static final Cutscene SEQUOIA = new Cutscene(
			new int[] { 2072, 2072, 2072, 17050, 1500, 1500},
			breakdown("Cutscenes/sequoia/scene_", 6));
	public static final Cutscene ROAD = new Cutscene(
			new int[] { 1500, 1500, 1500, 1500, 1500, 1500, 1500},
			breakdown("Cutscenes/road/scene_", 7));
	public static final Cutscene TYRONE = new Cutscene(
			new int[] { 3956, 12984, 5054, 500, 500, 500, 500, 500, 1500},
			breakdown("Cutscenes/tyrone/scene_", 9));

	public Cutscene currentCutscene;

	public CutsceneScreen() {
		b = new SpriteBatch();
		
		LEVEL_1.toAdvanceTo = Main.main.gameScreen;
		FINAL.toAdvanceTo = Main.main.levelSelectScreen;
		SEQUOIA.toAdvanceTo = Main.main.gameScreen;
		ROAD.toAdvanceTo = Main.main.gameScreen;
		TYRONE.toAdvanceTo = Main.main.levelSelectScreen;
	}

	@Override
	public void render(float arg0) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (currentCutscene.isCompleted()) {
			Main.main.setScreen(currentCutscene.toAdvanceTo);
		}

		if (currentCutscene != null) {
			b.begin();
			currentCutscene.draw(b);
			b.end();
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		Sounds.intro_narration.stop();
		Sounds.sequoia_narration.stop();
		Sounds.axel_exposition.stop();
		Sounds.tyrone_exposition.stop();
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

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
		Sounds.intro_music.stop(Sounds.intro_music_id);
		if (currentCutscene == LEVEL_1) Sounds.intro_narration.play(1.0f);
		if (currentCutscene == SEQUOIA) Sounds.sequoia_narration.play(1.0f);
		if (currentCutscene == FINAL) Sounds.axel_exposition.play(1.0f);
		if (currentCutscene == TYRONE) Sounds.tyrone_exposition.play(1.0f);
		currentCutscene.reset();
	}

	public static Sprite[] breakdown(String path, int amount) {
		Sprite[] ret = new Sprite[amount];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = new Sprite(new Texture(path + Integer.toString(i) + ".png"));
		}

		return ret;
	}
}