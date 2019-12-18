package com.cognitivethought.gui;

import com.badlogic.gdx.Gdx;
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
import com.cognitivethought.inventory.Inventory;
import com.cognitivethought.inventory.InventoryBar;
import com.cognitivethought.inventory.Item;

public class InventoryInteract {
	public Inventory i = InventoryBar.i;
	public BitmapFont font;
	public int selected;
	public InventoryInteract(String invFile) {
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(new FileHandle("assets/Fonts/times-new-roman.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 40;
		font = generator.generateFont(parameter); // font size 12 pixels
		generator.dispose();
	}
	
	public void render(Batch b, OrthographicCamera c) {
		for (int i = 0; i < this.i.getItems().size(); i++) {
			int yDisplacement = -i*100;
			float y = c.position.y + yDisplacement;
			float x = c.position.x;
			
//			this.i.getItems().get(i).setX((int)x);
//			this.i.getItems().get(i).setY((int)y);
			
			b.end();
			ShapeRenderer sp = new ShapeRenderer();
			sp.setProjectionMatrix(c.combined);
			sp.begin(ShapeType.Filled);
			Gdx.gl20.glEnable(GL20.GL_BLEND_SRC_ALPHA);
			
			sp.setColor(new Color(0.1f, 0.1f, 0.1f, 1f));
			
			sp.rect(x, y, 100, 100);
			sp.setColor(new Color(1f, 1f, 1f, 1f));
			sp.rectLine(x, y, x, y+100, 3);
			sp.rectLine(x, y+100, x+100, y+100, 3);
			sp.rectLine(x+100, y+100, x+100, y, 3);
			sp.rectLine(x, y, x+100, y, 3);
			Gdx.gl20.glDisable(GL20.GL_BLEND_SRC_ALPHA);
			sp.end();
			b.begin();
			b.draw(Item.getTexture(this.i.getItems().get(i).getId()), x+10, y+10, 80, 80);
			font.draw(b, "" + Integer.toString(this.i.getItems().get(i).getQuantity()), x+5, y+90);
		}
	}
	
}
