package com.cognitivethought.gui;


import java.awt.Dimension;
import java.awt.Toolkit;
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

public class LoginScreen implements Screen {
	public static Stage stage;
	private Image background;
	private ImageButton play;
	private ImageButton quit;
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private Cloud[] cloudArray;
	public LoginScreen() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		cloudArray = new Cloud[3];
		cloudArray[0] = new Cloud();
		cloudArray[1] = new Cloud();
		cloudArray[2] = new Cloud();
		Texture backgroundTexture = new Texture("backgroundlogin.png");
		Texture playTexture = new Texture("PlayButton.png");
		Texture quitTexture = new Texture("QuitButton.png");
		background = new Image(new TextureRegionDrawable(new TextureRegion(backgroundTexture)));
		play = new ImageButton(new TextureRegionDrawable(new TextureRegion(playTexture)));
		quit = new ImageButton(new TextureRegionDrawable(new TextureRegion(quitTexture)));
		
		background.setPosition(0, 0);
		background.setSize(screenSize.width, screenSize.height);
		play.setPosition(screenSize.width/2-144, screenSize.height/2-200);
		play.setSize(287, 143);
		quit.setPosition(screenSize.width/2-144, screenSize.height/2-344);
		quit.setSize(287, 143);
		
		
		stage.addActor(background);
		cloudArray[0].cloud();
		cloudArray[1].cloud();
		cloudArray[2].cloud();
		stage.addActor(play);
		stage.addActor(quit);
		
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
		for(int i = 0; i < cloudArray.length; i++) {
			cloudArray[i].animateBackground(cloudArray[i].cloud);
		}
		
		stage.act(delta);
		stage.draw();
		
		
		if (play.isPressed()) Main.main.setScreen(Main.main.gameScreen);
		if (quit.isPressed()) System.exit(0);
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
