package com.cognitivethought.entity.enemy;

import java.io.IOException;
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
import com.cognitivethought.entity.TreePlayer;
import com.cognitivethought.inventory.InventoryBar;
import com.cognitivethought.inventory.Item;
import com.cognitivethought.level.Level;
import com.cognitivethought.level.parts.Platform;
import com.cognitivethought.main.Main;
import com.cognitivethought.resources.Resources;
import com.cognitivethought.resources.Strings;
import com.cognitivethought.screens.CutsceneScreen;
import com.cognitivethought.screens.LevelSelectScreen;
import com.cognitivethought.screens.SettingsScreen;
import com.cognitivethought.ui.HealthBar;
import com.cognitivethought.sound.Sounds;

public class Tyrone extends Enemy {

	/**
	 * The final boss of the game
	 * 
	 * This class handles creation of animations and collision detection
	 * 
	 * Attack damage and event timers are also set
	 */

	int majorTimer = 500;
	final int attackCol = 4, attackRow = 2;
	final int majorCol = 5, majorRow = 2;
	final int moveCol = 4, moveRow = 3;
	final int deathCol = 29, deathRow = 1;
	

	boolean wall;

	float attackTime;
	float jumpTime;
	float deathTime = 1f;

	final float propWidth = 72f + 100, propHeight = 94.28571f + 100; //sets scale for the sprite

	boolean deathThreadPaused;

	HealthBar hb;

	Animation<TextureRegion> attackAnimation;
	Texture attackSheet;

	Animation<TextureRegion> majorAttackAnimation;
	Texture majorAttackSheet;

	Animation<TextureRegion> deathAnimation;
	Texture deathSheet;

	Animation<TextureRegion> jumpAnimation;
	Texture moveSheet;

	Texture idle;

	private final float g = 0.198f; // Gravitational constant

	Platform toBeOn; // The platform the monster should be on

	boolean movingLeft, movingRight; // Whether or not the monster is moving right or left
	boolean facingRight; // Whether the monster is facing right or not
	boolean attacking = false;
	boolean majorAttacking = false;

	float pauseTimer; // How long to pause in between movements
	float dx, dy; // The velocity of the monster
	
	long chainsaw_id;
	long smash_id;
	long run_id;
	
