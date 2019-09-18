package com.cognitivethought.level;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.cognitivethought.level.parts.Platform;

public class Level {

	// All the platforms in the level
	ArrayList<Platform> platforms = new ArrayList<Platform>();
	
	Spawnpoint sp;
	
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
	public Level(String s) throws FileNotFoundException, URISyntaxException {
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
	
	final int scale = 32;
	
	public Level(BufferedImage b) {
		int[][] data = new int[b.getHeight()][b.getWidth()];
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				data[i][j] = b.getRGB(i, j);			// Populate data array
			}
		}
		
		for (int i = 0; i < data.length; i++) {
			for(int j = 0; j < data[i].length; j++) {
				System.out.print(data[j][i] + " ");
			}
			System.out.println();
		}
		
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				switch(data[j][i]) {
				case(-1):
			//		addPlatform(new Platform(new Texture("assets/backgroundtile.png"), j*scale,-i*scale,scale,scale, false, false, false, false));
					break;
				case(-1237980):
					addPlatform(new Platform(new Texture("assets/ground.png"), j*scale,-i*scale,scale,scale, true, true, true, true));
					break;
				case(-14503604):
					addPlatform(new Platform(new Texture("assets/rightplat.png"), j*scale, -i*scale, scale, scale));
					break;
				case(-3620889):
					addPlatform(new Platform(new Texture("assets/topright.png"), j*scale, -i*scale, scale, scale, true, false, true, false));
					break;
				case(-16744416):
					addPlatform(new Platform(new Texture("assets/topleft.png"), j*scale,-i*scale,scale,scale, true, true, false, false));
					break;
				case(-16744384):
					addPlatform(new Platform(new Texture("assets/leftplat.png"), j*scale, -i*scale, scale, scale));
					break;	
				case(-32985):
					addPlatform(new Platform(new Texture("assets/normalplat.png"), j*scale, -i*scale, scale, scale));
					break;
				case(-16760768):
					addPlatform(new Platform(new Texture("assets/bottomleft.png"), j*scale, -i*scale, scale, scale, false, true, false, false));
					break;
				case(-4856291):
					addPlatform(new Platform(new Texture("assets/bottomright.png"), j*scale, -i*scale, scale, scale, false, false, true, true));
					break;
				case(-3584):
					addPlatform(new Platform(new Texture("assets/filledtopplat.png"), j*scale, -i*scale, scale, scale));
					break;
				case(-4621737):
					addPlatform(new Platform(new Texture("assets/filledplat.png"), j*scale, -i*scale, scale, scale, true, false, false, true));
					break;
				case(-16735512):
					addPlatform(new Platform(new Texture("assets/wall.png"), j*scale, -i*scale, scale, scale, false, true, true, false));
					break;
				case(-7864299):
				//	addPlatform(new Platform(new Texture("assets/backgroundtile.png"), j*scale,-i*scale,scale,scale, false, false, false, false));
					addPlatform(new Platform(new Texture("assets/spike.png"), j*scale, -i*scale, scale, scale, true, false, false, false));
					break;
				case(-16777216):
				//	addPlatform(new Platform(new Texture("assets/backgroundtile.png"), j*scale,-i*scale,scale,scale, false, false, false, false));
					addSpawnpoint(new Spawnpoint(j*scale,-i*scale));
					break;
				}
			}
		}
	}
	/**
	 * @param spawnpoint The spawnpoint that the player will be initialized
	 */
	public void addSpawnpoint(Spawnpoint spawnpoint) {
		this.sp = spawnpoint;
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

	/**
	 * Draw this level
	 * 
	 * @param b The batch that also renders everything else in the screen
	 */
	public void render(Batch b) {
		for (Platform plat : platforms) {
			plat.draw(b);
		}
	}
}