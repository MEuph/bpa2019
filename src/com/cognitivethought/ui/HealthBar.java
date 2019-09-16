package com.cognitivethought.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class HealthBar {
	
	public int health = 3;
	
	public void render(Batch b, OrthographicCamera c) {
		for (int i = 0; i < health; i++) {
			// Enable transparency blending
			Gdx.gl.glEnable(GL20.GL_BLEND);
			Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
			ShapeRenderer sp = new ShapeRenderer();
			sp.setProjectionMatrix(c.combined);
			sp.begin(ShapeType.Filled);
				sp.setColor(new Color(1f, 0f, 0f, 0.5f));
				sp.rect((c.position.x + 25 + (60*i) - c.viewportWidth / 2) / c.zoom, (c.position.y + 25 - c.viewportHeight / 2) / c.zoom, 50, 50);
			sp.end();
			// Disable transparency blending
			Gdx.gl.glDisable(GL20.GL_BLEND);
		}
	}
	
}