	/**
	 * The first monster the player will encounter. A heaping mass of garbage that
	 * is thankfully - for the sake of the player's nose - contained in a trash can.
	 * 
	 * @param damageValue How much damage this particular trash monster will do
	 * @param texture     The appearance of this particular trash monster
	 * @throws Exception
	 */
	public Tyrone(Behavior b, float damageValue, float majorDamageValue, Texture texture, ArrayList<Enemy> enemies, Level l) {
		super(b, Behavior.MELEE, damageValue, majorDamageValue, texture);
		this.speed = 2f; // Default speed to 3f
		this.dx = -speed; // Default movement to the left
		this.idle = texture;

		Tyrone t = this;

		canPlaySound = true;
		
		chainsaw_id = Sounds.chainsaw.play(SettingsScreen.VOL_SOUNDS);
		Sounds.chainsaw.setLooping(chainsaw_id, true);
		Sounds.chainsaw.pause(chainsaw_id);
		
		smash_id = Sounds.boss_smash.play(SettingsScreen.VOL_SOUNDS);
		Sounds.boss_smash.setLooping(smash_id, true);
		Sounds.boss_smash.pause(smash_id);
		
		run_id = Sounds.boss_run.play(SettingsScreen.VOL_SOUNDS);
		Sounds.boss_run.setLooping(run_id, true);
		Sounds.boss_run.pause(run_id);
		
		hb = new HealthBar(this, 10);
		
		deathThread = new Thread() { //defines the death thread and what to do
			@SuppressWarnings("static-access")
			public void run() {
				try {
					dx = 0;
					die();
					
					System.out.println("DIED!");
					this.sleep(1950);
					int organicMatterToDrop = new Random().nextInt(4);
					for (int i = 0; i < organicMatterToDrop; i++) {
						ItemDrop om = new ItemDrop(Resources.ORGANIC_MATTER, (int) t.getX() + (int) (t.getWidth() / 2),
								(int) t.getY() + (int) (t.getHeight() / 2), 40, 40, Item.ORGANIC_MATTER);
						om.dy = (float) (Math.random() * 2.0) + 1f;
						om.dx = (float) (Math.random() * (Math.random() <= 0.5f ? -1 : 1) * 2)
								* (new Random().nextInt(2) + 1);
						l.getItemDrops().add(om);
					}
					int coinsToDrop = 20;
					for (int i = 0; i < coinsToDrop; i++) {
						ItemDrop c = new ItemDrop(Resources.COIN, (int) t.getX() + (int) (t.getWidth() / 2),
								(int) t.getY() + (int) (t.getHeight() / 2), 40, 40, Item.COIN);
						c.dy = (float) (Math.random() * 2.0) + 1f;
						c.dx = (float) (Math.random() * (Math.random() <= 0.5f ? -1 : 1) * 2)
								* (new Random().nextInt(2) + 1);
						l.getItemDrops().add(c);
					}
					enemies.remove(t);
					this.sleep(5000);
					
					try { //saves inventory
						InventoryBar.i.save(Strings.INV_DIR + "inv.txt");
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					if (LevelSelectScreen.levelNumber == 1 && Main.levelsPassed < LevelSelectScreen.levelNumber) { //sets level completion code
						Main.levelsPassed = 1;
						
					}

					if (LevelSelectScreen.levelNumber == 2 && Main.levelsPassed < LevelSelectScreen.levelNumber) {
						Main.levelsPassed = 2;
						
					}

					if (LevelSelectScreen.levelNumber == 3 && Main.levelsPassed < LevelSelectScreen.levelNumber) {
						Main.levelsPassed = 3;
					
					}

					if (LevelSelectScreen.levelNumber == 4 && Main.levelsPassed < LevelSelectScreen.levelNumber) {
						Main.levelsPassed = 4;
						
					}

					if (LevelSelectScreen.levelNumber == 5 && Main.levelsPassed < LevelSelectScreen.levelNumber) {
						Main.levelsPassed = 5;
						
					}
					Main.main.cutsceneScreen.currentCutscene = CutsceneScreen.TYRONE;
					Main.main.setScreen(Main.main.cutsceneScreen);
					
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		};

		createAnimations();
	}

	void createAnimations() {
		attackSheet = Resources.TYRONE_ATTACK; //assigns the sprite animation textures
		majorAttackSheet = Resources.TYRONE_MAJOR;
		moveSheet = Resources.TYRONE_MOVE;
		deathSheet = Resources.TYRONE_DEATH;

		TextureRegion[][] tmp = TextureRegion.split(attackSheet, attackSheet.getWidth() / attackCol, //creates texture region
				attackSheet.getHeight() / attackRow);

		TextureRegion[] attackFrames = new TextureRegion[attackCol * attackRow];
		int x = 0;
		for (int i = 0; i < attackRow; i++) {
			for (int j = 0; j < attackCol; j++) {
				attackFrames[x++] = tmp[i][j];
			}
		}

		tmp = TextureRegion.split(majorAttackSheet, majorAttackSheet.getWidth() / majorCol,
				majorAttackSheet.getHeight() / majorRow);
		TextureRegion[] majorAttackFrames = new TextureRegion[majorCol * majorRow];
		x = 0;
		for (int i = 0; i < majorRow; i++) {
			for (int j = 0; j < majorCol; j++) {
				majorAttackFrames[x++] = tmp[i][j];
			}
		}

		tmp = TextureRegion.split(moveSheet, moveSheet.getWidth() / moveCol, moveSheet.getHeight() / moveRow);
		TextureRegion[] jumpFrames = new TextureRegion[moveCol * moveRow];
		x = 0;
		for (int i = 0; i < moveRow; i++) {
			for (int j = 0; j < moveCol; j++) {
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

		jumpAnimation = new Animation<TextureRegion>((float) (new Random().nextInt(3) + 6) / 100f, jumpFrames); //assigns animations
		attackAnimation = new Animation<TextureRegion>(0.09f, attackFrames);
		majorAttackAnimation = new Animation<TextureRegion>(0.09f, majorAttackFrames);
		deathAnimation = new Animation<TextureRegion>(2f / (float) (deathRow * deathCol), deathFrames);

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
			if (new Rectangle(plat.getX() + 1f, plat.getY() + 1f, plat.getWidth() - 2f, plat.getHeight() - 2f)
					.overlaps(getBoundingRectangle()) && dy < 0 && getY() >= plat.getY() + (plat.getHeight() / 2) + dy
					&& plat.collideTop) {
				dy = 0;
				setY(plat.getY() + plat.getHeight() - 2f); // Reset y position to the top of the platform
			}

			if (new Rectangle(plat.getX() + 1f, plat.getY() + 1f, plat.getWidth() - 2f, plat.getHeight() - 2f)
					.overlaps(getBoundingRectangle()) && dy > 0
					&& getY() + getHeight() >= plat.getY() + plat.getHeight() && plat.collideBottom) {
				System.out.println(getY() + getHeight() + " " + plat.getY());
				dy = 0;
				setY(plat.getY() - getHeight() + plat.getHeight() + 2f); // Reset y position to the bottom of the
																			// platform
				break;
			}

			Rectangle leftOfPlatform = new Rectangle(plat.getX(), plat.getY(), 2f, plat.getHeight()); 
			if (leftOfPlatform.overlaps(getBoundingRectangle()) && dx > 0
					&& getX() + getWidth() + dx >= leftOfPlatform.getX() && (plat.collideLeft || plat.collidesEnemy)
					&& !(getY() > (plat.getY() + plat.getHeight()) - 4)) { //detects if platform is bing collided with on left
				dx *= -1;
				translateX(-4);
				facingRight = true;
				this.flip(true, false);
				wall = true;
				return;
			}

			Rectangle rightOfPlatform = new Rectangle(plat.getX() + plat.getWidth() - 2f, plat.getY(), 2f,
					plat.getHeight());
			if (rightOfPlatform.overlaps(getBoundingRectangle()) && dx < 0 && getX() <= plat.getX() + plat.getWidth()
					&& (plat.collideLeft || plat.collidesEnemy) && !(getY() > (plat.getY() + plat.getHeight()) - 4)) {
				dx *= -1;
				translateX(4);
				facingRight = false;
				this.flip(true, false);
				wall = true;
				return;
			}
		}
		wall = false;
	}

	/**
	 * Update the monster
	 */
	@Override
	public void update(HealthBar hb, Level l) {
		if (!(getBoundingRectangle().overlaps(new Rectangle(l.getSpawnpoint().getPlayer().getX() - 1920,
				l.getSpawnpoint().getPlayer().getY() - 1080, 1920 * 2, 1080 * 2)))) {
			return;
		}

		if (dy > -15f)
			dy -= g; // Simulate gravity constantly, with terminal velocity set to 15f
		
		if (!canPlaySound) {
			Sounds.chainsaw.pause(chainsaw_id);
			Sounds.boss_run.pause();
			Sounds.boss_smash.pause();
		}
		
		if (hurtTimer > 0f) {
			hurtTimer--;
		}

		if (attacking) {
			attackTimer -= 2f;
		}

		facingRight = dx < 0;

		if (deathThreadPaused) {
			deathThreadTime++;
		}

		if (attackTimer <= 0f) {
			attacking = false;
		}

		move(l); // Do movement code
		if (majorTimer > 0) { // do normal movement before the timer goes off
			if (!wall) {
				if (TreePlayer.xPos <= this.getX() && facingRight == false) { //faces the player
					facingRight = true;
					this.flip(true, false);
					if (dx == 0)
						dx = -2f;
					dx *= -1;
				}
				if (TreePlayer.xPos >= this.getX() && facingRight == true) {
					facingRight = false;
					this.flip(true, false);
					if (dx == 0)
						dx = 2f;
					dx *= -1;
				}
				translateY(dy); // Do translations
				if (!attacking)
					translateX(dx);
			}
		} else {
			if (majorTimer == 0) { //set major attack speed
				Sounds.boss_run.resume(run_id);
				dx *= 4;

			}
			
			translateX(dx);
			

		}

		move(l);
		attack(hb, l); // Do attacking
	}

	/**
	 * Process monster attacking
	 */
	@Override
	void attack(HealthBar hb, Level l) {
		// if (l.getSpawnpoint().getPlayer().isAttacking) return;

		if (this.hb.health <= 0f)
			return;

		attackRange = 1f;
		// if the player is in range, the monster can attack
		boolean canAttack = new Rectangle(getX() - attackRange, getY() - attackRange, getWidth() + (attackRange * 2),
				getHeight() + (attackRange * 2)).overlaps(l.getSpawnpoint().getPlayer().getBoundingRectangle());
		boolean canMajorAttack = this.getBoundingRectangle()
				.overlaps(l.getSpawnpoint().getPlayer().getBoundingRectangle());
		
		if (!canAttack) {
			Sounds.chainsaw.pause(chainsaw_id);
		}
		
		if (!canMajorAttack) {
			Sounds.boss_smash.pause(smash_id);
		}
		
		if (majorAttacking && canMajorAttack && deathTime != 0f && !(l.getSpawnpoint().getPlayer().attackTime > 0f && l.getSpawnpoint().getPlayer().attackTime <= l.getSpawnpoint().getPlayer().timeToAttack)) {
			//what to do when major attacking
			Sounds.boss_smash.resume(smash_id);
			if (!l.getSpawnpoint().getPlayer().flashing) { //if the player isnt already hurt then attack
//				this.setHealth(0);
				if (hb.bark >= 0) { // damage bark
					hb.bark -= majorDamageValue;
					attackTimer = 50f;
				} else { //damage health
					hb.health -= majorDamageValue;
					attackTimer = 50f;
				}
				l.getSpawnpoint().getPlayer().flashing = true;
				l.getSpawnpoint().getPlayer().flashTimer = 500f;
			}
		}

		// attack if the monster can attack
		if (!majorAttacking && canAttack && deathTime != 0f && !(l.getSpawnpoint().getPlayer().attackTime > 0f
				&& l.getSpawnpoint().getPlayer().attackTime <= l.getSpawnpoint().getPlayer().timeToAttack)) {
			Sounds.chainsaw.resume(chainsaw_id);
			attacking = true;
			if (!l.getSpawnpoint().getPlayer().flashing) {
//				this.setHealth(0);
				if (hb.bark >= 0) {
					hb.bark -= damageValue;
					attackTimer = 50f;
				} else {
					hb.health -= damageValue;
					attackTimer = 50f;
				}
				l.getSpawnpoint().getPlayer().flashing = true;
				l.getSpawnpoint().getPlayer().flashTimer = 500f;
			}
		}
	}

	float prevDx = 0f;

	@Override
	public void die() { //death code
		deathThreadPaused = false;
		canPlaySound = false;
		Sounds.city_music.pause();
		attacking = false;
		deathTime = 0f;
		dx = 0;
	}

	@Override
	public void hurt(int value, boolean byProjectile) { //what to do when hurt
		if (hb.health <= 0f) {
			die();
			return;
		}
		if (hurtTimer > 0f) {
			return;
		} else {
			if (!byProjectile)
				hurtTimer = 40f;
		}
		deathTime = 1f;
		attacking = false;
		hb.health -= value;// take away the amt of health as damage
	}

	/**
	 * Draw the monster
	 */
	@Override
	public void draw(Batch batch, OrthographicCamera c, boolean paused) {
		if (!paused) {
			majorTimer -= 1; //ticks down the timer
		}
		this.setSize(propWidth, propHeight);
		if (majorTimer <= 0) { // when the timer reaches zero...

			majorAttacking = true;

			if (!paused) {
				attackTime += Gdx.graphics.getDeltaTime();
				TextureRegion currentFrame = majorAttackAnimation.getKeyFrame(attackTime, true);
//			System.out.println(facingRight);
				currentFrame.flip(currentFrame.isFlipX() != this.isFlipX() ? this.isFlipX() : !this.isFlipX(), false);
				this.setFlip(this.isFlipX(), false);
				batch.draw(currentFrame, facingRight ? getX() + this.propWidth + 30: getX(), getY(),facingRight ? -this.propWidth - 30: this.propWidth + 30, this.propHeight);
//			setTexture(idle);
			// super.draw(batch);
			
				if (majorTimer <= -50) { // when full cycle is done end the major attack
					majorTimer = 500;
					majorAttacking = false;
					dx = 2f;
					
					
				}
			}

		} else if (attacking && !deathThreadPaused && hb.health > 0f) {
			if (!paused)
				attackTime += Gdx.graphics.getDeltaTime();
			TextureRegion currentFrame = attackAnimation.getKeyFrame(attackTime, true);
//			System.out.println(facingRight);
			currentFrame.flip(currentFrame.isFlipX() != this.isFlipX() ? this.isFlipX() : !this.isFlipX(), false);
			this.setFlip(this.isFlipX(), false);
			batch.draw(currentFrame, facingRight ? getX() + this.propWidth: getX(), getY(),
					facingRight ? -this.propWidth: this.propWidth, this.propHeight);
//			setTexture(idle);
			// super.draw(batch);
			if (attackTime > 1f) {
				attacking = false;
			}
		} else if (hb.health > 0 && !deathThreadPaused) {
			attackTime = 0f;
			if (!paused)
				jumpTime += Gdx.graphics.getDeltaTime();
			TextureRegion currentFrame = jumpAnimation.getKeyFrame(jumpTime, true);
//			System.out.println(facingRight);
			currentFrame.flip(currentFrame.isFlipX() != this.isFlipX() ? this.isFlipX() : !this.isFlipX(), false);
			this.setFlip(this.isFlipX() || facingRight, false);
			batch.draw(currentFrame, facingRight ? getX() + this.propWidth : getX(), getY(),
					facingRight ? -this.propWidth : this.propWidth, this.propHeight);
			setTexture(idle);
			// this.flip(facingRight, false);
			// super.draw(batch);
			if (jumpTime > 1f) {
				jumpTime = 0f;
			}
		} else if (hb.health <= 0) {
			attacking = false;
			if (!paused)
				deathTime += Gdx.graphics.getDeltaTime();
			TextureRegion currentFrame = deathAnimation.getKeyFrame(deathTime, true);
			currentFrame.flip(currentFrame.isFlipX() != this.isFlipX() ? this.isFlipX() : !this.isFlipX(), false);
			this.setFlip(this.isFlipX() || facingRight, false);
			batch.draw(currentFrame, facingRight ? getX() + this.propWidth + 20 : getX(), getY(),
					facingRight ? -this.propWidth - 20 : this.propWidth + 20, this.propHeight + 10);
		} else {
			attacking = false;
			if (!paused)
				deathTime += Gdx.graphics.getDeltaTime();
			TextureRegion currentFrame = deathAnimation.getKeyFrame(deathTime, true);
			currentFrame.flip(currentFrame.isFlipX() != this.isFlipX() ? this.isFlipX() : !this.isFlipX(), false);
			this.setFlip(this.isFlipX() || facingRight, false);
			batch.draw(currentFrame, facingRight ? getX() + this.propWidth + 20 : getX(), getY(),
					facingRight ? -this.propWidth - 20 : this.propWidth + 20, this.propHeight + 10);
		}

		hb.render(batch, c);
	}

	@Override
	protected float getHealth() {
		return hb.health;
	}

}
