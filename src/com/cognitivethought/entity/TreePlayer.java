package com.cognitivethought.entity;

import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.cognitivethought.entity.enemy.Enemy;
import com.cognitivethought.entity.enemy.EnemySpawner;
import com.cognitivethought.inventory.InventoryBar;
import com.cognitivethought.inventory.Item;
import com.cognitivethought.level.Level;
import com.cognitivethought.level.parts.Platform;
import com.cognitivethought.main.Main;
import com.cognitivethought.main.desktop.DesktopLauncher;
import com.cognitivethought.resources.Resources;
import com.cognitivethought.resources.Strings;
import com.cognitivethought.screens.CutsceneScreen;
import com.cognitivethought.screens.LevelSelectScreen;
import com.cognitivethought.screens.SettingsScreen;
import com.cognitivethought.ui.HealthBar;
import com.cognitivethought.sound.Sounds;

public class TreePlayer extends Sprite {
	/**
	 * The Player class
	 * 
	 * This class handles all of the important player functions such as how the player moves
	 * 
	 * Collision detection is also handled here
	 * 
	 * What to do on death is defined in a thread called deathThread
	 * 
	 * Animations are created here too
	 */
	

	public static boolean canShoot;

	public final float timeToAttack = 0.5f;

	final float timeToDie = 1f;

	public static float xPos;

	public static boolean paused;

	final int shootCol = 4, shootRow = 3;
	final int deathCol = 2, deathRow = 11;
	final int idleCol = 4, idleRow = 4;
	final int attackCol = 4, attackRow = 4;

	// The velocity of the player
	public float dx, dy;

	// Gravitational constant
	public final float g = 0.198f;

	// Multiplied by the velocity when moving the player
	public final float speed = 2f;

	public float flashTimer = 0f;

	// How many times the player has jumped in a given jump-cycle
	public int jumps = 0;

	// Whether the sprite is facing right (flipped)
	public boolean facingRight;

	public boolean left, right;

	public boolean flashing = false;

	public boolean deathThreadPaused = true;

	public boolean isAttacking;

	public float shootTime, deathTime, idleTime, attackTime = 0.01f;

	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	Animation<TextureRegion> shootAnimation;
	Texture shootSheet;

	Animation<TextureRegion> attackAnimation;
	Texture attackSheet;

	Animation<TextureRegion> deathAnimation;
	Texture deathSheet;

	Animation<TextureRegion> idleAnimation;
	Texture idleSheet;

	public Thread deathThread;

	/**
	 * Instantiates a new Player in the scene
	 * 
	 * @param t The image of the player
	 */

	public TreePlayer(Texture t, Screen s) {
		super(t);
		setSize(getWidth() * 2.5f, getHeight() * 2.5f); // Make sure the player isn't incredibly small
		createAnimations();

		attackTime = 0.02f;
	}

