package com.cognitivethought.level.parts;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.cognitivethought.screens.LevelSelectScreen;

/**
 * An individual tile in the tilemap
 */
public class Platform extends Sprite {

	public static final Texture GRASS = new Texture("assets/Tilesets/Tutorial Tileset/filledtopplat.png");
	public static final Texture DIRT = new Texture("assets/Tilesets/Tutorial Tileset/filledplat.png");
	public static final Texture ROAD = new Texture("assets/Tilesets/City Tileset/road.png");
	public static final Texture CONC = new Texture("assets/Tilesets/City Tileset/concrete.png");

	public boolean collideTop;
	public boolean collideBottom;
	public boolean collideRight;
	public boolean collideLeft;
	public boolean canHarm;
	public boolean endsLevel;
	public boolean collidesEnemy;
	public boolean trampolineBehavior;

	private int posX, posY;

	/**
	 * Basically just a placeholder of values for the player to interact with
	 * 
	 * @param t The texture to draw when rendering the platform
	 * @param x The horizontal position
	 * @param y The vertical position
	 * @param w The horizontal size
	 * @param h The vertical size
	 */
	public Platform(Texture t, float x, float y, float w, float h) {
		super(t);
		super.setX(x);
		super.setY(y);
		super.setSize(w, h);
		this.collideTop = true;
		this.collideLeft = false;
		this.collideRight = false;
		this.collideBottom = false;
	}

	/**
	 * Basically just a placeholder of values for the player to interact with
	 * 
	 * @param t             The texture to draw when rendering the platform
	 * @param x             The horizontal position
	 * @param y             The vertical position
	 * @param w             The horizontal size
	 * @param h             The vertical size
	 * @param collideTop    Whether or not the player can collide with the top of
	 *                      the platform
	 * @param collideLeft   Whether or not the player can collide with the left of
	 *                      the platform
	 * @param collideRight  Whether or not the player can collide with the right of
	 *                      the platform
	 * @param collideBottom Whether or not the player can collide with the bottom of
	 *                      the platform
	 */
	public Platform(Texture t, float x, float y, float w, float h, boolean collideTop, boolean collideLeft,
			boolean collideRight, boolean collideBottom) {
		super(t);
		super.setX(x);
		super.setY(y);
		super.setSize(w, h);
		this.collideTop = collideTop;
		this.collideLeft = collideLeft;
		this.collideRight = collideRight;
		this.collideBottom = collideBottom;
	}

	/**
	 * Basically just a placeholder of values for the player to interact with
	 * 
	 * @param t             The texture to draw when rendering the platform
	 * @param x             The horizontal position
	 * @param y             The vertical position
	 * @param w             The horizontal size
	 * @param h             The vertical size
	 * @param collideTop    Whether or not the player can collide with the top of
	 *                      the platform
	 * @param collideLeft   Whether or not the player can collide with the left of
	 *                      the platform
	 * @param collideRight  Whether or not the player can collide with the right of
	 *                      the platform
	 * @param collideBottom Whether or not the player can collide with the bottom of
	 *                      the platform
	 * @param canHarm       Whether or not the player can be hurt by this platform
	 */
	public Platform(Texture t, float x, float y, float w, float h, boolean collideTop, boolean collideLeft,
			boolean collideRight, boolean collideBottom, boolean canHarm) {
		super(t);
		super.setX(x);
		super.setY(y);
		super.setSize(w, h);
		this.collideTop = collideTop;
		this.collideLeft = collideLeft;
		this.collideRight = collideRight;
		this.collideBottom = collideBottom;
		this.canHarm = canHarm;
	}

