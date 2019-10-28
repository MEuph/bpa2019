package com.cognitivethought.entity.enemy;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.cognitivethought.level.Level;
import com.cognitivethought.ui.HealthBar;

public class EnemySpawner {
	
	public ArrayList<Enemy> enemies; // The Array of enemies that this spawner can pull from
	
	/**
	 * A "tile" that allows enemies to be spawned on it
	 */
	public EnemySpawner() {
		enemies = new ArrayList<Enemy>(); // Initialize the array
	}
	
	/**
	 * Adds an enemy to the spawner.
	 * @param e
	 * 		The enemy to be added
	 * @param x
	 * 		The x position of the new enemy
	 * @param y
	 * 		The y position of the new enemy
	 */
	public void addEnemy(Enemy e, float x, float y) {
		e.setX(x);		// Set the position of the enemy
		e.setY(y);		
		enemies.add(e); // Add the new enemy to the array
	}
	
	/**
	 * @param hb
	 * 		The player's health bar
	 * @param l
	 * 		The level the enemy is in
	 */
	public void update(HealthBar hb, Level l) {
		for (Enemy e : enemies) {
			e.update(hb, l);
			
			if (e.getHealth() <= 0f) {
				if (e.deathThread.isAlive()) {
					if (e.deathThreadTime >= 2000) {
						enemies.remove(e);
					}
				} else {
					try {
						e.deathThread.start();
					} catch (IllegalThreadStateException itse) {
						itse.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * Draws the enemies
	 * @param b
	 * 		Batch that can do Batch-rendering
	 */
	public void draw(Batch b) {
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(b);
		}
	}
	
	/**
	 * Shows various information about the enemies
	 */
	public void debugInfo() {
		for (Enemy e : enemies) {
			System.out.println();
			
			System.out.println(e.getX());
			System.out.println(e.getY());
			System.out.println(e.getWidth());
			System.out.println(e.getHeight());

			System.out.println(e.getTexture().getWidth());
			System.out.println(e.getTexture().getHeight());
		}
	}
}