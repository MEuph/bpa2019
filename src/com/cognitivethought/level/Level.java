package com.cognitivethought.level;

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