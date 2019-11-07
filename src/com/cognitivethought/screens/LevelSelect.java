package com.cognitivethought.screens;

import java.io.IOException;

import javax.imageio.ImageIO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.cognitivethought.gui.LevelButton;
import com.cognitivethought.level.Level;
import com.cognitivethought.main.Main;

public class LevelSelect implements Screen {
	LevelButton[] levels = new LevelButton[5];
	
	Texture background = new Texture("assets/UI/placeholderbackground.png");
	
	SpriteBatch batch = new SpriteBatch();
	
	float y;
	float fade;
	
	public LevelSelect() {
		for (int i = 0; i < 5; i++) {
			levels[i] = new LevelButton();
			levels[i].addButton(i);
			switch (i) {
			  case 0:
				  levels[i].level.setClickListener(new ClickListener() {
					  @Override
					  public void clicked(InputEvent event, float x, float y) {
						  Main.main.setScreen(Main.main.gameScreen);
						  try {
							  Main.main.gameScreen.level = new Level(ImageIO.read(GameScreen.class.getResourceAsStream("/Levels/Development Level/level1.png")));
						  } catch (IOException e) {
							  // TODO Auto-generated catch block
							  e.printStackTrace();
						  }
					  }
				  });
			    break;
			  case 1:
				  levels[i].level.setClickListener(new ClickListener() {
					  @Override
					  public void clicked(InputEvent event, float x, float y) {
						  Main.main.setScreen(Main.main.gameScreen);
						  try {
							  Main.main.gameScreen.level = new Level(ImageIO.read(GameScreen.class.getResourceAsStream("/Levels/Development Level/level2.png")));
						  } catch (IOException e) {
							  // TODO Auto-generated catch block
							  e.printStackTrace();
						  }
					  }
				  });
			    break;
			  case 2:
				  levels[i].level.setClickListener(new ClickListener() {
					  @Override
					  public void clicked(InputEvent event, float x, float y) {
						  Main.main.setScreen(Main.main.gameScreen);
					  }
				  });
			    break;
			  case 3:
				  levels[i].level.setClickListener(new ClickListener() {
					  @Override
					  public void clicked(InputEvent event, float x, float y) {
						  Main.main.setScreen(Main.main.gameScreen);
					  }
				  });
			    break;
			  case 4:
				  levels[i].level.setClickListener(new ClickListener() {
					  @Override
					  public void clicked(InputEvent event, float x, float y) {
						  Main.main.setScreen(Main.main.gameScreen);
					  }
				  });
			    break;
			    default:
					  levels[i].level.setClickListener(new ClickListener() {
						  @Override
						  public void clicked(InputEvent event, float x, float y) {
							  System.exit(0);
						  }
					  });
				    break;
			
			}
		
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
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0.1f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		batch.draw(background, 0, -805, 1920, 1920);
		for(int i = 0; i < levels.length; i++) {
			levels[i].level.render(batch);
		}
//		for (Cloud c : clouds) {
//			c.animateBackground(c.cloud);
//			c.cloud.setY((y+c.cloud.getY()) + 1920 / 2 - 100);
//			c.cloud.draw(batch, delta);
//		}
		batch.end();

		
		
		
		
		if (fade <= 0f) {
			for(int i = 0; i < levels.length; i++) {
				levels[i].level.checkIfClicked(Gdx.input.getX(), Math.abs(1080-Gdx.input.getY()));
			}
		}
		// Enable transparency blending
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		// If still fading, then draw the black fade rectangle
		if (fade > 0f) {
			ShapeRenderer sp = new ShapeRenderer();
			sp.begin(ShapeType.Filled);
			sp.setColor(new Color(0, 0, 0, fade));
			sp.rect(0f, 0f, 1920f, 1080f);
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
		// TODO Auto-generated method stub
		
	}
	
}