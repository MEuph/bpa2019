package com.cognitivethought.entity.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.cognitivethought.level.Level;

public abstract class Enemy extends Sprite {
	
	protected float attackTimer;
	protected float damageValue;
	protected float detectionRange;
	protected float attackRange;
	protected float speed;
	
	protected Behavior movementBehavior;
	protected Behavior attackBehavior;
	
	protected Texture texture;
	
	public Enemy(Behavior movementBehavior, Behavior attackBehavior, float damageValue, Texture texture) {
		this.movementBehavior = movementBehavior;
		this.attackBehavior = attackBehavior;
		this.damageValue = damageValue;
		this.texture = texture;
	}
	
	abstract void move(Level l);
	abstract void update(Level l);
	abstract void attack(Level l);
	abstract void render(Batch b);
}

enum Behavior {
	EDGE_TO_EDGE,
	PLAT_TO_PLAT,
	MELEE,
	RANGED
}