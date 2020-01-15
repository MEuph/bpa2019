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
import com.badlogic.gdx.math.Rectangle;
import com.cognitivethought.entity.ItemDrop;
import com.cognitivethought.entity.enemy.Axel;
import com.cognitivethought.entity.enemy.Behavior;
import com.cognitivethought.entity.enemy.EnemySpawner;
import com.cognitivethought.entity.enemy.HumanEnemy;
import com.cognitivethought.entity.enemy.TrashCanMonster;
import com.cognitivethought.entity.enemy.TrashMonster;
import com.cognitivethought.entity.enemy.Tyrone;
import com.cognitivethought.gui.TextBubble;
import com.cognitivethought.inventory.InventoryBar;
import com.cognitivethought.level.parts.Platform;
import com.cognitivethought.main.Main;
import com.cognitivethought.main.desktop.DesktopLauncher;
import com.cognitivethought.screens.LevelSelectScreen;
import com.cognitivethought.ui.HealthBar;

/**
 * Where the enemies, tiles, and items are stored
 */
public class Level {

	// All the platforms in the level
	ArrayList<Platform> platforms = new ArrayList<Platform>();
	ArrayList<EnemySpawner> es = new ArrayList<>();
	ArrayList<ItemDrop> itemDrops = new ArrayList<ItemDrop>();
	ArrayList<TextBubble> bubbles = new ArrayList<TextBubble>();
	
	Spawnpoint sp;
	public Screen screen;
	