	void createAnimations() {
		shootSheet = Resources.PLAYER_THROW;
		deathSheet = Resources.PLAYER_DEATH;
		idleSheet = Resources.PLAYER_IDLE;
		attackSheet = Resources.PLAYER_ATTACK;

		TextureRegion[][] tmp = TextureRegion.split(shootSheet, shootSheet.getWidth() / shootCol,
				shootSheet.getHeight() / shootRow);

		TextureRegion[] shootFrames = new TextureRegion[shootCol * shootRow];
		int x = 0;
		for (int i = 0; i < shootRow; i++) {
			for (int j = 0; j < shootCol; j++) {
				shootFrames[x++] = tmp[i][j];
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

		tmp = TextureRegion.split(idleSheet, idleSheet.getWidth() / idleCol, idleSheet.getHeight() / idleRow);
		TextureRegion[] idleFrames = new TextureRegion[idleCol * idleRow];
		x = 0;
		for (int i = 0; i < idleRow; i++) {
			for (int j = 0; j < idleCol; j++) {
				idleFrames[x++] = tmp[i][j];
			}
		}

		tmp = TextureRegion.split(attackSheet, attackSheet.getWidth() / attackCol, attackSheet.getHeight() / attackRow);
		TextureRegion[] attackFrames = new TextureRegion[attackCol * attackRow];
		x = 0;
		for (int i = 0; i < attackRow; i++) {
			for (int j = 0; j < attackCol; j++) {
				attackFrames[x++] = tmp[i][j];
			}
		}

		shootAnimation = new Animation<TextureRegion>(timeToAttack / (float) (shootRow * shootCol), shootFrames);
		deathAnimation = new Animation<TextureRegion>(2f / (float) (deathRow * deathCol), deathFrames);
		idleAnimation = new Animation<TextureRegion>(3f / (float) (idleRow * idleCol), idleFrames);
		attackAnimation = new Animation<TextureRegion>(timeToAttack / (float) (attackCol * attackRow), attackFrames);
	}

	/**
	 * Controls player physics and movement
	 * 
	 * @param l Used for collision detection purposes
	 */
	public void update(Level l, HealthBar hb, InventoryBar b) {
		if (deathThreadPaused) {
			if (dy > -15f) // Cap the y velocity in the downward direction at 15 pixels per frame
				dy -= g; // Simulate gravity constantly, with terminal velocity set to 15f

			float vxChange = .5f; // How much to increment horizontal movement during smooth-movement calculations
			float maxSpeed = 5f; // The maximum absolute value that the horizontal velocity can ever be

			if (isAttacking) {
				Rectangle bounds = new Rectangle(facingRight ? getX() : getX() - 80, getY(),
						facingRight ? getWidth() + 80 : getWidth(), getHeight());
				for (EnemySpawner es : l.getEnemySpawners()) {
					for (Enemy e : es.enemies) {
						if (e.getBoundingRectangle().overlaps(bounds)) {
							e.hurt(1, false);
						}
					}
				}
			}

			if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)
					|| Gdx.input.isKeyPressed(Input.Keys.NUMPAD_4)
							&& (dy == 0 && !(shootTime > 0f && shootTime <= timeToAttack)
									&& (attackTime > 0f && (attackTime <= timeToAttack)))) {
				left = true;
				right = false;
				idleTime = 0f;
			} else if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)
					|| Gdx.input.isKeyPressed(Input.Keys.NUMPAD_6)
							&& (dy == 0 && !(shootTime > 0f && shootTime <= timeToAttack))
							&& !(attackTime > 0f && (attackTime <= timeToAttack))) {
				right = true;
				left = false;
				idleTime = 0f;
			} else {
				left = false;
				right = false;
			}

			if ((shootTime > 0f && shootTime <= timeToAttack) || (attackTime > 0f && attackTime <= timeToAttack)) {
				left = false;
				right = false;
			}

