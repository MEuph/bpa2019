package com.cognitivethought.level;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.cognitivethought.entity.Player;

public class Spawnpoint {

	float x, y;
	
	private Player player;
	
	public Spawnpoint(float x, float y, Screen s) {
		player = new Player(new Texture("assets/Player/base.png"), s); // Create Player
		player.setPosition(x, y);
		player.setSize(36f * 2f, 52f * 2f);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public void reset() {
		player.setX(this.x);
		player.setY(this.y);
	}
}