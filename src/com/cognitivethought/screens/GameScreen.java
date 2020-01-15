package com.cognitivethought.screens;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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
import com.cognitivethought.inventory.Item;
import com.cognitivethought.level.Level;
import com.cognitivethought.main.desktop.DesktopLauncher;
import com.cognitivethought.resources.Resources;
import com.cognitivethought.resources.Strings;
import com.cognitivethought.sound.Sounds;
import com.cognitivethought.ui.HealthBar;

public class GameScreen implements Screen {

	public ShapeRenderer sp;
	public SpriteBatch b; // The batch renderer that helps to render sprites faster than usual
	public Level level; // Holds current level information
	public BitmapFont font; // For FPS Counter
	public OrthographicCamera c; // Camera
	
	public static HealthBar hb = new HealthBar();
	public InventoryBar ib = new InventoryBar("assets/Inventory/inv.txt", hb);
	public static boolean paused;
	
	float smoothCamera = .1f; // How much to smooth the camera's movement by
	float timer = 0; // Timer for updating FPS counter
	float fade = 1f; // Timer/Opacity for screen fade-in
	
	ArrayList<Sprite> background = new ArrayList<Sprite>();
	
	String fps = "FPS:"; // Shows the current FPS
	
	@Override
	public void show() {
		if (LevelSelectScreen.levelNumber == 1) {
			if (Sounds.intro_music_id == 0) {
				Sounds.intro_music_id = Sounds.intro_music.play(SettingsScreen.VOL_MUSIC);
				Sounds.intro_music.setLooping(Sounds.intro_music_id, true);
			}
		}
		
		background.clear();
		
		hb.health = 3;
		hb.bark = 2;
		
		b = new SpriteBatch();
		sp = new ShapeRenderer();
		
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		
		Scanner sc;
		try {
			sc = new Scanner(new File(Strings.BG_DIR + "level" + Integer.toString(LevelSelectScreen.levelNumber)));
		} catch (FileNotFoundException e2) {
			sc = null;
			e2.printStackTrace();
		}
		
		if (sc.hasNextLine()) {
			String data = sc.nextLine();
			String[] splitData = data.split(";");
			
			System.out.println(Arrays.toString(splitData));
			
			for (int i = 0; i < splitData.length; i+=3) {
				int type = Integer.parseInt(splitData[i]);
				System.out.println(i + ", " + type);
				switch (type) {
				case 0:
					if (!(LevelSelectScreen.levelNumber == 4)) {
						background.add(new Sprite(Resources.TREE_BG));
					} else {
						background.add(new Sprite(Resources.BGCITY));
					}
					
					background.get(i / 3).setPosition(Integer.parseInt(splitData[i+1]), Integer.parseInt(splitData[i+2]));
					background.get(i / 3).setSize(1800, 1240);
					background.get(i / 3).translate(1800, 0);
					if ((i / 3) % 2 == 0) background.get(i / 3).flip(true, false);
					break;
				case 1:
					background.add(new Sprite(Resources.SMOKE_BG));
					background.get(i / 3).setPosition(Integer.parseInt(splitData[i+1]), Integer.parseInt(splitData[i+2]));
					background.get(i / 3).setSize(1800, 1240);
					if ((i / 3) % 2 == 0) background.get(i / 3).flip(true, false);
					break;
				}
			}
		}
		
		InventoryBar.grid.shown = false;
		
		try {
			InventoryBar.i.read(Strings.INV_DIR + "/inv.txt", InventoryBar.grid);
		} catch (FileNotFoundException e1) {
			DesktopLauncher.log();
			e1.printStackTrace();
		}
		
		c = new OrthographicCamera();
		c.setToOrtho(false, 1920, 1080); // Create camera, and set size to 1920x1080
		
		// Run new Thread that will process the fading in of the scene
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(level.getSpawnpoint().getPlayer().getX() + ", " + level.getSpawnpoint().getPlayer().getY());
				c.position.set(level.getSpawnpoint().getPlayer().getX(), level.getSpawnpoint().getPlayer().getY() + 200, 0f);
				while (fade > 0f) {
					fade -= .01f;
					c.translate(0, -1f, 0);
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						DesktopLauncher.log();
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		InventoryBar.currentlyHeldItem = new Item(Item.NONE, 0, 0);
	}

	public GameScreen() {

	}

	@Override
	public void dispose() {
//		batch.dispose();
	}

	@Override
	public void hide() {
		Sounds.intro_music.stop(Sounds.intro_music_id);
		
		Sounds.chainsaw.stop();
		Sounds.trash_attack.stop();
		Sounds.trashcan_attack.stop();
		
		dispose();
	}

	@Override
	public void pause() {

	}

	@Override
	public void render(float deltaTime) {
		Gdx.gl.glClearColor(0f,163f/255f,240f/255f,1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (background.size() > 0) {
			ShapeRenderer sp = new ShapeRenderer();
			sp.setProjectionMatrix(c.combined);
			sp.begin(ShapeType.Filled);
			sp.setColor(new Color(119f / 255f, 87f / 255f, 47f / 255f, 1));
			sp.rect(background.get(0).getX(), background.get(0).getY() - 600, 50000, 610);
			sp.end();
		}
		
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
		for (int i = 0; i < background.size(); i++) {
			background.get(i).draw(b);
		}
		
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
			sp.setProjectionMatrix(c.combined);
			sp.begin(ShapeType.Filled);
			sp.setColor(new Color(0, 0, 0, InventoryBar.grid.shown || paused ? 0.9f : fade));
			sp.rect(c.position.x - (c.viewportWidth / 2), c.position.y - (c.viewportHeight / 2), 1920, 1080);
			sp.end();
		}
		
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