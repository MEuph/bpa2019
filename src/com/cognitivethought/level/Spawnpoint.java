package com.cognitivethought.level;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.cognitivethought.entity.TreePlayer;

/**
 * The point where the player spawns
 */
public class Spawnpoint {

	float x, y;
	
	private TreePlayer player;
	
	public Spawnpoint(float x, float y, Screen s) {
		player = new TreePlayer(new Texture("assets/Player/base.png"), s); // Create Player
		player.setPosition(x, y);
		player.setSize(36f * 2f, 52f * 2f);
	}

	public TreePlayer getPlayer() {
		return player;
	}

	public void setPlayer(TreePlayer player) {
		this.player = player;
	}
	
	public void reset() {
		player.setX(this.x);
		player.setY(this.y);
	}
}