	/**
	 * Basically just a placeholder of values for the player to interact with
	 * 
	 * @param t             The texture to draw when rendering the platform
	 * @param x             The horizontal position
	 * @param y             The vertical position
	 * @param w             The horizontal size
	 * @param h             The vertical size
	 * @param collideTop    Whether or not the player can collide with the top of
	 *                      the platform
	 * @param collideLeft   Whether or not the player can collide with the left of
	 *                      the platform
	 * @param collideRight  Whether or not the player can collide with the right of
	 *                      the platform
	 * @param collideBottom Whether or not the player can collide with the bottom of
	 *                      the platform
	 * @param canHarm       Whether or not the player can be hurt by this platform
	 * @param endsLevel     Whether or not the player can end the level with this
	 *                      platform
	 */
	public Platform(Texture t, float x, float y, float w, float h, boolean collideTop, boolean collideLeft,
			boolean collideRight, boolean collideBottom, boolean canHarm, boolean endsLevel) {
		super(t);
		super.setX(x);
		super.setY(y);
		super.setSize(w, h);
		this.collideTop = collideTop;
		this.collideLeft = collideLeft;
		this.collideRight = collideRight;
		this.collideBottom = collideBottom;
		this.canHarm = canHarm;
		this.endsLevel = endsLevel;
	}

	public Platform(Texture t, float x, float y, float w, float h, boolean collideTop, boolean collideLeft,
			boolean collideRight, boolean collideBottom, boolean canHarm, boolean endsLevel, boolean collideEnemy) {
		super(t);
		super.setX(x);
		super.setY(y);
		super.setSize(w, h);
		this.collideTop = collideTop;
		this.collideLeft = collideLeft;
		this.collideRight = collideRight;
		this.collideBottom = collideBottom;
		this.canHarm = canHarm;
		this.endsLevel = endsLevel;
		this.collidesEnemy = collideEnemy;
	}

	public Platform(Texture t, float x, float y, float w, float h, boolean collideTop, boolean collideLeft,
			boolean collideRight, boolean collideBottom, boolean canHarm, boolean endsLevel, boolean collideEnemy,
			boolean trampolineBehavior) {
		super(t);
		super.setX(x);
		super.setY(y);
		super.setSize(w, h);
		this.collideTop = collideTop;
		this.collideLeft = collideLeft;
		this.collideRight = collideRight;
		this.collideBottom = collideBottom;
		this.canHarm = canHarm;
		this.endsLevel = endsLevel;
		this.collidesEnemy = collideEnemy;
		this.trampolineBehavior = trampolineBehavior;
	}

	public Platform(Texture t, float x, float y, float w, float h, int posX, int posY) {
		super(t);
		super.setX(x);
		super.setY(y);
		super.setSize(w, h);
		this.posX = posX;
		this.posY = posY;
	}

