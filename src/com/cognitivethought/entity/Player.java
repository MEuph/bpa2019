package com.cognitivethought.entity;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.cognitivethought.entity.enemy.Enemy;
import com.cognitivethought.entity.enemy.EnemySpawner;
import com.cognitivethought.level.Level;
import com.cognitivethought.level.parts.Platform;
import com.cognitivethought.screens.LevelSelect;
import com.cognitivethought.ui.HealthBar;

public class Player extends Sprite {
	
	final int attackCol = 4, attackRow = 3;
	final int deathCol = 2, deathRow = 11;
	final int idleCol = 4, idleRow = 4;
	
	public static int levelsPassed = 0;
	// The velocity of the player
	private float dx, dy;

	// Gravitational constant
	private final float g = 0.198f;

	// Multiplied by the velocity when moving the player
	private final float speed = 2f;

	public float flashTimer = 0f;

	// How many times the player has jumped in a given jump-cycle
	private int jumps = 0;

	// Whether the sprite is facing right (flipped)
	private boolean facingRight;
	
	private boolean left, right;
	
	public boolean flashing = false;
	
	float attackTime, deathTime, idleTime;
	
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	Animation<TextureRegion> attackAnimation;
	Texture attackSheet;
	
	Animation<TextureRegion> throwAnimation;
	Texture throwSheet;
	
	Animation<TextureRegion> deathAnimation;
	Texture deathSheet;
	
	Animation<TextureRegion> idleAnimation;
	Texture idleSheet;
	
	/**
	 * Instantiates a new Player in the scene
	 * 
	 * @param t The image of the player
	 */
	public Player(Texture t) {
		super(t);
		setSize(getWidth() * 2.5f, getHeight() * 2.5f); // Make sure the player isn't incredibly small
		createAnimations();
	}