			if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.UP)
					|| Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_8) && (shootTime > 0f && shootTime <= timeToAttack)
							&& (attackTime > 0f && attackTime <= timeToAttack)) {
				jump();
				jumps++;
				idleTime = 0f;
			}

			if (hb.health <= 0f) {
				deathThread.start();
			}

			// If colliding with platform top
			// SOON TO BE OBSOLETE. DO NOT RELY ON THIS CODE
			for (Platform plat : l.getPlatforms()) {
				if (new Rectangle(plat.getX() + 1f, plat.getY() + 1f, plat.getWidth() - 2f, plat.getHeight() - 2f)
						.overlaps(getBoundingRectangle()) && dy < 0
						&& getY() >= plat.getY() + (plat.getHeight() / 2) + dy && plat.collideTop) {
					if (!plat.trampolineBehavior) {
						dy = 0; // Stop vertical movement
						setY(plat.getY() + plat.getHeight() - 2f); // Reset y position to the top of the platform
						jumps = 0; // Reset jump counter
						if (plat.canHarm && !this.flashing) { // If the platform can harm and the player is not already
																// being harmed
							if (hb.bark >= 0) { // Decrease bark first
								hb.bark--;
								this.flashing = true;
								this.flashTimer = 100f;
							} else {
								this.flashing = true;
								this.flashTimer = 100f;
								hb.health--; // Then decrease health after bark reaches 0
							}
						}
					} else {
						dy = 8f;
						setY(plat.getY() + plat.getHeight() - 2f); // Reset y position to the top of the platform
						if (plat.canHarm && !this.flashing) { // If the platform can harm and the player is not already
							if (hb.bark >= 0) { // Decrease bark first
								hb.bark--;
								this.flashing = true;
								this.flashTimer = 100f;
							} else {
								this.flashing = true;
								this.flashTimer = 100f;
								hb.health--; // Then decrease health after bark reaches 0
							}
						}
					}
				}
			}

			// If colliding with platform top
			// SOON TO BE OBSOLETE. DO NOT RELY ON THIS CODE
			for (Platform plat : l.getPlatforms()) {
				if (new Rectangle(plat.getX() + 1f, plat.getY() + 1f, plat.getWidth() - 2f, plat.getHeight() - 2f)
						.overlaps(getBoundingRectangle()) && dy < 0
						&& getY() >= plat.getY() + (plat.getHeight() / 2) + dy && plat.collideTop) {
					dy = 0; // Stop vertical movement
					setY(plat.getY() + plat.getHeight() - 2f); // Reset y position to the top of the platform
					jumps = 0; // Reset jump counter
					if (plat.canHarm && !this.flashing) { // If the platform can harm and the player is not already
															// being harmed
						if (hb.bark >= 0) { // Decrease bark first
							hb.bark--;
							this.flashing = true; // Set flashing to true because the player is being harmed
							this.flashTimer = 100f; // Set the time to be flashing
						} else {
							hb.health--; // Then decrease health after bark reaches 0

							this.flashing = true; // Set flashing to true because the player is being harmed
							this.flashTimer = 100f; // Set the time to be flashing
						}
					}
				}

				Rectangle bottomOfPlatform = new Rectangle(plat.getX(), plat.getY() - 2, plat.getWidth(), 2f);
				if (bottomOfPlatform.overlaps(getBoundingRectangle()) && dy > 0
						&& getY() + getHeight() + dy >= bottomOfPlatform.getY() && plat.collideBottom) {
					dy = 0; // Stop horizontal movement
					setY(plat.getY() - getHeight()); // Reset x position to the left of the platform
				}

				Rectangle leftOfPlatform = new Rectangle(plat.getX(), plat.getY(), 2f, plat.getHeight());
				if (leftOfPlatform.overlaps(getBoundingRectangle()) && dx > 0
						&& getX() + getWidth() + dx >= leftOfPlatform.getX() && plat.collideLeft
						&& !(getY() > (plat.getY() + plat.getHeight()) - 4)) {
					dx = 0; // Stop horizontal movement
					setX(plat.getX() - getWidth()); // Reset x position to the left of the platform
				}

				Rectangle rightOfPlatform = new Rectangle(plat.getX() + plat.getWidth() - 2f, plat.getY(), 2f,
						plat.getHeight());
				if (rightOfPlatform.overlaps(getBoundingRectangle()) && dx < 0
						&& getX() <= plat.getX() + plat.getWidth() && plat.collideRight
						&& !(getY() > (plat.getY() + plat.getHeight()) - 4)) {
					dx = 0; // Stop horizontal movement
					setX(plat.getX() + plat.getWidth()); // Reset x position to the right of the platform
				}
				if (plat.endsLevel && getBoundingRectangle().overlaps(plat.getBoundingRectangle())) {

					try { // saves inventory
						InventoryBar.i.save(Strings.INV_DIR + "inv.txt");
						Main.save();
					} catch (IOException e) {
						DesktopLauncher.log();
						e.printStackTrace();
					}

					if (LevelSelectScreen.levelNumber == 1 && Main.levelsPassed < LevelSelectScreen.levelNumber) {
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
					Main.main.completeScreen.toResetTo = l.screen;
					Main.main.setScreen(Main.main.completeScreen);
				}
			}

			if (flashTimer > 0f) {
				flashTimer--;
				flashing = true;
			} else {
				flashing = false;
			}

			if (flashTimer > 100f) {
				flashTimer = 100f;
			}

			if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
				shoot(l, b, true);
			} else if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
				shoot(l, b, false);
			}

			if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
				hit(l, true);
			} else if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
				hit(l, false);
			}

			for (int i = 0; i < projectiles.size(); i++) {
				if (projectiles.get(i).life <= 0) {
					projectiles.remove(i);
					break;
				}
				projectiles.get(i).update();
				for (EnemySpawner es : l.getEnemySpawners()) {
					for (Enemy e : es.enemies) {
						if (projectiles.get(i).checkHit(e) || projectiles.get(i).hitWall(l.getPlatforms())) {
							projectiles.get(i).life = 0;
							System.out.println("test");
							break;
						}
					}
					if (projectiles.get(i).life <= 0) {
						break;
					}
				}
			}

			if (left) {
				dx += dx > -maxSpeed ? -vxChange : 0; // if dx has not yet reached maximum speed, increment it
				dx = dx < -maxSpeed ? -maxSpeed : dx; // cap dx at its maximum speed
				facingRight = false;
			} else if (right) {
				dx += dx < maxSpeed ? vxChange : 0; // if dx has not yet reached maximum speed, increment it
				dx = dx > maxSpeed ? maxSpeed : dx; // cap dx at its maximum speed
				facingRight = true;
			} else {
				dx = dx != 0 ? (dx < 0 ? dx + vxChange : dx - vxChange) : 0; // normalize dx to 0 by using vxChange
				dx = dx > -1 && dx < 1 ? 0 : dx; // if dx is between -1 and 1, just set it to 0. This makes it so
			}

			setX(getX() + dx * speed); // Could use translate, but this works too
			setY(getY() + dy * speed);
		} else {
			dx = 0;
			dy = 0;
		}
	}

	public void die() {
		Sounds.chainsaw.stop();
		Sounds.trash_attack.stop();
		Sounds.player_melee.stop();
		Sounds.trashcan_attack.stop();
		deathThreadPaused = false;
		shootTime = 0f;
		deathTime = 0f;
	}

	/**
	 * Makes the player jump
	 */
	boolean jump() {
		if (jumps >= 2) // If there's been 2 or more jumps, the player has hit the jump limit
			return false;
		dy = 5f; // All jump does is set vertical velocity to +4 instantly.
		translateY(1);
		return true;
	}

	Projectile p;

	void shoot(Level l, InventoryBar ib, boolean mouseTriggered) {
		if (!canShoot)
			return;

		if (TreePlayer.paused)
			return;

		if (shootTime > 0.01f)
			return; // Due to potential floating point rounding errors, there is a .01 tolerance in
					// attack time

		if (mouseTriggered)
			facingRight = Gdx.input.getX() >= 1920 / 2;

		if (Item.getTexture(InventoryBar.i.getItems().get(ib.ammoSelected).getId()) == Item.getTexture(Item.APPLE)
				&& InventoryBar.i.getItems().get(ib.ammoSelected).getQuantity() > 0) {
			Sounds.player_apple.play(SettingsScreen.VOL_SOUNDS);
			p = new Projectile(Item.getTexture(Item.APPLE),
					l.getSpawnpoint().getPlayer().getX() + (facingRight ? 20 : 0),
					l.getSpawnpoint().getPlayer().getY() + getHeight() - 20, 0, 0, 400, 100, 2);
			InventoryBar.i.getItems().get(ib.ammoSelected).decrement();
		} else if (Item.getTexture(InventoryBar.i.getItems().get(ib.ammoSelected).getId()) == Item.getTexture(Item.SEED)
				&& InventoryBar.i.getItems().get(ib.ammoSelected).getQuantity() > 0) {
			Sounds.player_seed.play(SettingsScreen.VOL_SOUNDS);
			p = new Projectile(Item.getTexture(Item.SEED),
					l.getSpawnpoint().getPlayer().getX() + (facingRight ? 20 : 0),
					l.getSpawnpoint().getPlayer().getY() + getHeight() - 20, 0, 0, 400, 100, 1);
			InventoryBar.i.getItems().get(ib.ammoSelected).decrement();
		} else {
			return;
		}

		new Thread() {
			public void run() {
				shootTime = 0.02f;

				try {
					sleep((int) (timeToAttack * 1000f));
				} catch (InterruptedException e) {
					DesktopLauncher.log();
					e.printStackTrace();
				}

				p.setX(l.getSpawnpoint().getPlayer().getX() + (facingRight ? 20 : 0));
				p.setY(l.getSpawnpoint().getPlayer().getY() + getHeight() - 20);
				float vx = mouseTriggered ? (Gdx.input.getX() <= (1920 / 2) ? -10 : 10) : (facingRight ? 10 : -10); // (((Gdx.input.getX()
																													// -
																													// 960f)
																													// *
																													// 2f)
																													// /
																													// div)
																													// :
																													// ((Gdx.input.getX()
																													// -
																													// 960f)
																													// *
																													// 2f
																													// /
																													// div);
				float vy = 0;
				p.dx = vx;
				p.dy = vy;
				// float vy = ((520f - Gdx.input.getY()) * 2f / 110f) > 0f ? ((520f -
				// Gdx.input.getY()) * 2f / 110f) : 0f;

				projectiles.add(p);
			}
		}.start();
	}

	public void hit(Level l, boolean mouseTriggered) {
		if (!canShoot)
			return;

		if (attackTime > 0.01f)
			return; // Due to potential floating point rounding errors, there is a .01 tolerance in
					// attack time

		if (mouseTriggered)
			facingRight = Gdx.input.getX() >= 1920 / 2;

		Sounds.player_melee.play(SettingsScreen.VOL_SOUNDS);

		attackTime = 0.02f;

	}

	TextureRegion currentFrame;

	/**
	 * Draws the player
	 * 
	 * @param sb The same SpriteBatch that is rendering other objects in the Screen
	 */
	public void render(SpriteBatch sb, boolean paused) {
		xPos = this.getX();
		// Facing right explanation: if the player is facing right, then draw the player
		// at an increased x value, but with a negative width,
		// otherwise just draw player normally
		for (int i = 0; i < projectiles.size(); i++) {
			sb.draw(projectiles.get(i), projectiles.get(i).getX(), projectiles.get(i).getY());
		}

		if (this.flashing && Math.random() > 0.75f && deathThreadPaused)
			return;

		if (shootTime > 0f && shootTime <= timeToAttack) {
			isAttacking = false;
			left = false;
			right = false;
			jumps = 3;
			if (!paused)
				shootTime += Gdx.graphics.getDeltaTime();
			idleTime = 0f;
			currentFrame = shootAnimation.getKeyFrame(shootTime, true);
			currentFrame.flip(currentFrame.isFlipX() != this.isFlipX() ? this.isFlipX() : !this.isFlipX(), false);
			this.setFlip(this.isFlipX() || facingRight, false);
			sb.draw(currentFrame, facingRight ? getX() + getWidth() : getX(), getY(),
					facingRight ? -getWidth() : getWidth(), getHeight() * 1.25f);
			// setTexture();
			// this.flip(facingRight, false);
			// super.draw(batch);
			if (shootTime >= timeToAttack) {
				shootTime = 0f;
			}
		} else if (attackTime > 0f && attackTime <= timeToAttack) {
			left = false;
			right = false;
			jumps = 3;
			if (!paused)
				attackTime += Gdx.graphics.getDeltaTime();
			isAttacking = true;
			idleTime = 0f;
			currentFrame = attackAnimation.getKeyFrame(attackTime, true);
			currentFrame.flip(currentFrame.isFlipX() != this.isFlipX() ? this.isFlipX() : !this.isFlipX(), false);
			this.setFlip(this.isFlipX() || facingRight, false);
			sb.draw(currentFrame, facingRight ? getX() + (getWidth() + 84f) : (getX() - getWidth() - 12f), getY(),
					facingRight ? -(getWidth() + 84f) : (getWidth() + 84f), (getHeight() + 18f) * 1.15f);
			// setTexture();
			// this.flip(facingRight, false);
			// super.draw(batch);
			if (attackTime >= timeToAttack) {
				attackTime = 0f;
			}
		} else if (deathThreadPaused) {
			if (!paused)
				idleTime += Gdx.graphics.getDeltaTime();
			isAttacking = false;
			currentFrame = idleAnimation.getKeyFrame(idleTime < 3f ? idleTime : 0f, true);
			// System.out.println(facingRight);
			currentFrame.flip(currentFrame.isFlipX() != this.isFlipX() ? this.isFlipX() : !this.isFlipX(), false);
			this.setFlip(this.isFlipX() || facingRight, false);
			sb.draw(currentFrame, facingRight ? getX() + getWidth() : getX(), getY(),
					facingRight ? -getWidth() : getWidth(), getHeight());
			// setTexture();
			// this.flip(facingRight, false);
			// super.draw(batch);
			if (idleTime > 5f) {
				idleTime = 0f;
			}
		} else {
			isAttacking = false;
			left = false;
			right = false;
			jumps = 3;
			if (!paused)
				deathTime += Gdx.graphics.getDeltaTime();
			idleTime = 0f;
			shootTime = 0f;
//			setSize(78*2, 70*1.75f);
			TextureRegion currentFrame = deathAnimation.getKeyFrame(deathTime <= 1f ? deathTime : 1f, true);
			currentFrame.flip(currentFrame.isFlipX() != this.isFlipX() ? this.isFlipX() : !this.isFlipX(), false);
			this.setFlip(this.isFlipX() || facingRight, false);
			sb.draw(currentFrame, facingRight ? getX() + getWidth() : getX(), getY(),
					facingRight ? -getWidth() : getWidth(), getHeight() * 1f);
			// setTexture();
			// this.flip(facingRight, false);
			// super.draw(batch);
			if (deathTime >= timeToDie) {
				deathTime = timeToDie;
			}
		}

//		sb.draw(getTexture(), !facingRight ? getX() : getX() + getWidth(), getY(),
//				!facingRight ? getWidth() : -getWidth(), getHeight());

	}
}