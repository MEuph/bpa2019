package com.cognitivethought.entity.enemy;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.cognitivethought.level.Level;
import com.cognitivethought.ui.HealthBar;

public class EnemySpawner {
	
	public ArrayList<Enemy> enemies;
	
	public EnemySpawner() {
		enemies = new ArrayList<Enemy>();
	}
	
	public void addEnemy(Enemy e, float x, float y) {
		e.setX(x);
		e.setY(y);
		enemies.add(e);
	}
	
	public void update(HealthBar hb, Level l) {
		for (Enemy e : enemies) {
			e.update(hb, l);
		}
	}
	
	public void draw(Batch b) {
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(b);
		}
	}
	
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