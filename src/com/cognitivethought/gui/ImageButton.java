package com.cognitivethought.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ImageButton {
	/**
	 * A class that creates an image button from scratch
	 * 
	 * handles clicks on the buttons
	 * 
	 */
	
	
	Texture unHighlighted;
	Texture highlighted;
	
	private Sprite skin;
	private ClickListener listener;
	
	public ImageButton(Texture texture, float x, float y, float width, float height) {
		skin = new Sprite(texture);
		skin.setPosition(x, y);
		skin.setSize(width, height);
	}
	
	public ImageButton(Texture unHighlighted, Texture highlighted, float x, float y, float width, float height) {
		this.unHighlighted = unHighlighted;
		this.highlighted = highlighted;
		skin = new Sprite(unHighlighted);
		skin.setPosition(x, y);
		skin.setSize(width, height);
	}
	
	public ImageButton(Texture texture, float x, float y) {
		skin = new Sprite(texture);
		skin.setPosition(x, y);
	}

	public void update(SpriteBatch batch, float input_x, float input_y) {
		checkIfClicked(input_x, input_y);
		skin.draw(batch);
	}

	public void render(SpriteBatch b) {
		skin.draw(b);
	}

	public boolean checkIfClicked(float ix, float iy) {
		if (ix > skin.getX() && ix < skin.getX() + skin.getWidth()) {
			if (iy >= skin.getY() && iy <= skin.getY() + skin.getHeight()) {
				if (highlighted != null && unHighlighted != null)
					skin.setTexture(highlighted);
				if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
					this.listener.clicked(null, ix, iy);
				}
			}
		} else {
			if (highlighted != null && unHighlighted != null)
				skin.setTexture(unHighlighted);
		}
		return false;
	}
	
	public void setClickListener(ClickListener cl) {
		this.listener = cl;
	}
	
	public Sprite getSkin() {
		return skin;
	}
}