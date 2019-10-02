package com.cognitivethought.level;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.cognitivethought.entity.enemy.Behavior;
import com.cognitivethought.entity.enemy.EnemySpawner;
import com.cognitivethought.entity.enemy.TrashMonster;
import com.cognitivethought.level.parts.Platform;
import com.cognitivethought.ui.HealthBar;

public class Select extends Level {
	
	public Select(String s) throws FileNotFoundException, URISyntaxException {
		super(s);
	}
	
	@Override
	public Select(BufferedImage b) {
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
					addPlatform(new Platform(new Texture("assets/Tilesets/Development Tileset/ground.png"), j*scale,-i*scale,scale,scale, true, true, true, true));
					break;
				case(-14503604):
					addPlatform(new Platform(new Texture("assets/Tilesets/Development Tileset/rightplat.png"), j*scale, -i*scale, scale, scale));
					break;
				case(-3620889):
					addPlatform(new Platform(new Texture("assets/Tilesets/Development Tileset/topright.png"), j*scale, -i*scale, scale, scale, true, false, true, false));
					break;
				case(-16744416):
					addPlatform(new Platform(new Texture("assets/Tilesets/Development Tileset/topleft.png"), j*scale,-i*scale,scale,scale, true, true, false, false));
					break;
				case(-16744384):
					addPlatform(new Platform(new Texture("assets/Tilesets/Development Tileset/leftplat.png"), j*scale, -i*scale, scale, scale));
					break;	
				case(-32985):
					addPlatform(new Platform(new Texture("assets/Tilesets/Development Tileset/normalplat.png"), j*scale, -i*scale, scale, scale));
					break;
				case(-16760768):
					addPlatform(new Platform(new Texture("assets/Tilesets/Development Tileset/bottomleft.png"), j*scale, -i*scale, scale, scale, false, true, false, false));
					break;
				case(-4856291):
					addPlatform(new Platform(new Texture("assets/Tilesets/Development Tileset/bottomright.png"), j*scale, -i*scale, scale, scale, false, false, true, true));
					break;
				case(-3584):
					addPlatform(new Platform(new Texture("assets/Tilesets/Development Tileset/filledtopplat.png"), j*scale, -i*scale, scale, scale));
					break;
				case(-4621737):
					addPlatform(new Platform(new Texture("assets/Tilesets/Development Tileset/filledplat.png"), j*scale, -i*scale, scale, scale, true, false, false, true));
					break;
				case(-16735512):
					addPlatform(new Platform(new Texture("assets/Tilesets/Development Tileset/wall.png"), j*scale, -i*scale, scale, scale, false, true, true, false));
					break;
				case(-7864299):
				//	addPlatform(new Platform(new Texture("assets/backgroundtile.png"), j*scale,-i*scale,scale,scale, false, false, false, false));
					addPlatform(new Platform(new Texture("assets/Tilesets/Development Tileset/spike.png"), j*scale, -i*scale, scale, scale, true, false, false, false, true));
					break;
				case(-16777216):
				//	addPlatform(new Platform(new Texture("assets/backgroundtile.png"), j*scale,-i*scale,scale,scale, false, false, false, false));
					addSpawnpoint(new Spawnpoint(j*scale,-i*scale));
					break;
				case(-6075996):
					EnemySpawner es = new EnemySpawner();
					es.addEnemy(new TrashMonster(Behavior.EDGE_TO_EDGE, 1f, new Texture("assets/Monsters/Trash Monster/trashmonster.png")), j*scale, -i*scale);
					es.enemies.get(0).setSize(scale * 1.5f, scale / (42f/55f) * 1.5f);
					addSpawner(es);
				case(-65408):
					EnemySpawner es2 = new EnemySpawner();
					es2.addEnemy(new TrashMonster(Behavior.WALL_TO_WALL, 1f, new Texture("assets/Monsters/Trash Monster/trashmonster.png")), j*scale, -i*scale);
					es2.enemies.get(0).setSize(scale * 1.5f, scale / (42f/55f) * 1.5f);
					addSpawner(es2);
				}
			}
		}
		
	}
	
	public void render(Batch b, HealthBar hb, OrthographicCamera c) {
		super.render(b, hb, c);
	}
}