	void createAnimations() {
		attackSheet = new Texture("assets/Player/throw.png");
		deathSheet = new Texture("assets/Player/death.png");
		idleSheet = new Texture("assets/Player/idle.png");
		
		TextureRegion[][] tmp = TextureRegion.split(attackSheet, attackSheet.getWidth() / attackCol, 
				attackSheet.getHeight() / attackRow);
		
		TextureRegion[] attackFrames = new TextureRegion[attackCol * attackRow];
		int x = 0;
		for (int i = 0; i < attackRow; i++) {
			for (int j = 0; j < attackCol; j++) {
				attackFrames[x++] = tmp[i][j];
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
		
		attackAnimation = new Animation<TextureRegion>(2f / (float)(attackRow * attackCol), attackFrames);
		deathAnimation = new Animation<TextureRegion>(2f / (float)(deathRow * deathCol), deathFrames);
		idleAnimation = new Animation<TextureRegion>(3f / (float)(idleRow * idleCol), idleFrames);
	}
	
	/**
	 * Controls player physics and movement
	 * 
	 * @param l Used for collision detection purposes
	 */
	public void update(Level l, HealthBar hb) {
		if (dy > -15f) // Cap the y velocity in the downward direction at 15 pixels per frame
			dy -= g; // Simulate gravity constantly, with terminal velocity set to 15f

		float vxChange = .5f; // How much to increment horizontal movement during smooth-movement calculations
		float maxSpeed = 5f; // The maximum absolute value that the horizontal velocity can ever be
		
		if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.NUMPAD_4) && !(attackTime > 0f && attackTime <= 2f)) {
			left = true;
			right = false;
			idleTime = 0f;
		} else if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.NUMPAD_6) && !(attackTime > 0f && attackTime <= 2f)) {
			right = true;
			left = false;
			idleTime = 0f;
		} else {
			left = false;
			right = false;
		}
		
		if (attackTime > 0f && attackTime <= 2f) {
			left = false;
			right = false;
		}
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.W) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.UP) || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_4) && (attackTime > 0f && attackTime <= 2f)) {
			jump();
			jumps++;
			idleTime = 0f;
		}
		
		// If colliding with platform top
		// SOON TO BE OBSOLETE. DO NOT RELY ON THIS CODE
		for (Platform plat : l.getPlatforms()) {
			if (new Rectangle(plat.getX()+1f, plat.getY()+1f, plat.getWidth()-2f, plat.getHeight()-2f).overlaps(getBoundingRectangle()) && dy < 0 && getY() >= plat.getY() + (plat.getHeight() / 2) + dy && plat.collideTop) {
				dy = 0; // Stop vertical movement
				setY(plat.getY() + plat.getHeight() - 2f); // Reset y position to the top of the platform
				jumps = 0; // Reset jump counter
				if (plat.canHarm && !this.flashing) { // If the platform can harm and the player is not already being harmed
					if (hb.bark >= 0) { // Decrease bark first
						hb.bark--;
					} else {
						hb.health--; // Then decrease health after bark reaches 0
						if (LevelSelect.levelNumber == 1 && levelsPassed < LevelSelect.levelNumber) {
							levelsPassed = 1;
						}
						if (LevelSelect.levelNumber == 2 && levelsPassed < LevelSelect.levelNumber) {
							levelsPassed = 2;
						}
						if (LevelSelect.levelNumber == 3 && levelsPassed < LevelSelect.levelNumber) {
							levelsPassed = 3;
						}
						if (LevelSelect.levelNumber == 4 && levelsPassed < LevelSelect.levelNumber) {
							levelsPassed = 4;
						}
						if (LevelSelect.levelNumber == 5 && levelsPassed < LevelSelect.levelNumber) {
							levelsPassed = 5;
						}
					}
					this.flashing = true; // Set flashing to true because the player is being harmed
					this.flashTimer = 2000f; // Set the time to be flashing
				}
			}
			
			if (new Rectangle(plat.getX()+1f, plat.getY()+1f, plat.getWidth()-2f, plat.getHeight()-2f).overlaps(getBoundingRectangle()) && dy > 0 && getY() + getHeight() >= plat.getY() + plat.getHeight()  && plat.collideBottom) {
//				System.out.println(getY() + getHeight() + " " + plat.getY()); // For debugging purposes
				dy = 0; // Stop vertical movement
				setY(plat.getY() - getHeight() + plat.getHeight() + 2f); // Reset y position to the bottom of the platform
			}
			
			Rectangle leftOfPlatform = new Rectangle(plat.getX(), plat.getY(), 2f, plat.getHeight());
			if (leftOfPlatform.overlaps(getBoundingRectangle()) && dx > 0 && getX() + getWidth() + dx >= leftOfPlatform.getX() && plat.collideLeft && !(getY()>(plat.getY()+plat.getHeight())-4)) {
				dx = 0; // Stop horizontal movement
				setX(plat.getX() - getWidth()); // Reset x position to the left of the platform
			}
			
			Rectangle rightOfPlatform = new Rectangle(plat.getX()+plat.getWidth()-2f, plat.getY(), 2f, plat.getHeight());
			if (rightOfPlatform.overlaps(getBoundingRectangle()) && dx < 0 && getX() <= plat.getX() + plat.getWidth() && plat.collideRight && !(getY()>(plat.getY()+plat.getHeight())-4)) {
				dx = 0; // Stop horizontal movement
				setX(plat.getX() + plat.getWidth()); // Reset x position to the right of the platform
			}
			
			if (this.flashing && flashTimer > 0f) { // If the player is flashing and being harmed, but the timer is more than 0
				flashTimer -= 0.05f; 
			}
			else {
				flashTimer = 2000f;
				flashing = false;
			}
		}
		
		if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) || Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
			shoot(l);
		}

		for (Projectile p : projectiles) {
			if (p.life <= 0) {
				projectiles.remove(p);
				break;
			}
			p.update();
			for (EnemySpawner es : l.getEnemySpawners()) {
				for (Enemy e : es.enemies) {
					if (p.checkHit(e) || p.hitWall(l.getPlatforms())) {
						p.life = 0;
						break;
					}
				}
				if (p.life <= 0) {
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
			dx = dx > -1 && dx < 1 ? 0 : dx; // if dx is between -1 and 1, just set it to 0. This makes it so vxChange
		}

		setX(getX() + dx * speed); // Could use translate, but this works too
		setY(getY() + dy * speed);
		
	}

	/**
	 * Makes the player jump
	 */
	boolean jump() {
		if (jumps >= 2) // If there's been 2 or more jumps, the player has hit the jump limit
			return false;
		dy = 4f; // All jump does is set vertical velocity to +4 instantly.
		translateY(1);
		return true;
	}
	
	Projectile p;
	
	void shoot(Level l) {
		if (attackTime > 0.01f) return; // Due to potential floating point rounding errors, there is a .01 tolerance in attack time

		float div = 240f;
		
		facingRight = Gdx.input.getX() >= 1920 / 2;
		
		p = new Projectile(new Texture("assets/Player/apple.png"), l.getSpawnpoint().getPlayer().getX() + (facingRight ? 20 : 0), l.getSpawnpoint().getPlayer().getY() + getHeight() - 20, 0, 0, 400, 100);
		
		new Thread() {
			public void run() {
				attackTime = 0.02f;
				
				p.setX(l.getSpawnpoint().getPlayer().getX() + (facingRight ? 20 : 0));
				p.setY(l.getSpawnpoint().getPlayer().getY() + getHeight() - 20);
				float vx = Gdx.input.getX() <= (1920 / 2) ? -5 : 5; //(((Gdx.input.getX() - 960f) * 2f) / div) : ((Gdx.input.getX() - 960f) * 2f / div);
				float vy = 0;
				p.dx = vx;
				p.dy = vy;
				//float vy = ((520f - Gdx.input.getY()) * 2f / 110f) > 0f ? ((520f - Gdx.input.getY()) * 2f / 110f) : 0f;
				
				System.out.println("test");
				
				try {
					sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				projectiles.add(p);
			}
		}.start();
	}

	/**
	 * Draws the player
	 * 
	 * @param sb The same SpriteBatch that is rendering other objects in the Screen
	 */
	public void render(SpriteBatch sb) {
		// Facing right explanation: if the player is facing right, then draw the player
		// at an increased x value, but with a negative width,
		// otherwise just draw player normally
		for (Projectile p : projectiles) {
			sb.draw(p, p.getX(), p.getY());
		}

		if (this.flashing && Math.random() > 0.75f) return;
		
		 if (attackTime > 0f && attackTime <= 2f){
			left = false;
			right = false;
			jumps = 3;
			attackTime+=Gdx.graphics.getDeltaTime();
			idleTime = 0f;
			TextureRegion currentFrame = attackAnimation.getKeyFrame(attackTime, true);
			currentFrame.flip(currentFrame.isFlipX() != this.isFlipX() ? this.isFlipX() : !this.isFlipX(), false);
			this.setFlip(this.isFlipX() || facingRight, false);
			sb.draw(currentFrame, facingRight ? getX() + getWidth() : getX(), getY(), facingRight ? -getWidth() : getWidth(), getHeight()*1.25f);
	//		setTexture();
			//this.flip(facingRight, false);
			//super.draw(batch);
			if (attackTime >= 2f) {
				attackTime = 0f;
			}
		} else {
			idleTime+=Gdx.graphics.getDeltaTime();
			TextureRegion currentFrame = idleAnimation.getKeyFrame(idleTime < 3f ? idleTime : 0f, true);
	//		System.out.println(facingRight);
			currentFrame.flip(currentFrame.isFlipX() != this.isFlipX() ? this.isFlipX() : !this.isFlipX(), false);
			this.setFlip(this.isFlipX() || facingRight, false);
			sb.draw(currentFrame, facingRight ? getX() + getWidth() : getX(), getY(), facingRight ? -getWidth() : getWidth(), getHeight());
	//		setTexture();
			//this.flip(facingRight, false);
			//super.draw(batch);
			if (idleTime > 5f) {
				idleTime = 0f;
			}
		}
		
//		sb.draw(getTexture(), !facingRight ? getX() : getX() + getWidth(), getY(),
//				!facingRight ? getWidth() : -getWidth(), getHeight());
		
	}
}