	public void updateTexture(ArrayList<Platform> platforms, int[][] data) {
		boolean grass = this.getTexture().equals(GRASS);
		boolean dirt = this.getTexture().equals(DIRT);
		boolean road = this.getTexture().equals(ROAD);
		boolean conc = this.getTexture().equals(CONC);

		if (!grass && !dirt && !road && !conc)
			return;

		boolean top, bottom, right, left;
		top = bottom = right = left = false;

		if (posY > 0) {
			top = data[posY - 1][posX] != -1 && data[posY - 1][posX] != -7864299 && data[posY - 1][posX] != -16777216
					&& data[posY - 1][posX] != -6075996 && data[posY - 1][posX] != -65408
					&& data[posY - 1][posX] != -20791 && data[posY - 1][posX] != -9072273
					&& data[posY - 1][posX] != -12843677 && data[posY - 1][posX] != -6837608;
		}

		if (posY < data.length - 1) {
			bottom = data[posY + 1][posX] != -1 && data[posY + 1][posX] != -7864299 && data[posY + 1][posX] != -16777216
					&& data[posY + 1][posX] != -6075996 && data[posY + 1][posX] != -65408
					&& data[posY + 1][posX] != -20791 && data[posY + 1][posX] != -9072273
					&& data[posY + 1][posX] != -12843677 && data[posY + 1][posX] != -6837608;
		}

		if (posX > 0) {
			left = data[posY][posX - 1] != -1 && data[posY][posX - 1] != -7864299 && data[posY][posX - 1] != -16777216
					&& data[posY][posX - 1] != -6075996 && data[posY][posX - 1] != -65408
					&& data[posY][posX - 1] != -20791 && data[posY][posX - 1] != -9072273
					&& data[posY][posX - 1] != -12843677 && data[posY][posX - 1] != -6837608;
		}

		if (posX < data[posY].length - 1) {
			right = data[posY][posX + 1] != -1 && data[posY][posX + 1] != -7864299 && data[posY][posX + 1] != -16777216
					&& data[posY][posX + 1] != -6075996 && data[posY][posX + 1] != -65408
					&& data[posY][posX + 1] != -20791 && data[posY][posX + 1] != -9072273
					&& data[posY][posX + 1] != -12843677 && data[posY][posX + 1] != -6837608;
		}

		if (!top && !bottom && !right && left) {
			if (!(LevelSelectScreen.levelNumber == 4)) {
				setTexture(new Texture("assets/Tilesets/Tutorial Tileset/rightplat.png"));
			}	else {
				setTexture(new Texture("assets/Tilesets/City Tileset/rightbeam.png"));
			}
			collideTop = true;
		} else if (!top && !bottom && !right && !left) {
			if (!(LevelSelectScreen.levelNumber == 4)) {
				if (grass) {
					setTexture(new Texture("assets/Tilesets/Tutorial Tileset/variants/grassn.png"));
				} else {
					setTexture(new Texture("assets/Tilesets/Tutorial Tileset/variants/dirtn.png"));
				}
			}	else {
				
				if (road) {
					setTexture(new Texture("assets/Tilesets/City Tileset/road.png"));
				} else {
					setTexture(new Texture("assets/Tilesets/City Tileset/concrete.png"));
				}
			}
			collideTop = true;
		} else if (!top && !bottom && right && left) {
			if (!(LevelSelectScreen.levelNumber == 4)) {
				setTexture(new Texture("assets/Tilesets/Tutorial Tileset/normalplat.png"));
			}	else {
				setTexture(new Texture("assets/Tilesets/City Tileset/middlebeam.png"));
			}
			collideTop = true;
		} else if (!top && !bottom && right && !left) {
			if (!(LevelSelectScreen.levelNumber == 4)) {
				setTexture(new Texture("assets/Tilesets/Tutorial Tileset/leftplat.png"));
			}	else {
				setTexture(new Texture("assets/Tilesets/City Tileset/leftbeam.png"));
			}
			collideTop = true;
		} else if (!top && bottom && !right && left) {
			if (!(LevelSelectScreen.levelNumber == 4)) {
				if (grass) {
					setTexture(new Texture("assets/Tilesets/Tutorial Tileset/variants/grassr.png"));
				} else {
					setTexture(new Texture("assets/Tilesets/Tutorial Tileset/variants/dirttr.png"));
				}
			}	else {
				if (road) {
					setTexture(new Texture("assets/Tilesets/City Tileset/road.png"));
				} else {
					setTexture(new Texture("assets/Tilesets/City Tileset/concrete.png"));
				}
			}
			collideTop = collideRight = true;
		} else if (!top && bottom && !right && !left) {
			if (!(LevelSelectScreen.levelNumber == 4)) {
				if (grass) {
					setTexture(new Texture("assets/Tilesets/Tutorial Tileset/variants/grasslr.png"));
				} else {
					setTexture(new Texture("assets/Tilesets/Tutorial Tileset/variants/dirtlr.png"));
				}
			}	else {
				if (road) {
					setTexture(new Texture("assets/Tilesets/City Tileset/road.png"));
				} else {
					setTexture(new Texture("assets/Tilesets/City Tileset/concrete.png"));
				}
			}
			collideTop = collideRight = collideLeft = true;
		} else if (!top && bottom && right && left) {
			if (!(LevelSelectScreen.levelNumber == 4)) {
				if (grass) {
					setTexture(new Texture("assets/Tilesets/Tutorial Tileset/ground.png"));
				} else {
					setTexture(new Texture("assets/Tilesets/Tutorial Tileset/variants/dirtt.png"));
				}
			}	else {
				if (road) {
					setTexture(new Texture("assets/Tilesets/City Tileset/road.png"));
				} else {
					setTexture(new Texture("assets/Tilesets/City Tileset/concrete.png"));
				}
			}	
			setFlip(Math.random() <= 0.5, false);
			collideTop = collideRight = collideLeft = true;
		} else if (!top && bottom && right && !left) {
			if (!(LevelSelectScreen.levelNumber == 4)) {
				if (grass) {
					setTexture(new Texture("assets/Tilesets/Tutorial Tileset/variants/grassl.png"));
				} else {
					setTexture(new Texture("assets/Tilesets/Tutorial Tileset/variants/dirttl.png"));
				}
			}	else {
				if (road) {
					setTexture(new Texture("assets/Tilesets/City Tileset/road.png"));
				} else {
					setTexture(new Texture("assets/Tilesets/City Tileset/concrete.png"));
				}
			}		
			collideTop = collideLeft = true;
		} else if (top && !bottom && !right && left) {
			if (!(LevelSelectScreen.levelNumber == 4)) {
				setTexture(new Texture("assets/Tilesets/Tutorial Tileset/variants/dirtbr.png"));
			}	else {
				setTexture(new Texture("assets/Tilesets/City Tileset/concrete.png"));
			}
			collideBottom = collideRight = true;
		} else if (top && !bottom && !right && !left) {
			if (!(LevelSelectScreen.levelNumber == 4)) {
				setTexture(new Texture("assets/Tilesets/Tutorial Tileset/variants/dirtblr.png"));
			}	else {
				setTexture(new Texture("assets/Tilesets/City Tileset/concrete.png"));
			}
			collideBottom = collideLeft = collideRight = true;
		} else if (top && !bottom && right && left) {
			if (!(LevelSelectScreen.levelNumber == 4)) {
				setTexture(new Texture("assets/Tilesets/Tutorial Tileset/variants/dirtb.png"));
			}	else {
				setTexture(new Texture("assets/Tilesets/City Tileset/concrete.png"));
			}
			setFlip(Math.random() <= 0.5, false);
			collideBottom = true;
		} else if (top && !bottom && right && !left) {
			if (!(LevelSelectScreen.levelNumber == 4)) {
				setTexture(new Texture("assets/Tilesets/Tutorial Tileset/variants/dirtbl.png"));
			}	else {
				setTexture(new Texture("assets/Tilesets/City Tileset/concrete.png"));
			}
			collideBottom = collideLeft = true;
		} else if (top && bottom && !right && left) {
			if (!(LevelSelectScreen.levelNumber == 4)) {
				setTexture(new Texture("assets/Tilesets/Tutorial Tileset/variants/dirtr.png"));
			}	else {
				setTexture(new Texture("assets/Tilesets/City Tileset/concrete.png"));
			}
			collideRight = true;
		} else if (top && bottom && !right && !left) {
			if (!(LevelSelectScreen.levelNumber == 4)) {
				setTexture(new Texture("assets/Tilesets/Tutorial Tileset/variants/dirtlr.png"));
			}	else {
				setTexture(new Texture("assets/Tilesets/City Tileset/concrete.png"));
			}
			collideRight = true;
		} else if (top && bottom && right && left) {
			if (!(LevelSelectScreen.levelNumber == 4)) {
				setTexture(new Texture("assets/Tilesets/Tutorial Tileset/filledplat.png"));
			}	else {
				setTexture(new Texture("assets/Tilesets/City Tileset/concrete.png"));
			}
			setFlip(Math.random() <= 0.5, Math.random() <= 0.5);
		} else if (top && bottom && right && !left) {
			if (!(LevelSelectScreen.levelNumber == 4)) {
				setTexture(new Texture("assets/Tilesets/Tutorial Tileset/variants/dirtl.png"));
			}	else {
				setTexture(new Texture("assets/Tilesets/City Tileset/concrete.png"));
			}
			collideLeft = true;
		} else {
			System.err.println("\n\n\n...what");
		}
	}
}