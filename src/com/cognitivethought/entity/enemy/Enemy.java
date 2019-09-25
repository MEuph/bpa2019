package com.cognitivethought.entity.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.cognitivethought.level.Level;
import com.cognitivethought.ui.HealthBar;

public abstract class Enemy extends Sprite {
	
	protected float attackTimer;
	protected float damageValue;
	protected float detectionRange;
	protected float attackRange;
	protected float speed;
	
	protected Behavior movementBehavior;
	protected Behavior attackBehavior;
	
	public Enemy(Behavior movementBehavior, Behavior attackBehavior, float damageValue, Texture texture) {
		super(texture);
		this.movementBehavior = movementBehavior;
		this.attackBehavior = attackBehavior;
		this.damageValue = damageValue;
		super.setSize(getTexture().getWidth(), getTexture().getHeight());
	}
	
	abstract void move(Level l);
	public abstract void update(HealthBar hb, Level l);
	abstract void attack(HealthBar hb, Level l);
}

enum Behavior {
	EDGE_TO_EDGE,
	PLAT_TO_PLAT,
	MELEE,
	RANGED
}