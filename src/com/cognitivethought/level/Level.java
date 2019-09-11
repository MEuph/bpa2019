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
	
	public Level(BufferedImage b) {
		int[][] data = new int[b.getHeight()][b.getWidth()];
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				data[i][j] = b.getRGB(i, j);			// Populate data array
			}
		}
		
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				switch(data[i][j]) {
				case(0xFFFFFF):
					break;
				case(0xFF0000):
					addPlatform(new Platform(new Texture("/bigplat.png"), i*32,j*32,32,32));
					break;
				case(0x22B14C):
					addPlatform(new Platform(new Texture("/leftplat.png"), i*32, j*32, 32, 32));
					break;
				case(0x008040):
					addPlatform(new Platform(new Texture("/rightplat.png"), i*32, j*32, 32, 32));
					break;	
				case(0xFF7F27):
					addPlatform(new Platform(new Texture("/normalplat.png"), i*32, j*32, 32, 32));
					break;
				case(0x004040):
					addPlatform(new Platform(new Texture("/bottomright.png"), i*32, j*32, 32, 32));
					break;
				case(0xB5E61D):
					addPlatform(new Platform(new Texture("/bottomleft.png"), i*32, j*32, 32, 32));
					break;
				case(0xFFF200):
					addPlatform(new Platform(new Texture("/filledtopplat.png"), i*32, j*32, 32, 32));
					break;
				}
			}
		}
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