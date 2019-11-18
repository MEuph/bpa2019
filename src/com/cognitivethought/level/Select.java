package com.cognitivethought.level;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.cognitivethought.level.parts.Platform;
import com.cognitivethought.ui.HealthBar;

public class Select extends Level {
	
	public Select(String s) throws FileNotFoundException, URISyntaxException {
		super(s);
	}
	
	public Select(BufferedImage b, Screen s) {
		super(b, s);
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
				}
			}
		}
		
	}
	
	public void render(Batch b, HealthBar hb, OrthographicCamera c) {
		super.render(b, hb, c);
	}
}