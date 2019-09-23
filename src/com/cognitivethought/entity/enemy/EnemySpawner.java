package com.cognitivethought.entity.enemy;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.cognitivethought.level.Level;

public class EnemySpawner {
	
	public ArrayList<Enemy> enemies;
	
	public EnemySpawner() {
		
	}
	
	public void addEnemy(Enemy e) {
		enemies.add(e);
	}
	
	public void update(Level l) {
		for (Enemy e : enemies) {
			e.update(l);
		}
	}
	
	public void render(Batch b) {
		for (Enemy e : enemies) {
			e.render(b);
		}
	}
}