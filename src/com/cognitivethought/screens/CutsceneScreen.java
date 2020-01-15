package com.cognitivethought.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cognitivethought.main.Main;
import com.cognitivethought.sound.Sounds;

public class CutsceneScreen implements Screen {

	public SpriteBatch b;

	public static final Cutscene LEVEL_1 = new Cutscene(new int[] { 1416, 1245, 1245, 1245, 1245, 1, 1, 20951, 2939, 2939,
			1400, 1400, 2248, 2990, 2815, 2815, 1434, 1434, 1902, 1902, 1902, 2500},
			breakdown("Cutscenes/Level1/scene_", 22));
	public static final Cutscene FINAL = new Cutscene(
			new int[] { 1500, 250, 250, 250, 250, 250, 500, 7500, 1000, 1000, 1500, 2500 },
			breakdown("Cutscenes/final/final_", 12));
	public static final Cutscene SEQUOIA = new Cutscene(
			new int[] { 1500, 1500, 1500, 1500, 1500, 1500},
			breakdown("Cutscenes/sequoia/scene_", 6));
//	public static final Cutscene LEVEL_4 = 
//			new Cutscene();

	public Cutscene currentCutscene;
	
	public static Screen toAdvanceTo;

	public CutsceneScreen() {
		b = new SpriteBatch();
	}

	@Override
	public void render(float arg0) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (currentCutscene.isCompleted()) {
			Main.main.setScreen(toAdvanceTo);
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