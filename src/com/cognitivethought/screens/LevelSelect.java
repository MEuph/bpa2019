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
import com.cognitivethought.gui.ImageButton;
import com.cognitivethought.gui.LevelButton;
import com.cognitivethought.entity.Player;
import com.cognitivethought.level.Level;
import com.cognitivethought.main.Main;
//screen to select the level
public class LevelSelect implements Screen {
	LevelButton[] levels = new LevelButton[5]; //list that stores the level buttons
	public static int levelNumber = 0;
	Texture background = new Texture("assets/UI/placeholderbackground.png"); // background texture
	ImageButton quitButton = new ImageButton(new Texture("assets/UI/QuitButton.png"), 100, 100);
	
	SpriteBatch batch = new SpriteBatch(); //spritebatch initialization

	float y;
	float fade;
	
	public LevelSelect() { //main method of the level selection
		
		for (int i = 0; i < levels.length; i++) { //adds buttons to the list and sets the properties of each
			levels[i] = new LevelButton();
			levels[i].addButton(i);
			switch (i) { //switch to determine which level the button takes you to 
			  case 0:
				  levels[i].level.setClickListener(new ClickListener() { 
					  @Override
					  public void clicked(InputEvent event, float x, float y) { //assigns the action that happens when the button is clicked for level 1
						  Main.main.setScreen(Main.main.gameScreen);
						  try {
							  Main.main.setScreen(Main.main.gameScreen);
							  Main.main.gameScreen.level = new Level(ImageIO.read(GameScreen.class.getResourceAsStream("/Levels/Development Level/level1.png")));
							  levelNumber = 1;
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
					  public void clicked(InputEvent event, float x, float y) { //assigns the action that happens when the button is clicked for level 2
						  
						  if (Player.levelsPassed >= 1) {
							  try {
								  Main.main.setScreen(Main.main.gameScreen);
								  Main.main.gameScreen.level = new Level(ImageIO.read(GameScreen.class.getResourceAsStream("/Levels/Development Level/level2.png")));
								  levelNumber = 2;
							  } catch (IOException e) {
								  // TODO Auto-generated catch block
								  e.printStackTrace();
							  }
						  } 
					  }
				  });
			    break;
			  case 2:
				  levels[i].level.setClickListener(new ClickListener() {
					  @Override
					  public void clicked(InputEvent event, float x, float y) { //assigns the action that happens when the button is clicked for level 3
						  if (Player.levelsPassed >= 2) {
							  try {
								  Main.main.setScreen(Main.main.gameScreen);
								  Main.main.gameScreen.level = new Level(ImageIO.read(GameScreen.class.getResourceAsStream("/Levels/Development Level/lvl 3.png")));
								  levelNumber = 3;
							  } catch (IOException e) {
								  // TODO Auto-generated catch block
								  e.printStackTrace();
							  }
						  }
					  }
				  });
			    break;
			  case 3:
				  levels[i].level.setClickListener(new ClickListener() {
					  @Override
					  public void clicked(InputEvent event, float x, float y) { //assigns the action that happens when the button is clicked for level 4
						  Main.main.setScreen(Main.main.gameScreen);
						  levelNumber = 4;
					  }
				  });
			    break;
			  case 4:
				  levels[i].level.setClickListener(new ClickListener() {
					  @Override
					  public void clicked(InputEvent event, float x, float y) { //assigns the action that happens when the button is clicked for level 5
						  Main.main.setScreen(Main.main.gameScreen);
						  levelNumber = 5;
					  }
				  });
			    break;
			    default:
					  levels[i].level.setClickListener(new ClickListener() {
						  @Override
						  public void clicked(InputEvent event, float x, float y) { //assigns the action that happens when the button is clicked as a default
							  System.exit(0);
						  }
					  });
				    break;
			
			}
			quitButton.setClickListener(new ClickListener() { //sets the actions to perform if the buttons are clicked
				@Override
				public void clicked(InputEvent event, float x, float y) {
					System.exit(0);
				}
			});
		
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
		quitButton.render(batch);
//		for (Cloud c : clouds) {
//			c.animateBackground(c.cloud);
//			c.cloud.setY((y+c.cloud.getY()) + 1920 / 2 - 100);
//			c.cloud.draw(batch, delta);
//		}
		batch.end();
		if (fade <= 0f) {
			for(int i = 0; i < levels.length; i++) {
				levels[i].level.checkIfClicked(Gdx.input.getX(), Math.abs(1080-Gdx.input.getY())); //checks if the button has been clicked per frame
				quitButton.checkIfClicked(Gdx.input.getX(), Math.abs(1080-Gdx.input.getY()));
			}
		}

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