package com.cognitivethought.inventory;

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
import com.cognitivethought.entity.enemy.Behavior;
import com.cognitivethought.entity.enemy.EnemySpawner;
import com.cognitivethought.entity.enemy.TrashMonster;
import com.cognitivethought.level.Level;
import com.cognitivethought.level.Spawnpoint;
import com.cognitivethought.inventory.parts.Tile;
import com.cognitivethought.ui.HealthBar;

public class Inventory {
	// All the platforms in the level
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		ArrayList<EnemySpawner> items = new ArrayList<>();
		
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
		
		
		final int scale = 48;
		
		public Inventory(BufferedImage b, Screen s) {
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
			
			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[i].length; j++) {
					switch(data[i][j]) {
					case(-1237980):
							addPlatform(new Tile(new Texture("/Tilesets/Development Tileset/wall.png.png"), j*scale,-i*scale,scale,scale));
						break;
					
					}
				}
			}
		}
		/**
		 * @param spawnpoint The spawnpoint that the player will be initialized
		 */
		
	
		public void addPlatform(Tile p) {
			tiles.add(p);
		}

		/**
		 * Change every platform in the level, including all data, position, etc. into
		 * the platforms of this array
		 * 
		 * @param platforms What Level.platforms will be changed to
		 */
		public void setPlatforms(ArrayList<Tile> platforms) {
			this.tiles = platforms;
		}

		/**
		 * Draw this level
		 * 
		 * @param b The batch that also renders everything else in the screen
		 */
		public void render(Batch b, HealthBar hb) {
			for (Tile plat : tiles) {
				plat.draw(b);
			}
			
			for (EnemySpawner e : items) {
				e.draw(b);
			}
		}
}