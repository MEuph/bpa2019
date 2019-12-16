package com.cognitivethought.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.cognitivethought.inventory.Inventory;
import com.cognitivethought.inventory.Item;
import com.cognitivethought.level.Level;
import com.cognitivethought.level.parts.Platform;

public class ItemDrop extends Sprite {
	
	public Texture t;
	
	public float x, y, w, h;
	public float dx, dy;
	public float g = .198f;
	public int id;
	public boolean remove = false;
	
	public ItemDrop(Texture t, int x, int y, int w, int h, int id) {
		setTexture(t);
		this.t = t;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.id = id;
	}
	
	public void update (Level l, Inventory i, TreePlayer p) {
		setSize(w, h);
		setX(x);
		setY(y);
		
		setBounds(x, y, w, h);
		
		if (dy > -15f) dy -= g; else dy = -15f;
		
		for (Platform plat : l.getPlatforms()) {
//			if (!(plat.getX() > getX() - 200 && plat.getX() < getX() + 200 && plat.getY() > getY() - 200 && plat.getY() < getY() + 200)) break;
			
			if (new Rectangle(plat.getX()+1f, plat.getY()+1f, plat.getWidth()-2f, plat.getHeight()-2f).overlaps(getBoundingRectangle()) && dy < 0 && getY() >= plat.getY() + (plat.getHeight() / 2) + dy && plat.collideTop) {
				dy = 0;
				dx = 0;
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
				dx = 0;
				setX(plat.getX() - getWidth()); // Reset x position to the left of the platform
			}
			
			Rectangle rightOfPlatform = new Rectangle(plat.getX()+plat.getWidth()-2f, plat.getY(), 2f, plat.getHeight());
			if (rightOfPlatform.overlaps(getBoundingRectangle()) && dx < 0 && getX() <= plat.getX() + plat.getWidth() && plat.collideRight && !(getY()>(plat.getY()+plat.getHeight())-4)) {
				dx = 0;
				setX(plat.getX() + getWidth()); // Reset x position to the right of the platform
			}
		}
		
		if (p.getBoundingRectangle().overlaps(getBoundingRectangle())) {
			pickUp(l, i);
		}
		
		x += dx;
		y += dy;
	}
	
	public void pickUp(Level l, Inventory i) {
		remove = true;
		for (int j = 0; j < i.getItems().size(); j++) {
			if (i.getItems().get(j).getId() == this.id) {
				i.getItems().get(j).increment();
				return;
			} else continue;
		}
		
		int pos = 0;
		for (; pos < i.getItems().size(); pos++) {
			if (i.getItems().get(pos).getId() == Item.NONE) break;
		}
		
		i.getItems().set(pos, new Item(this.id, 1, pos));
	}
	
	public void render(Batch b) {
		b.draw(t, x, y, w, h);
	}
}