package com.cognitivethought.screens;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.cognitivethought.gui.Cloud;
import com.cognitivethought.gui.LevelButton;
import com.cognitivethought.main.Main;

public class LevelSelect implements Screen {
	public static Stage stage;
	private Image background;
	private ArrayList<LevelButton> levels;
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public LevelSelect() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		levels = new ArrayList<>();
		
		for (int i = 0; i < new Random().nextInt(20) + 10; i++) {
			levels.add(new LevelButton());
		}
		
		Texture title = new Texture("assets/UI/placeholdertitle.png");
		Texture backgroundTexture = new Texture("assets/UI/placeholderbackground.png");
		Texture playTexture = new Texture("assets/UI/PlayButton.png");
		Texture quitTexture = new Texture("assets/UI/QuitButton.png");
		background = new Image(new TextureRegionDrawable(new TextureRegion(backgroundTexture)));
		background.setPosition(0, 0);
		background.setSize(screenSize.width, screenSize.height);
		
		
		stage.addActor(background);
		for(LevelButton c : levels) {
			c.button();
		}
		
	}
	
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		stage.dispose();
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
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0f,0.1f,0f,1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		stage.draw();
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

}
