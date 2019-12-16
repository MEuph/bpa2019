package com.cognitivethought.level;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.cognitivethought.entity.ItemDrop;
import com.cognitivethought.entity.enemy.Behavior;
import com.cognitivethought.entity.enemy.EnemySpawner;
import com.cognitivethought.entity.enemy.TrashCanMonster;
import com.cognitivethought.entity.enemy.TrashMonster;
import com.cognitivethought.gui.TextBubble;
import com.cognitivethought.level.parts.Platform;
import com.cognitivethought.main.Main;
import com.cognitivethought.ui.HealthBar;
import com.cognitivethought.ui.InventoryBar;

public class Level {

	// All the platforms in the level
	ArrayList<Platform> platforms = new ArrayList<Platform>();
	ArrayList<EnemySpawner> es = new ArrayList<>();
	ArrayList<ItemDrop> itemDrops = new ArrayList<ItemDrop>();
	ArrayList<TextBubble> bubbles = new ArrayList<TextBubble>();
	
	Spawnpoint sp;
	Screen screen;
	
	/**
	 * @param s The path of the level file that will be parsed and loaded into this
	 *          level
	 * @throws FileNotFoundException
	 * @throws URISyntaxException
	 * 
	 *                               Exceptions will be thrown in the event of an
	 *                               error in parsing
	 * 
	 *                               PLEASE NOTE: PARSER WILL CHANGE. THIS IS NOT
	 *                               PERMANENT. DO NOT MAKE ANY PERMAMENT LEVELS IN
	 *                               THIS FILE FORMAT
	 */
	public Level(String s, Screen screen) throws FileNotFoundException, URISyntaxException {
		this.screen = screen;
		
		Scanner sc = new Scanner(new File(Level.class.getResource(s).toURI()));
		ArrayList<String> file = new ArrayList<>(); // All of the data in the file
		while (sc.hasNextLine())
			file.add(sc.nextLine()); // Populate the ArrayList, 'file'
		for (String line : file) { // For every single line in the file
			String[] tokens = line.split("\\s+"); // Split up the words in said line and save them in 'tokens'
			int index = 0; // For keeping track of current line in file
			for (String token : tokens) { // For all tokens in the line
				switch (token) {
				case "plat":
					addPlatform(new Platform(new Texture(tokens[++index]), Float.parseFloat(tokens[++index]),
							Float.parseFloat(tokens[++index]), Float.parseFloat(tokens[++index]),
							Float.parseFloat(tokens[++index]))); // Add a platform using data from the file
					break;
				}
			}
		}

		sc.close();
	}
	
	final int scale = 48;
	
