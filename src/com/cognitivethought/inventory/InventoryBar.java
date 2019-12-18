package com.cognitivethought.inventory;

import java.io.FileNotFoundException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.cognitivethought.entity.TreePlayer;
import com.cognitivethought.resources.Resources;

public class InventoryBar implements InputProcessor {

	public static Item currentlyHeldItem;
	public static Inventory i = new Inventory();
	public static CraftingGrid grid;

	public BitmapFont font;

	public BitmapFont smallFont;

	Rectangle craftingButton = new Rectangle(0, 0, 0, 0);

	public int selected;

	public float timer = 0f;
	public float timer2 = 0f;

	float relativeX, relativeY;

	private boolean highlighted = false;

	public InventoryBar(String invFile) {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(new FileHandle("assets/Fonts/times-new-roman.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 40;
		font = generator.generateFont(parameter);
		parameter.size = 20;
		smallFont = generator.generateFont(parameter);
		generator.dispose();
		try {
			i.read(invFile);
			grid = new CraftingGrid(Resources.CRAFTING_FILE);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for (Item item : i.getItems()) {
			System.out.println(
					Item.getName(item.getId()) + "(x" + item.getQuantity() + ") at slot " + item.getPosition());
		}

		currentlyHeldItem = new Item(Item.NONE, 0, 0);

		Gdx.input.setInputProcessor(this);
	}

	Vector2 relMousePos = new Vector2();
	Vector3 absMousePos = new Vector3();

	public void render(Batch b, OrthographicCamera c) {
		float y = c.position.y - (c.viewportHeight / 2) + 800;
		float x = c.position.x - (c.viewportWidth / 2) + 10;

		absMousePos.x = Gdx.input.getX();
		absMousePos.y = Gdx.input.getY();
		absMousePos.z = 0;
		c.unproject(absMousePos);
		relMousePos.x = absMousePos.x;
		relMousePos.y = absMousePos.y;

		relativeX = x;
		relativeY = y;

		mouseMoved((int) relMousePos.x, (int) relMousePos.y);

		for (int i = 0; i < InventoryBar.i.getItems().size(); i++) {
			int yDisplacement = -i * 100;
			y = c.position.y - (c.viewportHeight / 2) + yDisplacement + 800;

//			this.i.getItems().get(i).setX((int)x);
//			this.i.getItems().get(i).setY((int)y);

			if (b.isDrawing())
				b.end();
			Gdx.gl20.glEnable(GL20.GL_BLEND_SRC_ALPHA);
			Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
			ShapeRenderer sp = new ShapeRenderer();
			sp.setProjectionMatrix(c.combined);
			sp.begin(ShapeType.Filled);

			if (timer <= 0f) {
				if (Gdx.input.getInputProcessor().scrolled(-1)) {
					System.out.println(selected);
					timer = 5f;
					timer2 = 300f;
				}

				if (Gdx.input.getInputProcessor().scrolled(1)) {
					System.out.println(selected);
					timer = 5f;
					timer2 = 300f;
				}
			} else {
				timer--;
			}

			if (selected == i) {
				sp.setColor(new Color(0.5f, 0.5f, 0.5f, 0.9f));
			} else {
				sp.setColor(new Color(0.1f, 0.1f, 0.1f, 0.9f));
			}

			sp.rect(x, y, 100, 100);
			sp.setColor(new Color(1f, 1f, 1f, 1f));
			sp.rectLine(x, y, x, y + 100, 3);
			sp.rectLine(x, y + 100, x + 100, y + 100, 3);
			sp.rectLine(x + 100, y + 100, x + 100, y, 3);
			sp.rectLine(x, y, x + 100, y, 3);
			Gdx.gl20.glDisable(GL20.GL_BLEND_SRC_ALPHA);
			sp.end();
			b.begin();
			if (InventoryBar.i.getItems().get(i).getQuantity() > 0) {
				b.draw(Item.getTexture(InventoryBar.i.getItems().get(i).getId()), x + 10, y + 10, 80, 80);
				font.draw(b, "" + Integer.toString(InventoryBar.i.getItems().get(i).getQuantity()), x + 5, y + 90);
				if (selected == i && timer2 > 0f) {
					font.draw(b, Item.getName(InventoryBar.i.getItems().get(i).getId()), x + 105, y + 60);
				} else if (timer2 > 0f) {
					timer2--;
				}
			} else {
				InventoryBar.i.getItems().get(i).updateItem();
			}
		}

		y -= 100;

		b.end();
		Gdx.gl20.glEnable(GL20.GL_BLEND_SRC_ALPHA);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		ShapeRenderer sp = new ShapeRenderer();
		sp.begin(ShapeType.Filled);
		sp.setProjectionMatrix(c.combined);
		sp.setColor(highlighted ? new Color(0.5f, 0.5f, 0.5f, 0.5f) : new Color(0.1f, 0.1f, 0.1f, 0.9f));
		sp.rect(x, y, 100, 100);
		sp.setColor(new Color(1f, 1f, 1f, 1f));
		sp.rectLine(x, y, x, y + 100, 3);
		sp.rectLine(x, y + 100, x + 100, y + 100, 3);
		sp.rectLine(x + 100, y + 100, x + 100, y, 3);
		sp.rectLine(x, y, x + 100, y, 3);
		sp.end();
		Gdx.gl20.glDisable(GL20.GL_BLEND_SRC_ALPHA);
		b.begin();
		b.draw(Resources.UI_CRAFTING, x + 27, y + 7, 8 * 5, 17 * 5);

		craftingButton = new Rectangle(x, y, 100, 100);

		smallFont.draw(b, "Press [C] to Make Apples (x2)", x + 5, y - 20);
		smallFont.draw(b, "(x1 Organic Matter, x1 Seed)", x + 5, y - 40);

		if (Gdx.input.isKeyJustPressed(Keys.C)) {
			int m = 0;
			for (int i = 0; i < InventoryBar.i.getItems().size(); i++) {
				if (InventoryBar.i.getItems().get(i).getId() == Item.ORGANIC_MATTER) {
					m = InventoryBar.i.getItems().get(i).getQuantity();
				}
			}
			System.out.println(m);

			int s = 0;
			for (int i = 0; i < InventoryBar.i.getItems().size(); i++) {
				if (InventoryBar.i.getItems().get(i).getId() == Item.SEED) {
					s = InventoryBar.i.getItems().get(i).getQuantity();
				}
			}
			System.out.println(s);

			if (m >= 1 && s >= 1) {
				for (int i = 0; i < InventoryBar.i.getItems().size(); i++) {
					if (InventoryBar.i.getItems().get(i).getId() == Item.SEED) {
						InventoryBar.i.getItems().get(i).decrement();
					}
				}

				for (int i = 0; i < InventoryBar.i.getItems().size(); i++) {
					if (InventoryBar.i.getItems().get(i).getId() == Item.ORGANIC_MATTER) {
						InventoryBar.i.getItems().get(i).decrement();
					}
				}

				for (int j = 0; j < i.getItems().size(); j++) {
					if (i.getItems().get(j).getId() == Item.APPLE) {
						i.getItems().get(j).increment();
						i.getItems().get(j).increment();
						return;
					} else
						continue;
				}

				int pos = 0;
				for (; pos < i.getItems().size(); pos++) {
					if (i.getItems().get(pos).getId() == Item.NONE)
						break;
				}

				i.getItems().set(pos, new Item(Item.APPLE, 1, pos));
			}
		}

		grid.render(b, c, x + 100, y, 700f, font);

		if (currentlyHeldItem.getId() != Item.NONE) {
			if (!b.isDrawing())
				b.begin();
			b.draw(Item.getTexture(currentlyHeldItem.getId()), relMousePos.x - 25, relMousePos.y - 25, 50, 50);
			font.draw(b, "" + Integer.toString(currentlyHeldItem.getQuantity()), relMousePos.x + 25, relMousePos.y - 25);
		}
	}

	@Override
	public boolean keyDown(int arg0) {
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		return false;
	}

	@Override
	public boolean mouseMoved(int mx, int my) {
		highlighted = craftingButton.contains((float) mx, (float) my);
		TreePlayer.canShoot = !highlighted;

		Rectangle slot1 = new Rectangle(relativeX, relativeY, 100, 100);
		Rectangle slot2 = new Rectangle(relativeX, relativeY - 100, 100, 100);
		Rectangle slot3 = new Rectangle(relativeX, relativeY - 200, 100, 100);
		Rectangle slot4 = new Rectangle(relativeX, relativeY - 300, 100, 100);
		Rectangle slot5 = new Rectangle(relativeX, relativeY - 400, 100, 100);
		Rectangle slot6 = new Rectangle(relativeX, relativeY - 500, 100, 100);

		if (slot1.contains(mx, my)) {
			selected = 0;
		} else if (slot2.contains(mx, my)) {
			selected = 1;
		} else if (slot3.contains(mx, my)) {
			selected = 2;
		} else if (slot4.contains(mx, my)) {
			selected = 3;
		} else if (slot5.contains(mx, my)) {
			selected = 4;
		} else if (slot6.contains(mx, my)) {
			selected = 5;
		}

		return false;
	}

	@Override
	public boolean scrolled(int amt) {
		selected += amt;

		if (amt != 0) {
			if (selected < -1) {
				selected = -1;
			} else if (selected > 5) {
				selected = 5;
			}
		}

		return false;
	}

	@Override
	public boolean touchDown(int mx, int my, int pointer, int button) {
		if (highlighted) {
			if (grid.shown)
				grid.close();
			else
				grid.open();
		}

		Rectangle slot1 = new Rectangle(relativeX, relativeY, 100, 100);
		Rectangle slot2 = new Rectangle(relativeX, relativeY - 100, 100, 100);
		Rectangle slot3 = new Rectangle(relativeX, relativeY - 200, 100, 100);
		Rectangle slot4 = new Rectangle(relativeX, relativeY - 300, 100, 100);
		Rectangle slot5 = new Rectangle(relativeX, relativeY - 400, 100, 100);
		Rectangle slot6 = new Rectangle(relativeX, relativeY - 500, 100, 100);

		if (slot1.contains(relMousePos.x, relMousePos.y) || slot2.contains(relMousePos.x, relMousePos.y)
				|| slot3.contains(relMousePos.x, relMousePos.y) | slot4.contains(relMousePos.x, relMousePos.y)
				|| slot5.contains(relMousePos.x, relMousePos.y) || slot6.contains(relMousePos.x, relMousePos.y)) {
			if (selected != currentlyHeldItem.getPosition()) {
				if (InventoryBar.i.getItems().get(selected).getId() == currentlyHeldItem.getId()) {
					for (int i = 0; i < currentlyHeldItem.getQuantity(); i++) {
						InventoryBar.i.getItems().get(selected).increment();
					}
				} else {
					Item temp = currentlyHeldItem;
					temp.setPosition(selected);
					currentlyHeldItem = InventoryBar.i.getItems().get(selected);
					InventoryBar.i.getItems().set(selected, temp);
				}
			} else {
				if (i.getItems().get(selected).getId() == Item.NONE) {
					InventoryBar.i.getItems().set(selected, currentlyHeldItem);
					currentlyHeldItem = new Item(Item.NONE, 0, 0);
				} else {
					Item temp = currentlyHeldItem;
					temp.setPosition(selected);
					currentlyHeldItem = InventoryBar.i.getItems().get(selected);
					InventoryBar.i.getItems().set(selected, temp);
				}
			}
			TreePlayer.canShoot = false;
		} else {
			if (i.getItems().get(currentlyHeldItem.getPosition()).getId() == Item.NONE) {
				i.getItems().set(currentlyHeldItem.getPosition(), currentlyHeldItem);
			} else {
				int emptyPos = 0;
				for (; emptyPos < i.getItems().size(); emptyPos++) {
					if (i.getItems().get(emptyPos).getId() == Item.NONE) break;
				}
				if (emptyPos != -1) {
					i.getItems().set(emptyPos, currentlyHeldItem);
					currentlyHeldItem = new Item(Item.NONE, 0, 0);
				}
			}
			currentlyHeldItem = new Item(Item.NONE, 0, 0);
		}

		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		return false;
	}

	@Override
	public boolean touchUp(int mx, int my, int pointer, int button) {
		return false;
	}
}