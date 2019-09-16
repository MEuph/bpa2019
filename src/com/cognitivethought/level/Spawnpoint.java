package com.cognitivethought.level;

import com.badlogic.gdx.graphics.Texture;
import com.cognitivethought.entity.Player;

public class Spawnpoint {

	float x, y;
	
	private Player player;
	
	public Spawnpoint(float x, float y) {
		player = new Player(new Texture("base.png")); // Create Player
		player.setPosition(x, y);
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