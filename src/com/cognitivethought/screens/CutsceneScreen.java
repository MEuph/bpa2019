package com.cognitivethought.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cognitivethought.main.Main;

public class CutsceneScreen implements Screen {
	
	public SpriteBatch b;
	
	public static final Cutscene LEVEL_1 = 
			new Cutscene(new int[] {2000, 2000, 500, 500, 500, 500, 500, 500, 
					1000, 1000, 1000, 1500, 1000, 1000, 3000, 
					1000, 4000}, breakdown("Cutscenes/Level1/scene_", 17));
//	public static final Cutscene LEVEL_2 = 
//			new Cutscene();
//	public static final Cutscene LEVEL_3 = 
//			new Cutscene();
//	public static final Cutscene LEVEL_4 = 
//			new Cutscene();
	
	public Cutscene currentCutscene;
	public Screen toAdvanceTo;
	
	public CutsceneScreen() {
		b = new SpriteBatch();
	}
	

	@Override
	public void render(float arg0) {
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
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}
	
	public static Sprite[] breakdown(String path, int amount) {
		Sprite[] ret = new Sprite[amount];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = new Sprite(new Texture(path + Integer.toString(i) + ".png"));
		}
		
		return ret;
	}
}