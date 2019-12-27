package com.cognitivethought.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class PauseScreen {
	
	Rectangle resumeButton;
	Rectangle settingsButton;
	Rectangle quitButton;
	
	int x, y;
	
	public PauseScreen() {
		
	}
	
	public void updateClick(int mx, int my) {
		
	}
	
	public void updateMove(int mx, int my) {
		
	}
	
	public void render(Batch b, OrthographicCamera c, ShapeRenderer sp, int w, int h) {
		if (b.isDrawing()) b.end();
		if (!sp.isDrawing()) sp.begin(ShapeType.Filled);
		sp.setProjectionMatrix(c.combined);
		sp.setColor(new Color(0.1f, 0.1f, 0.1f, 1f));
		sp.rect(x, y, 100, 100);
		sp.setColor(new Color(1f, 1f, 1f, 1f));
		sp.rectLine(x, y, x, y + 100, 3);
		sp.rectLine(x, y + 100, x + 100, y + 100, 3);
		sp.rectLine(x + 100, y + 100, x + 100, y, 3);
		sp.rectLine(x, y, x + 100, y, 3);
		if (sp.isDrawing()) sp.end();
		Gdx.gl20.glDisable(GL20.GL_BLEND_SRC_ALPHA);
		if (!b.isDrawing()) b.begin();
	}
}