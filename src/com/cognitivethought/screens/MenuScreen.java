package com.cognitivethought.screens;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.cognitivethought.gui.Cloud;
import com.cognitivethought.main.Main;

public class MenuScreen implements Screen {
	public static Stage stage;
	private Image background;
	private Image title;
	private ImageButton play;
	private ImageButton quit;
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	private ArrayList<Cloud> clouds;
	
	private int y = 0;
	private float fade;
	
	public MenuScreen() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		clouds = new ArrayList<>();
		
		for (int i = 0; i < new Random().nextInt(20) + 10; i++) {
			clouds.add(new Cloud());
		}
		
		Texture title = new Texture("assets/UI/placeholdertitle.png");
		Texture backgroundTexture = new Texture("assets/UI/placeholderbackground.png");
		Texture playTexture = new Texture("assets/UI/PlayButton.png");
		Texture quitTexture = new Texture("assets/UI/QuitButton.png");
		this.title = new Image(new TextureRegionDrawable(new TextureRegion(title)));
		background = new Image(new TextureRegionDrawable(new TextureRegion(backgroundTexture)));
		play = new ImageButton(new TextureRegionDrawable(new TextureRegion(playTexture)));
		quit = new ImageButton(new TextureRegionDrawable(new TextureRegion(quitTexture)));
		
		this.title.setPosition(screenSize.width/2-this.title.getWidth()/2, screenSize.height-150);
		background.setPosition(0, this.y);
		background.setSize(screenSize.width, screenSize.width);
		play.setPosition(100, screenSize.height/2-350);
		play.setSize(287, 143);
		quit.setPosition(100, screenSize.height/2-494);
		quit.setSize(287, 143);
		
		stage.addActor(background);
		for(Cloud c : clouds) {
			c.cloud();
		}
		
		stage.addActor(play);
		stage.addActor(quit);
		stage.addActor(this.title);
		
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

		for (Cloud c : clouds) {
			c.animateBackground(c.cloud);
			c.cloud.setY((background.getY() + c.cloud.getY()) + background.getHeight() / 2 - 100);
		}
		
		if (y >= -775) {
			background.setPosition(0, this.y);
			y-=5;
		} else {
			y = -775;
		}
		
		fade = 1-((float)y / -755f);
		System.out.println(fade);
		
		stage.act(delta);
		stage.draw();
		
		
		if (fade <= 0f) {
			if (play.isPressed()) Main.main.setScreen(Main.main.gameScreen);
			if (quit.isPressed()) System.exit(0);
		}
		// Enable transparency blending
				Gdx.gl.glEnable(GL20.GL_BLEND);
				Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
				// If still fading, then draw the black fade rectangle
				if (fade > 0f) {
					ShapeRenderer sp = new ShapeRenderer();
					sp.begin(ShapeType.Filled);
					sp.setColor(new Color(0, 0, 0, fade));
					sp.rect(0f, 0f, (float)screenSize.getWidth(), (float)screenSize.getHeight());
					sp.end();
				}
				// Disable transparency blending
				Gdx.gl.glDisable(GL20.GL_BLEND);
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
		y = 0;
	}

}
