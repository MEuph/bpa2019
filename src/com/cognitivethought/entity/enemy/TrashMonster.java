package com.cognitivethought.entity.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.cognitivethought.level.Level;
import com.cognitivethought.level.parts.Platform;
import com.cognitivethought.ui.HealthBar;

public class TrashMonster extends Enemy {
	
	final int attackCol = 3, attackRow = 3;
	float attackTime;
	
	Animation<TextureRegion> attackAnimation;
	Texture attackSheet;
	
	Animation<TextureRegion> deathAnimation;
	Texture deathSheet;
	
	Texture idle;
	
	private final float g = 0.198f;	 	// Gravitational constant
	
	Platform toBeOn;					// The platform the monster should be on
	
	int health;							// The health this monster has
	
	boolean movingLeft, movingRight;	// Whether or not the monster is moving right or left
	boolean facingRight;				// Whether the monster is facing right or not
	boolean attacking = false;
	
	float pauseTimer;					// How long to pause in between movements
	float dx, dy;						// The velocity of the monster
	
	/**
	 * The first monster the player will encounter. A heaping mass of garbage that is thankfully
	 *  - for the sake of the player's nose - contained in a trash can.
	 * @param damageValue
	 * 		How much damage this particular trash monster will do
	 * @param texture
	 * 		The appearance of this particular trash monster
	 */
	public TrashMonster(Behavior b, float damageValue, Texture texture) {
		super(b, Behavior.MELEE, damageValue, texture);
		this.speed = 2f - (float)Math.random();	// Default speed to 1f
		this.dx = -speed;	// Default movement to the left
		this.idle = texture;
		createAnimations();
	}

	void createAnimations() {
		attackSheet = new Texture("assets/Monsters/Trash Monster/attack.png");
		
		TextureRegion[][] tmp = TextureRegion.split(attackSheet, attackSheet.getWidth() / attackCol, 
				attackSheet.getHeight() / attackRow);
		
		TextureRegion[] attackFrames = new TextureRegion[attackCol * attackRow];
		int x = 0;
		for (int i = 0; i < attackRow; i++) {
			for (int j = 0; j < attackCol; j++) {
				attackFrames[x++] = tmp[i][j];
			}
		}
		
		attackAnimation = new Animation<TextureRegion>(0.09f, attackFrames);
		
		attackTime = 0f;
	}
	
	/**
	 * @see com.cognitivethought.entity.enemy.Enemy.move
	 */
	@Override
	void move(Level l) {
		// If the monster hits either the left bound or the right bound, just flip it
		// Same collision detection code as the player
		for (Platform plat : l.getPlatforms()) {
			if (new Rectangle(plat.getX()+1f, plat.getY()+1f, plat.getWidth()-2f, plat.getHeight()-2f).overlaps(getBoundingRectangle()) && dy < 0 && getY() >= plat.getY() + (plat.getHeight() / 2) + dy && plat.collideTop) {
				dy = 0;
				setY(plat.getY() + plat.getHeight() - 2f); // Reset y position to the top of the platform
			}
			
			if (new Rectangle(plat.getX()+1f, plat.getY()+1f, plat.getWidth()-2f, plat.getHeight()-2f).overlaps(getBoundingRectangle()) && dy > 0 && getY() + getHeight() >= plat.getY() + plat.getHeight()  && plat.collideBottom) {
				System.out.println(getY() + getHeight() + " " + plat.getY());
				dy = 0;
				setY(plat.getY() - getHeight() + plat.getHeight() + 2f); // Reset y position to the bottom of the platform
				break;
			}
			
			Rectangle leftOfPlatform = new Rectangle(plat.getX(), plat.getY(), 2f, plat.getHeight());
			if (leftOfPlatform.overlaps(getBoundingRectangle()) && dx > 0 && getX() + getWidth() + dx >= leftOfPlatform.getX() && plat.collideLeft && !(getY()>(plat.getY()+plat.getHeight())-4)) {
				dx *= -1;
				facingRight = true;
				this.flip(true, false);
			}
			
			Rectangle rightOfPlatform = new Rectangle(plat.getX()+plat.getWidth()-2f, plat.getY(), 2f, plat.getHeight());
			if (rightOfPlatform.overlaps(getBoundingRectangle()) && dx < 0 && getX() <= plat.getX() + plat.getWidth() && plat.collideRight && !(getY()>(plat.getY()+plat.getHeight())-4)) {
				dx *= -1;
				facingRight = false;
				this.flip(true, false);
			}
		}
	}
	
	/**
	 * Update the monster
	 */
	@Override
	public void update(HealthBar hb, Level l) {
		if (dy > -15f)
			dy -= g; // Simulate gravity constantly, with terminal velocity set to 15f
		
		if (attacking) {
			attackTimer-=1f;
		}
		
		if (attackTimer <= 0f) {
			attacking = false;
		}
		
		move(l); // Do movement code
		
		translateY(dy); // Do translations
		if (!attacking) translateX(dx);
		
		attack(hb, l); // Do attacking
	}

	/**
	 * Process monster attacking
	 */
	@Override
	void attack(HealthBar hb, Level l) {
		attackRange = 1f;
		// if the player is in range, the monster can attack
		boolean canAttack =
			new Rectangle(getX() - attackRange, getY() - attackRange, getWidth() + (attackRange * 2), getHeight() + 
			(attackRange * 2)).overlaps(l.getSpawnpoint().getPlayer().getBoundingRectangle());
		
		// attack if the monster can attack
		if (canAttack) {
			attacking = true;
			if (!l.getSpawnpoint().getPlayer().flashing) {
				if (hb.bark >= 0) {
					hb.bark-=damageValue;
					attackTimer=50f;
				} else {
					hb.health-=damageValue;
					attackTimer=50f;
				}
				l.getSpawnpoint().getPlayer().flashing = true;
				l.getSpawnpoint().getPlayer().flashTimer = 500f;
			}
		}
	}
	
	float prevDx = 0f;
	
	/**
	 * Draw the monster
	 */
	@Override
	public void draw(Batch batch) {
		if (attacking) {
			attackTime+=Gdx.graphics.getDeltaTime();
//			TextureRegion currentFrame = attackAnimation.getKeyFrame(attackTime, true);
//			System.out.println(facingRight);
//			currentFrame.flip(currentFrame.isFlipX() != this.isFlipX() ? this.isFlipX() : !this.isFlipX(), false);
//			this.setFlip(this.isFlipX(), false);
//			batch.draw(currentFrame, facingRight ? getX() + getWidth() : getX(), getY(), facingRight ? -getWidth() : getWidth(), getHeight());
			setTexture(idle);
			super.draw(batch);
			if (attackTime > 1f) {
				attacking = false;
			}
		} else {
			attackTime = 0f;
			setTexture(idle);
			//this.flip(facingRight, false);
			super.draw(batch);
		}
	}
}