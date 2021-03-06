package com.cognitivethought.entity.enemy;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.cognitivethought.level.Level;
import com.cognitivethought.ui.HealthBar;

public abstract class Enemy extends Sprite {
	
	protected float attackTimer;	// How long the enemy has between attacks
	protected float damageValue;	// How hard the enemy hits. IE, how many hearts it takes from the player
	protected float majorDamageValue;	// How hard the enemy hits on the major attack. IE, how many hearts it takes from the player
	protected float detectionRange; // How far the detection range of the enemy is
	protected float attackRange;	// How far away the enemy can attack from
	protected float speed;			// The speed of the enemy
	protected float hurtTimer;
	
	protected Behavior movementBehavior;	// The behavior of movement
	protected Behavior attackBehavior;		// The behavior of attacking
	
	public Thread deathThread;
	public int deathThreadTime;
	public boolean canPlaySound;
	
	/**
	 * The superclass of all enemies
	 * 
	 * @param movementBehavior
	 * @param attackBehavior
	 * @param damageValue
	 * @param texture
	 */
	public Enemy(Behavior movementBehavior, Behavior attackBehavior, float damageValue, Texture texture) {
		super(texture);
		this.movementBehavior = movementBehavior;
		this.attackBehavior = attackBehavior;
		this.damageValue = damageValue;
		super.setSize(getTexture().getWidth(), getTexture().getHeight());
	}
	public Enemy(Behavior movementBehavior, Behavior attackBehavior, float damageValue, float majorDamageValue, Texture texture) {
		super(texture);
		this.movementBehavior = movementBehavior;
		this.attackBehavior = attackBehavior;
		this.damageValue = damageValue;
		this.majorDamageValue = majorDamageValue;
		super.setSize(getTexture().getWidth(), getTexture().getHeight());
	}
	
	public abstract void die();
	public abstract void hurt(int value, boolean byProjectile);
	abstract void move(Level l);						// How the enemy will move
	public abstract void update(HealthBar hb, Level l); // How the enemy will be updated
	abstract void attack(HealthBar hb, Level l);		// How the enemy will attack

	protected abstract float getHealth();

	public abstract void draw(Batch batch, OrthographicCamera c, boolean paused);
}