	public Level(BufferedImage b, Screen s) {
		this.screen = s;
		
		int[][] data = new int[b.getWidth()][b.getHeight()];
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				data[i][j] = b.getRGB(i, j);			// Populate data array
			}
		}
		
		for (int i = 0; i < data.length; i++) {
			for(int j = 0; j < data[i].length; j++) {
				System.out.print(data[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println("test");
		
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				switch(data[i][j]) {
				case(-1):
//					addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/wall.png"), j*scale,-i*scale,scale,scale, false, false, false, false));
					break;
				case(-1237980):
					addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/ground.png"), j*scale,-i*scale,scale,scale, true, true, true, false));
					break;
				case(-14503604):
					addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/rightplat.png"), j*scale, -i*scale, scale, scale));
					break;
				case(-3620889):
					addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/topright.png"), j*scale, -i*scale, scale, scale, true, false, true, false));
					break;
				case(-16744416):
					addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/topleft.png"), j*scale,-i*scale,scale,scale, true, true, false, false));
					break;
				case(-16744384):
					addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/leftplat.png"), j*scale, -i*scale, scale, scale));
					break;	
				case(-32985):
					addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/normalplat.png"), j*scale, -i*scale, scale, scale));
					break;
				case(-16760768):
					addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/bottomleft.png"), j*scale, -i*scale, scale, scale, false, true, false, false));
					break;
				case(-4856291):
					addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/bottomright.png"), j*scale, -i*scale, scale, scale, false, false, true, true));
					break;
				case(-3584):
					addPlatform(new Platform(Platform.GRASS, j*scale, -i*scale, scale, scale, j, i));
					break;
				case(-4621737):
					addPlatform(new Platform(Platform.DIRT, j*scale, -i*scale, scale, scale, j, i));
					break;
				case(-16735512):
					addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/wall.png"), j*scale, -i*scale, scale, scale, true, true, true, true));
					break;
				case(-7864299):
				//	addPlatform(new Platform(new Texture("assets/backgroundtile.png"), j*scale,-i*scale,scale,scale, false, false, false, false));
					addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/spike.png"), j*scale, -i*scale, scale, scale, true, false, false, false, true));
					break;
				case(-16777216):
				//	addPlatform(new Platform(new Texture("assets/backgroundtile.png"), j*scale,-i*scale,scale,scale, false, false, false, false));
					addSpawnpoint(new Spawnpoint(j*scale,-i*scale, s));
					
					break;
				case(-16760431):
					addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/jump.png"), j*scale, -i*scale, scale*2, scale*2, false, false, false, false, false, false));
					break;

				case(-16479106):
					addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/left.png"), j*scale, -i*scale, scale*2, scale*2, false, false, false, false, false, false));
					break;

				case(-8025339):
					addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/shoot.png"), j*scale, -i*scale, scale*2, scale*2, false, false, false, false, false, false));
					break;

				case(-16419701):
					addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/right.png"), j*scale, -i*scale, scale*2, scale*2, false, false, false, false, false, false));
					break;

				case(-6837608):
					addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/finish.png"), j*scale, -i*scale, scale, scale, false, false, false, false, false, true));
					break;
				case(-6075996):
					EnemySpawner es = new EnemySpawner();
					es.addEnemy(new TrashMonster(Behavior.EDGE_TO_EDGE, 1f, new Texture("assets/Monsters/Trash Monster/trashmonster.png"), es.enemies, this), j*scale, -i*scale);
					es.enemies.get(0).setSize(scale * 1.5f, scale / (42f/55f) * 1.5f);
					addSpawner(es);
					es.debugInfo();
					System.out.println();
				case(-16760832):
					EnemySpawner es2 = new EnemySpawner();
					es2.addEnemy(new TrashCanMonster(Behavior.EDGE_TO_EDGE, 1f, new Texture("assets/Monsters/Trash Monster/trashmonster.png"), es2.enemies, this), j*scale, -i*scale);
					es2.enemies.get(0).setSize(scale * 1.5f, scale / (42f/55f) * 1.5f);
					addSpawner(es2);
					es2.debugInfo();
					System.out.println();
				case(-13640667):
					
				}
			}
		}
		
		for (Platform p : platforms) {
			p.updateTexture(platforms, data);
		}
	}
	/**
	 * @param spawnpoint The spawnpoint that the player will be initialized
	 */
	public void addSpawnpoint(Spawnpoint spawnpoint) {
		this.sp = spawnpoint;
		
		sp.getPlayer().deathThread = new Thread() {
			@SuppressWarnings("static-access")
			@Override
			public void run() {
				try {
					sp.getPlayer().die();
					sp.getPlayer().left = sp.getPlayer().right = false;
					sp.getPlayer().dy = 0;
					sp.getPlayer().jumps = 4;
					this.sleep(3000);
					Main.main.gameScreen.hb.health = 3;
					Main.main.gameScreen.hb.bark = 2;
					Main.main.deathScreen.toResetTo = screen;
					Main.main.setScreen(Main.main.deathScreen);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
	}
	
	public void addSpawner(EnemySpawner spawner) {
		this.es.add(spawner);
	}
	
	/**
	 * @return The Spawnpoint of this level
	 */
	public Spawnpoint getSpawnpoint() {
		return this.sp;
	}
	
	/**
	 * @return The ArrayList containing all platforms in this level
	 */
	public ArrayList<Platform> getPlatforms() {
		return platforms;
	}

	public ArrayList<EnemySpawner> getEnemySpawners() {
		return es;
	}
	public void setEs(ArrayList<EnemySpawner> es) {
		this.es = es;
	}
	/**
	 * Adds a platform to the arraylist, platforms
	 * 
	 * @param p The platform that will be added
	 */
	public void addPlatform(Platform p) {
		platforms.add(p);
	}

	/**
	 * Change every platform in the level, including all data, position, etc. into
	 * the platforms of this array
	 * 
	 * @param platforms What Level.platforms will be changed to
	 */
	public void setPlatforms(ArrayList<Platform> platforms) {
		this.platforms = platforms;
	}

	public ArrayList<ItemDrop> getItemDrops() {
		return itemDrops;
	}
	
	public void setItemDrops(ArrayList<ItemDrop> itemDrops) {
		this.itemDrops = itemDrops;
	}
	
	/**
	 * Draw this level
	 * 
	 * @param b The batch that also renders everything else in the screen
	 */
	public void render(Batch b, HealthBar hb, OrthographicCamera c) {
		for (Platform plat : platforms) {
			plat.draw(b);
		}
		
		for (EnemySpawner e : es) {
			e.update(hb, this);
			e.draw(b, c);
		}
		
		for (ItemDrop id : itemDrops) {
			id.update(this, InventoryBar.i, this.sp.getPlayer());
			id.render(b);
		}
		
		for (int i = 0; i < itemDrops.size(); i++) {
			if (itemDrops.get(i).remove) {
				itemDrops.remove(i);
			}
		}
	}
}