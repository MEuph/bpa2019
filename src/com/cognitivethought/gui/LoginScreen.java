package com.cognitivethought.gui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.cognitivethought.main.Main;
import com.cognitivethought.screens.GameScreen;

public class LoginScreen implements Screen {
	private GameScreen game;
	private Stage stage;
	private ImageButton play;
	
	
	public LoginScreen() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		Texture playTexture = new Texture("Button.png.png");
		Image playImage = new Image(playTexture);
		play = new ImageButton(new TextureRegionDrawable(new TextureRegion(playTexture)));
		
		play.setPosition(300, 300);
		play.setSize(448, 448);
		
		stage.addActor(play);
		
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
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
		stage.act(delta);
		stage.draw();
		
		if (play.isPressed()) Main.main.setScreen(Main.main.gameScreen);
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