	/**
	 * @param s The path of the level file that will be parsed and loaded into this
	 *          level
	 * @throws FileNotFoundException
	 * @throws URISyntaxException
	 * 
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
					if (!(LevelSelectScreen.levelNumber == 4)) {
						addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/ground.png"), j*scale,-i*scale,scale,scale, true, true, true, false));
					} else {
						addPlatform(new Platform(Platform.ROAD, j*scale, -i*scale, scale, scale, j, i));
					}	
					break;
				case(-14503604):
					if (!(LevelSelectScreen.levelNumber == 4)) {
						addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/rightplat.png"), j*scale, -i*scale, scale, scale));
					} else {
						addPlatform(new Platform(new Texture("assets/Tilesets/City Tileset/rightbeam.png"), j*scale, -i*scale, scale, scale, j, i));
					}
					break;
				case(-3620889):
					if (!(LevelSelectScreen.levelNumber == 4)) {
						addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/topright.png"), j*scale, -i*scale, scale, scale, true, false, true, false));
					} else {
						addPlatform(new Platform(Platform.ROAD, j*scale, -i*scale, scale, scale, j, i));
					}
					break;
				case(-16744416):
					if (!(LevelSelectScreen.levelNumber == 4)) {
						addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/topleft.png"), j*scale,-i*scale,scale,scale, true, true, false, false));
					} else {
						addPlatform(new Platform(Platform.ROAD, j*scale, -i*scale, scale, scale, j, i));
					}
					break;
				case(-16744384):
					if (!(LevelSelectScreen.levelNumber == 4)) {
						addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/leftplat.png"), j*scale, -i*scale, scale, scale));
					} else {
						addPlatform(new Platform(new Texture("assets/Tilesets/City Tileset/leftbeam.png"), j*scale, -i*scale, scale, scale, j, i));
					}
					break;	
				case(-32985):
					if (!(LevelSelectScreen.levelNumber == 4)) {
						addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/normalplat.png"), j*scale, -i*scale, scale, scale));
					}	else {
						addPlatform(new Platform(new Texture("assets/Tilesets/City Tileset/middlebeam.png"), j*scale, -i*scale, scale, scale, j, i));
					}
					break;
				case(-16760768):
					if (!(LevelSelectScreen.levelNumber == 4)) {
						addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/bottomleft.png"), j*scale, -i*scale, scale, scale, false, true, false, false));
					} else {
						addPlatform(new Platform(Platform.CONC, j*scale, -i*scale, scale, scale, j, i));
					}
					break;
				case(-4856291):
					if (!(LevelSelectScreen.levelNumber == 4)) {
						addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/bottomright.png"), j*scale, -i*scale, scale, scale, false, false, true, true));
					} else {
						addPlatform(new Platform(Platform.CONC, j*scale, -i*scale, scale, scale, j, i));
					}
					break;
				case(-3584):
					if (!(LevelSelectScreen.levelNumber == 4)) {
						addPlatform(new Platform(Platform.GRASS, j*scale, -i*scale, scale, scale, j, i));
					} else {
						addPlatform(new Platform(Platform.ROAD, j*scale, -i*scale, scale, scale, j, i));
					}
					break;
				case(-4621737):
					if (!(LevelSelectScreen.levelNumber == 4)) {
						addPlatform(new Platform(Platform.DIRT, j*scale, -i*scale, scale, scale, j, i));
					} else {
						addPlatform(new Platform(Platform.CONC, j*scale, -i*scale, scale, scale, j, i));
					}
					break;
				case(-16735512):
					
					addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/wall.png"), j*scale, -i*scale, scale, scale, true, true, true, true));
					break;
				case(-7864299):
					if (!(LevelSelectScreen.levelNumber == 4) && !(LevelSelectScreen.levelNumber == 1)) {
						addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/spike.png"), j*scale, -i*scale, scale, scale, true, false, false, false, true));
					} else if (LevelSelectScreen.levelNumber == 4) {
						addPlatform(new Platform(new Texture("assets/Tilesets/City Tileset/cone.png"), j*scale, -i*scale, scale, scale, true, false, false, false, true));
					} else {
						addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/fire.png"), j*scale, -i*scale, scale, scale, true, false, false, false, true));
					}
					break;
				case(-16777216):
				//	addPlatform(new Platform(new Texture("assets/backgroundtile.png"), j*scale,-i*scale,scale,scale, false, false, false, false));
					addSpawnpoint(new Spawnpoint(j*scale,-i*scale, s));
					
					break;
				case(-12351296): //jump movement explanation
					addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/jump.png"), j*scale, -i*scale, scale*2, scale*2, false, false, false, false, false, false));
					break;

				case(-4511270): //left movement explanation
					addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/left.png"), j*scale, -i*scale, scale*2, scale*2, false, false, false, false, false, false));
					break;

				case(-9029939): //shoot explanation
					addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/shoot.png"), j*scale, -i*scale, scale*2, scale*2, false, false, false, false, false, false));
					break;
				case(-10966182): //melee explanation
					addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/melee.png"), j*scale, -i*scale, scale*2, scale*2, false, false, false, false, false, false));
					break;
				case(-3222991): //right movement explanation
					addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/right.png"), j*scale, -i*scale, scale*2, scale*2, false, false, false, false, false, false));
					break;
				case(-5531560): //escape = pause explanation
					addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/escpause.png"), j*scale, -i*scale, scale*2, scale*2, false, false, false, false, false, false));
					break;
				case(-14099560): //spike explanation
					addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/spikeexp.png"), j*scale, -i*scale, scale*2, scale*2, false, false, false, false, false, false));
					break;
				case(-10778712): //trampoline explanation
					addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/trampexp.png"), j*scale, -i*scale, scale*2, scale*2, false, false, false, false, false, false));
					break;
				case(-13323057): //more help explanation
					addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/morehelp.png"), j*scale, -i*scale, scale*2, scale*2, false, false, false, false, false, false));
					break;

				case(-6837608):
					addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/finish.png"), j*scale, -i*scale, scale, scale, false, false, false, false, false, true));
					break;
				case(-6075996):
					EnemySpawner es = new EnemySpawner();
					if (!(LevelSelectScreen.levelNumber == 4)) {
						es.addEnemy(new TrashMonster(Behavior.EDGE_TO_EDGE, 1f, new Texture("assets/Monsters/Trash Monster/trashmonster.png"), es.enemies, this), j*scale, -i*scale);
					} else {
						es.addEnemy(new HumanEnemy(Behavior.EDGE_TO_EDGE, 1f, new Texture("assets/Monsters/Trash Monster/trashmonster.png"), es.enemies, this), j*scale, -i*scale);
					}
					es.enemies.get(0).setSize(scale * 1.5f, scale / (42f/55f) * 1.5f);
					addSpawner(es);
					es.debugInfo();
					System.out.println();
					break;
				case(-20791):
					
					EnemySpawner es2 = new EnemySpawner();
					if (!(LevelSelectScreen.levelNumber == 4)) {
						es2.addEnemy(new TrashCanMonster(Behavior.EDGE_TO_EDGE, 1f, new Texture("assets/Monsters/Trash Monster/trashmonster.png"), es2.enemies, this), j*scale, -i*scale);
					} else {
						es2.addEnemy(new HumanEnemy(Behavior.EDGE_TO_EDGE, 1f, new Texture("assets/Monsters/Trash Monster/trashmonster.png"), es2.enemies, this), j*scale, -i*scale);
					}
					es2.enemies.get(0).setSize(scale * 1.5f, scale / (42f/55f) * 1.5f);
					addSpawner(es2);
					es2.debugInfo();
					System.out.println();
					break;
				case(-9072273):
					EnemySpawner es3 = new EnemySpawner();
					if(!(LevelSelectScreen.levelNumber == 4)) {
						
						es3.addEnemy(new Axel(Behavior.EDGE_TO_EDGE, 1f, 3f, new Texture("assets/Monsters/Trash Monster/trashmonster.png"), es3.enemies, this), j*scale, -i*scale);
					}
					else {
						es3.addEnemy(new Tyrone(Behavior.EDGE_TO_EDGE, 1f, 3f, new Texture("assets/Monsters/Trash Monster/trashmonster.png"), es3.enemies, this), j*scale, -i*scale);
					}
					es3.enemies.get(0).setSize(scale * 1.5f, scale / (42f/55f) * 1.5f);
					addSpawner(es3);
					es3.debugInfo();
					System.out.println();
					break;
				case(-7649845):
					addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/wall.png"), j*scale, -i*scale, scale*2, scale*2, false, false, false, false, false, false, true));
					break;
				case(-12843677):
					addPlatform(new Platform(new Texture("assets/Tilesets/Tutorial Tileset/trampoline.png"), j*scale, -i*scale, scale, scale, true, true, true, false, false, false, true, true));
					break;
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
					DesktopLauncher.log();
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
	public void render(Batch b, HealthBar hb, OrthographicCamera c, boolean paused) {
		for (int i = 0; i < itemDrops.size(); i++) {
			if (itemDrops.get(i).remove && !paused) {
				itemDrops.remove(i);
			}
		}

		float cx = c.position.x - (c.viewportWidth / 2);
		float cy = c.position.y - (c.viewportHeight / 2);
		Rectangle r = new Rectangle(cx, cy, c.viewportWidth, c.viewportHeight);
		for (Platform plat : platforms) {
			if (plat.getBoundingRectangle().overlaps(r)) {
				plat.draw(b);
			}
		}
		
		for (EnemySpawner e : es) {
			e.draw(b, c, paused);
			if (!InventoryBar.grid.shown && !paused) e.update(hb, this);
		}
		
		for (int i = 0; i < itemDrops.size(); i++) {
			itemDrops.get(i).render(b);
			if (!InventoryBar.grid.shown && !paused) itemDrops.get(i).update(this, InventoryBar.i, this.sp.getPlayer());
		}
		
	}
}