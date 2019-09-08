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
	
	ArrayList<Platform> platforms = new ArrayList<Platform>();
	
	public Level(String s) throws FileNotFoundException, URISyntaxException {
		Scanner sc = new Scanner(new File(Level.class.getResource(s).toURI()));
		ArrayList<String> file = new ArrayList<>();
		while (sc.hasNextLine()) file.add(sc.nextLine());
		for (String line : file) {
			String[] tokens = line.split("\\s+");
			int index = 0;
			for (String token : tokens) {
				switch(token) {
				case "plat":
					addPlatform(new Platform(
							new Texture(tokens[++index]),
							Float.parseFloat(tokens[++index]),
							Float.parseFloat(tokens[++index]),
							Float.parseFloat(tokens[++index]),
							Float.parseFloat(tokens[++index])));
					break;
				}
			}
		}
		
		sc.close();
	}
	
	public ArrayList<Platform> getPlatforms() {
		return platforms;
	}
	
	public void addPlatform(Platform p) {
		platforms.add(p);
	}
	
	public void setPlatforms(ArrayList<Platform> platforms) {
		this.platforms = platforms;
	}
	
	public void render(Batch b) {
		for (Platform p : platforms) {
			p.draw(b);
		}
	}
}