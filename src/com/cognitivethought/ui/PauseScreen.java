package com.cognitivethought.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class PauseScreen {
	
	Rectangle resumeButton = new Rectangle(0, 0, 0, 0);
	Rectangle settingsButton = new Rectangle(0, 0, 0, 0);
	Rectangle quitButton = new Rectangle(0, 0, 0, 0);
	
	int x, y;
	
	public PauseScreen() {
		
	}
	
	public void updateClick(int mx, int my) {
		
	}
	
	public void updateMove(int mx, int my) {
		
	}
	
	public void render(Batch b, OrthographicCamera c, ShapeRenderer sp, BitmapFont font, int w, int h) {
		x = (int)(c.position.x - (w / 2));
		y = (int)(c.position.y - (h / 2));
		
		resumeButton.set(x, y, w, 100);
		settingsButton.set(x, y+100, w, 100);
		quitButton.set(x, y+200, w, 100);
		
		if (b.isDrawing()) b.end();
		if (!sp.isDrawing()) sp.begin(ShapeType.Filled);
		sp.setProjectionMatrix(c.combined);
		sp.setColor(new Color(0.1f, 0.1f, 0.1f, 1f));
		sp.rect(x, y, w, h);
		sp.setColor(new Color(1f, 1f, 1f, 1f));
		sp.rectLine(x, y, x, y + h, 3);
		sp.rectLine(x, y + h, x + w, y + h, 3);
		sp.rectLine(x + w, y + h, x + w, y, 3);
		sp.rectLine(x, y, x + w, y, 3);
		sp.setColor(new Color(0.1f, 0.1f, 0.1f, 1f));
		sp.rect(resumeButton.x, resumeButton.y, resumeButton.width, resumeButton.height);
		sp.setColor(new Color(1f, 1f, 1f, 1f));
		sp.rectLine(resumeButton.x, resumeButton.y, resumeButton.x, resumeButton.y + resumeButton.height, 3);
		sp.rectLine(resumeButton.x, resumeButton.y + resumeButton.height, resumeButton.x + resumeButton.width, resumeButton.y + resumeButton.height, 3);
		sp.rectLine(resumeButton.x + resumeButton.width, resumeButton.y + resumeButton.height, resumeButton.x + resumeButton.width, resumeButton.y, 3);
		sp.rectLine(resumeButton.x, resumeButton.y, resumeButton.x + resumeButton.width, resumeButton.y, 3);
		sp.setColor(new Color(0.1f, 0.1f, 0.1f, 1f));
		sp.rect(settingsButton.x, settingsButton.y, settingsButton.width, settingsButton.height);
		sp.setColor(new Color(1f, 1f, 1f, 1f));
		sp.rectLine(settingsButton.x, settingsButton.y, settingsButton.x, settingsButton.y + settingsButton.height, 3);
		sp.rectLine(settingsButton.x, settingsButton.y + settingsButton.height, settingsButton.x + settingsButton.width, settingsButton.y + settingsButton.height, 3);
		sp.rectLine(settingsButton.x + settingsButton.width, settingsButton.y + settingsButton.height, settingsButton.x + settingsButton.width, settingsButton.y, 3);
		sp.rectLine(settingsButton.x, settingsButton.y, settingsButton.x + settingsButton.width, settingsButton.y, 3);
		sp.setColor(new Color(0.1f, 0.1f, 0.1f, 1f));
		sp.rect(quitButton.x, quitButton.y, quitButton.width, quitButton.height);
		sp.setColor(new Color(1f, 1f, 1f, 1f));
		sp.rectLine(quitButton.x, quitButton.y, quitButton.x, quitButton.y + quitButton.height, 3);
		sp.rectLine(quitButton.x, quitButton.y + quitButton.height, quitButton.x + quitButton.width, quitButton.y + quitButton.height, 3);
		sp.rectLine(quitButton.x + quitButton.width, quitButton.y + quitButton.height, quitButton.x + quitButton.width, quitButton.y, 3);
		sp.rectLine(quitButton.x, quitButton.y, quitButton.x + quitButton.width, quitButton.y, 3);
		if (sp.isDrawing()) sp.end();
		
		if (!b.isDrawing()) b.begin();
		font.draw(b, "Resume", resumeButton.x, resumeButton.y + font.getData().lineHeight);
		font.draw(b, "Settings", settingsButton.x, settingsButton.y + font.getData().lineHeight);
		font.draw(b, "Quit", quitButton.x, quitButton.y + font.getData().lineHeight);
		if (b.isDrawing()) b.end();
		
		Gdx.gl20.glDisable(GL20.GL_BLEND_SRC_ALPHA);
		if (!b.isDrawing()) b.begin();
	}
}