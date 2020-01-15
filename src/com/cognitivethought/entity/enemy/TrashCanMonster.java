package com.cognitivethought.entity.enemy;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.cognitivethought.entity.ItemDrop;
import com.cognitivethought.inventory.Item;
import com.cognitivethought.level.Level;
import com.cognitivethought.level.parts.Platform;
import com.cognitivethought.main.desktop.DesktopLauncher;
import com.cognitivethought.resources.Resources;
import com.cognitivethought.ui.HealthBar;

public class TrashCanMonster extends Enemy {
	
	final int attackCol = 7, attackRow = 2;
	final int jumpCol = 4, jumpRow = 2;
	final int deathCol = 5, deathRow = 3;
	
	float attackTime;
	float jumpTime;
	float deathTime = 1f;
	
	final float propWidth = 72f+15, propHeight = 94.28571f+15;
	
	boolean deathThreadPaused;
	
	HealthBar hb;
	
	Animation<TextureRegion> attackAnimation;
	Texture attackSheet;
	
	Animation<TextureRegion> deathAnimation;
	Texture deathSheet;
	
	Animation<TextureRegion> jumpAnimation;
	Texture jumpSheet;
	
	Texture idle;
	
	private final float g = 0.198f;	 	// Gravitational constant
	
	Platform toBeOn;					// The platform the monster should be on
	
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
	 * @throws Exception 
	 */
	public TrashCanMonster(Behavior b, float damageValue, Texture texture, ArrayList<Enemy> enemies, Level l) {
		super(b, Behavior.MELEE, damageValue, texture);
		this.speed = 2f - (float)Math.random();	// Default speed to 1f
		this.dx = -speed;	// Default movement to the left
		this.idle = texture;
		
		TrashCanMonster t = this;
		
		hb = new HealthBar(this, 3);
		
		deathThread = new Thread() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					canPlaySound = false;
					die();
					dx = 0;
					System.out.println("DIED!");
					this.sleep(1950);
					int organicMatterToDrop = new Random().nextInt(4) + 1;
					for (int i = 0; i < organicMatterToDrop; i++) {
						ItemDrop om = new ItemDrop(Resources.ORGANIC_MATTER, (int)t.getX() + (int)(t.getWidth() / 2), (int)t.getY() + (int)(t.getHeight() / 2), 40, 40, Item.ORGANIC_MATTER);
						om.dy = 2f;
						om.dx = (float)(Math.random() * (Math.random() <= 0.5f ? -1 : 1) * 2) * (new Random().nextInt(2) + 1);
						l.getItemDrops().add(om);
					}
					ItemDrop coin = new ItemDrop(Resources.COIN, (int)t.getX(), (int)t.getY(), 40, 40, Item.COIN);
					coin.dy = 2f;
					coin.dx = (float)(Math.random() * (Math.random() <= 0.5f ? -1 : 1) * 2);
					l.getItemDrops().add(coin);
					enemies.remove(t);
				} catch (InterruptedException e1) {
					DesktopLauncher.log();
					e1.printStackTrace();
				}
			}
		};

		createAnimations();
	}

	void createAnimations() {
		attackSheet = new Texture("assets/Monsters/Trashcan Monster/attack.png");
		jumpSheet = new Texture("assets/Monsters/Trashcan Monster/move.png");
		deathSheet = new Texture("assets/Monsters/Trashcan Monster/death.png");
		
		TextureRegion[][] tmp = TextureRegion.split(attackSheet, attackSheet.getWidth() / attackCol, 
				attackSheet.getHeight() / attackRow);
		
		TextureRegion[] attackFrames = new TextureRegion[attackCol * attackRow];
		int x = 0;
		for (int i = 0; i < attackRow; i++) {
			for (int j = 0; j < attackCol; j++) {
				attackFrames[x++] = tmp[i][j];
			}
		}
		
		tmp = TextureRegion.split(jumpSheet, jumpSheet.getWidth() / jumpCol, jumpSheet.getHeight() / jumpRow);
		TextureRegion[] jumpFrames = new TextureRegion[jumpCol * jumpRow];
		x = 0;
		for (int i = 0; i < jumpRow; i++) {
			for (int j = 0; j < jumpCol; j++) {
				jumpFrames[x++] = tmp[i][j];
			}
		}
		
		tmp = TextureRegion.split(deathSheet, deathSheet.getWidth() / deathCol, deathSheet.getHeight() / deathRow);
		TextureRegion[] deathFrames = new TextureRegion[deathCol * deathRow];
		x = 0;
		for (int i = 0; i < deathRow; i++) {
			for (int j = 0; j < deathCol; j++) {
				deathFrames[x++] = tmp[i][j];
			}
		}
		
		jumpAnimation = new Animation<TextureRegion>((float)(new Random().nextInt(3) + 6) / 100f, jumpFrames);
		attackAnimation = new Animation<TextureRegion>(0.09f, attackFrames);
		deathAnimation = new Animation<TextureRegion>(2f / (float)(deathRow * deathCol), deathFrames);
		
		attackTime = 0f;
		deathTime = 1f;
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
			if (leftOfPlatform.overlaps(getBoundingRectangle()) && dx > 0 && getX() + getWidth() + dx >= leftOfPlatform.getX() && plat.collidesEnemy && !(getY()>(plat.getY()+plat.getHeight())-4)) {
				dx *= -1;
				facingRight = true;
				this.flip(true, false);
			}
			
			Rectangle rightOfPlatform = new Rectangle(plat.getX()+plat.getWidth()-2f, plat.getY(), 2f, plat.getHeight());
			if (rightOfPlatform.overlaps(getBoundingRectangle()) && dx < 0 && getX() <= plat.getX() + plat.getWidth() && plat.collideRight && !(getY()>(plat.getY()+plat.getHeight())-4)) {
				dx *= -1;
				facingRight = false;
				this.flip(true, false);
			}if (rightOfPlatform.overlaps(getBoundingRectangle()) && dx < 0 && getX() <= plat.getX() + plat.getWidth() && plat.collidesEnemy && !(getY()>(plat.getY()+plat.getHeight())-4)) {
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
		
		if (hurtTimer > 0f) {
			hurtTimer--;
		}
		
		if (attacking) {
			attackTimer-=2f;
		}
		
		facingRight = dx < 0;
		
		if (deathThreadPaused) {
			deathThreadTime++;
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
		//if (l.getSpawnpoint().getPlayer().isAttacking) return;
		
		if (hb.health <= 0f) return;
		
		attackRange = 1f;
		// if the player is in range, the monster can attack
		boolean canAttack =
			new Rectangle(getX() - attackRange, getY() - attackRange, getWidth() + (attackRange * 2), getHeight() + 
			(attackRange * 2)).overlaps(l.getSpawnpoint().getPlayer().getBoundingRectangle());
		
		if (!canAttack) return;
		
//		System.out.println(canAttack);
//		System.out.println(deathTime);
//		System.out.println();
		
		// attack if the monster can attack
		if (canAttack && deathTime != 0f && !(l.getSpawnpoint().getPlayer().attackTime > 0f && l.getSpawnpoint().getPlayer().attackTime <= l.getSpawnpoint().getPlayer().timeToAttack)) {
			attacking = true;
			if (!l.getSpawnpoint().getPlayer().flashing) {
//				this.setHealth(0);
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
	
	@Override
	public void die() {
		deathThreadPaused = false;
		attacking = false;
		deathTime = 0f;
	}
	
	@Override
	public void hurt(int value, boolean byProjectile) {
		if (hb.health <= 0f) {
			die();
			return;
		}
		if (hurtTimer > 0f) {
			return;
		} else {
			if (!byProjectile) hurtTimer = 40f;
		}
		deathTime = 1f;
		attacking = false;
		hb.health -= value;
	}
	
	/**
	 * Draw the monster
	 */
	@Override
	public void draw(Batch batch, OrthographicCamera c, boolean paused) {
		if (attacking && !deathThreadPaused && hb.health > 0f) {
			if (!paused) attackTime+=Gdx.graphics.getDeltaTime();
			TextureRegion currentFrame = attackAnimation.getKeyFrame(attackTime, true);
//			System.out.println(facingRight);
			currentFrame.flip(currentFrame.isFlipX() != this.isFlipX() ? this.isFlipX() : !this.isFlipX(), false);
			this.setFlip(this.isFlipX(), false);
			batch.draw(currentFrame, facingRight ? getX() + this.propWidth : getX(), getY(), facingRight ? -this.propWidth : this.propWidth, this.propHeight);
//			setTexture(idle);
			//super.draw(batch);
			if (attackTime > 1f) {
				attacking = false;
			}
		} else if (hb.health > 0 && !deathThreadPaused) {
			attackTime = 0f;
			if (!paused) jumpTime+=Gdx.graphics.getDeltaTime();
			TextureRegion currentFrame = jumpAnimation.getKeyFrame(jumpTime, true);
//			System.out.println(facingRight);
			currentFrame.flip(currentFrame.isFlipX() != this.isFlipX() ? this.isFlipX() : !this.isFlipX(), false);
			this.setFlip(this.isFlipX() || facingRight, false);
			batch.draw(currentFrame, facingRight ? getX() + this.propWidth : getX(), getY(), facingRight ? -this.propWidth : this.propWidth, this.propHeight);
			setTexture(idle);
			//this.flip(facingRight, false);
			//super.draw(batch);
			if (jumpTime > 1f) {
				jumpTime = 0f;
			}
		} else if (hb.health <= 0) {
			attacking = false;
			if (!paused) deathTime+=Gdx.graphics.getDeltaTime();
			TextureRegion currentFrame = deathAnimation.getKeyFrame(deathTime, true);
			currentFrame.flip(currentFrame.isFlipX() != this.isFlipX() ? this.isFlipX() : !this.isFlipX(), false);
			this.setFlip(this.isFlipX() || facingRight, false);
			batch.draw(currentFrame, facingRight ? getX() + this.propWidth + 20 : getX(), getY(), facingRight ? -this.propWidth - 20 : this.propWidth + 20, this.propHeight + 10);
		} else {
			attacking = false;
			if (!paused) deathTime+=Gdx.graphics.getDeltaTime();
			TextureRegion currentFrame = deathAnimation.getKeyFrame(deathTime, true);
			currentFrame.flip(currentFrame.isFlipX() != this.isFlipX() ? this.isFlipX() : !this.isFlipX(), false);
			this.setFlip(this.isFlipX() || facingRight, false);
			batch.draw(currentFrame, facingRight ? getX() + this.propWidth + 20 : getX(), getY(), facingRight ? -this.propWidth - 20 : this.propWidth + 20, this.propHeight + 10);
		}
		
		hb.render(batch, c);
	}
	
	@Override
	protected float getHealth() {
		return hb.health;
	}
}