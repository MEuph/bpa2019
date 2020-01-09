package com.cognitivethought.screens;

import java.io.FileNotFoundException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.cognitivethought.inventory.InventoryBar;
import com.cognitivethought.level.Level;
import com.cognitivethought.resources.Resources;
import com.cognitivethought.resources.Strings;
import com.cognitivethought.ui.HealthBar;

public class GameScreen implements Screen {

	public ShapeRenderer sp;
	public SpriteBatch b; // The batch renderer that helps to render sprites faster than usual
	public Level level; // Holds current level information
	public BitmapFont font; // For FPS Counter
	public OrthographicCamera c; // Camera
	
	public HealthBar hb = new HealthBar();
	public InventoryBar ib = new InventoryBar("assets/Inventory/inv.txt", hb);
	public static boolean paused;
	
	float smoothCamera = .1f; // How much to smooth the camera's movement by
	float timer = 0; // Timer for updating FPS counter
	float fade = 1f; // Timer/Opacity for screen fade-in
	
	Sprite background;
	
	String fps = "FPS:"; // Shows the current FPS

	@Override
	public void show() {
		b = new SpriteBatch();
		sp = new ShapeRenderer();
		
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		if (LevelSelectScreen.levelNumber == 4) {
			
		}
		else {
			background = new Sprite(Resources.BG);
		}
		background.setSize(1920 * 4, 1080 * 2);
		background.setPosition(-1920, -1080 * 2f);
		
		InventoryBar.grid.shown = false;
		
		try {
			InventoryBar.i.read(Strings.INV_DIR + "/inv.txt", InventoryBar.grid);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
//		try {
//			level = new Level(ImageIO.read(GameScreen.class.getResourceAsStream("/Levels/Development Level/level1.png")), this); // Initialize level with 'testlevel.level'
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		c = new OrthographicCamera();
		c.setToOrtho(false, 1920, 1080); // Create camera, and set size to 1920x1080
		if (c == null) System.out.println("c");
		if (level == null) System.out.println("level");
		if (level.getSpawnpoint() == null) System.out.println("spawn");
		c.position.set(level.getSpawnpoint().getPlayer().getX(), level.getSpawnpoint().getPlayer().getY(), 0f);
		
		// Run new Thread that will process the fading in of the scene
		new Thread(new Runnable() {
			@Override
			public void run() {
				c.position.set(new Vector3(c.position.x, c.position.y + 200, c.position.z));
				while (fade > 0f) {
					fade -= .01f;
//					System.out.println(fade);
					c.translate(0, -1f, 0);
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public GameScreen() {

	}

	@Override
	public void dispose() {
//		batch.dispose();
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {

	}

	@Override
	public void render(float deltaTime) {
		Gdx.gl.glClearColor(0f,0.1f,0f,1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Smooth camera fade
		Vector3 position = c.position;
		smoothCamera = 0.1f / c.zoom;
		
		if (!(fade > 0)) {
			position.x += (level.getSpawnpoint().getPlayer().getX() - position.x) * smoothCamera;
			position.y += (level.getSpawnpoint().getPlayer().getY() - position.y + 100) * smoothCamera;
		}
		
		c.position.set(position);
		c.update();
		// End smooth camera fade
		
		// Set the projection matrix of the sprite batch to the camera's combined matrix
		b.setProjectionMatrix(c.combined);
		
		if (!b.isDrawing()) b.begin();
		background.draw(b);
		background.translateX(background.getWidth());
		background.flip(true, false);
		background.draw(b);
		background.translateX(-background.getWidth());
		background.flip(true, false);
		background.draw(b);
		
		if (b == null) {
			System.out.println("batch");
		}

		if (hb == null) {
			System.out.println("hb");
		}

		if (c == null) {
			System.out.println("c");
		}
		
		level.render(b, hb, c, paused);

		// If the level has faded in, process physics on the player
		if (!(fade > 0) && !InventoryBar.grid.shown && !paused) {
			level.getSpawnpoint().getPlayer().update(level, hb, ib);
		}
		level.getSpawnpoint().getPlayer().render(b, paused);

		// Increment the FPS timer
		timer += Gdx.graphics.getDeltaTime();

		// If the timer has surpassed 0.5, then reset the timer and update the FPS
		// counter
		if (timer > .5f) {
			timer = 0f;
			fps = "FPS: " + (int) (1 / Gdx.graphics.getDeltaTime());
		}

		// Draw the FPS counter
		font.draw(b, fps, c.position.x - (c.viewportWidth / 2), c.position.y + (c.viewportHeight / 2) - 20f);
		
		hb.render(b, c);
		if (!InventoryBar.grid.shown) ib.render(b, c, sp);
		
		b.end();

		// Enable transparency blending
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		// If still fading, then draw the black fade rectangle
		if (fade > 0f || InventoryBar.grid.shown || paused) {
			ShapeRenderer sp = new ShapeRenderer();
			sp.setProjectionMatrix(c.combined);
			sp.begin(ShapeType.Filled);
			sp.setColor(new Color(0, 0, 0, InventoryBar.grid.shown || paused ? 0.9f : fade));
			sp.rect(c.position.x - (c.viewportWidth / 2), c.position.y - (c.viewportHeight / 2), 1920, 1080);
			sp.end();
		}
		// Disable transparency blending
		Gdx.gl.glDisable(GL20.GL_BLEND);

		if (InventoryBar.grid.shown) ib.render(b, c, sp);
		
		// Zoom In-Out Support
//		if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT_BRACKET)) {
//			c.zoom += c.zoom < 1f ? 0.25f : 0f;
//		}
//		
//		if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT_BRACKET)) {
//			c.zoom -= c.zoom > 0.25f ? 0.25f : 0f;
//		}
//
//		if (c.zoom < 0.25f) {
//			c.zoom = 0.25f;
//		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) { 
			togglePause();
		}
		
		if (paused) {
			InventoryBar.pm.render(b, c, sp, InventoryBar.font, 500, 300);
		}
	}
	
	public static void togglePause() {
		if (paused) {
			paused = false;
			InventoryBar.paused = false;
		} else {
			if (!InventoryBar.grid.shown) {
				paused = true;
				InventoryBar.paused = true;
			} else {
				InventoryBar.paused = false;
				InventoryBar.grid.shown = false;
			}
		}
	}
	
	@Override
	public void resize(int w, int h) {
		 c.setToOrtho(false, w, h);
	}

	@Override
	public void resume() {

	}
}