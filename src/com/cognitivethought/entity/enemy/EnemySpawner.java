package com.cognitivethought.entity.enemy;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.cognitivethought.level.Level;
import com.cognitivethought.ui.HealthBar;

public class EnemySpawner {
	
	public ArrayList<Enemy> enemies;
	
	public EnemySpawner() {
		enemies = new ArrayList<>();
	}
	
	public void addEnemy(Enemy e) {
		enemies.add(e);
	}
	
	public void update(HealthBar hb, Level l) {
		for (Enemy e : enemies) {
			e.update(hb, l);
		}
	}
	
	public void render(Batch b) {
		for (Enemy e : enemies) {
			b.draw(e, e.getX(), e.getY());
		}
	}
}