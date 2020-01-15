package com.cognitivethought.ui;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.cognitivethought.main.Main;
import com.cognitivethought.main.desktop.DesktopLauncher;
import com.cognitivethought.screens.GameScreen;
import com.cognitivethought.screens.SettingsScreen;

public class PauseMenu {

	Rectangle resumeButton = new Rectangle(0, 0, 0, 0);
	Rectangle settingsButton = new Rectangle(0, 0, 0, 0);
	Rectangle moreHelpButton = new Rectangle(0, 0, 0, 0);
	Rectangle quitButton = new Rectangle(0, 0, 0, 0);

	Sprite craftingSystem;
	Sprite openMenu;
	Sprite shopMenu;

	int x, y;

	int sel = -1;

	boolean moreHelp = false;

	public PauseMenu() {
		openMenu = new Sprite(new Texture("Cutscenes/more help/open menu.png"));
		craftingSystem = new Sprite(new Texture("Cutscenes/more help/crafting system.png"));
		shopMenu = new Sprite(new Texture("Cutscenes/more help/shop menu.png"));
	}

	public void updateClick(int mx, int my) {
		if (sel == -1)
			return;
		switch (sel) {
		case 0:
			GameScreen.togglePause();
			break;
		case 1:
			Main.main.setScreen(Main.main.settingsScreen);
			SettingsScreen.toResetTo = Main.main.gameScreen;
			break;
		case 2:
			Main.main.setScreen(Main.main.levelSelectScreen);
			try {
				Main.save();
			} catch (IOException e) {
				DesktopLauncher.log();
				e.printStackTrace();
			}
			GameScreen.togglePause();
			break;
		case 3:
			moreHelp = !moreHelp;
			System.out.println(moreHelp);
			break;
		}
	}

	public void updateMove(int mx, int my) {
		if (resumeButton.contains(mx, my)) {
			sel = 0;
		} else if (settingsButton.contains(mx, my)) {
			sel = 1;
		} else if (quitButton.contains(mx, my)) {
			sel = 2;
		} else if (moreHelpButton.contains(mx, my)) {
			sel = 3;
		} else {
			sel = -1;
		}
	}

	public void render(Batch b, OrthographicCamera c, ShapeRenderer sp, BitmapFont font, int w, int h) {
		x = (int) (c.position.x - (w / 2));
		y = (int) (c.position.y - (h / 2));

		openMenu.setSize(657 / 2, 736 / 2);
		openMenu.setPosition(c.position.x - (c.viewportWidth / 2),
				c.position.y - (c.viewportHeight / 2));

		craftingSystem.setSize(910 / 2, 803 / 2);
		craftingSystem.setPosition(c.position.x + c.viewportWidth - craftingSystem.getWidth() - (c.viewportWidth / 2),
				c.position.y + c.viewportHeight - craftingSystem.getHeight() - (c.viewportHeight / 2));

		shopMenu.setSize(760 / 2, 382 / 2);
		shopMenu.setPosition(c.position.x + c.viewportWidth - craftingSystem.getWidth() - (c.viewportWidth / 2),
				c.position.y - (c.viewportHeight / 2));

		resumeButton.set(x, y + 300, w, 100);
		moreHelpButton.set(x, y + 200, w, 100);
		settingsButton.set(x, y + 100, w, 100);
		quitButton.set(x, y, w, 100);

		if (b.isDrawing())
			b.end();
		if (!sp.isDrawing())
			sp.begin(ShapeType.Filled);
		sp.setProjectionMatrix(c.combined);
		sp.setColor(new Color(0.1f, 0.1f, 0.1f, 1f));
		sp.rect(x, y, w, h);
		sp.setColor(new Color(1f, 1f, 1f, 1f));
		sp.rectLine(x, y, x, y + h, 3);
		sp.rectLine(x, y + h, x + w, y + h, 3);
		sp.rectLine(x + w, y + h, x + w, y, 3);
		sp.rectLine(x, y, x + w, y, 3);
		sp.setColor(sel == 0 ? new Color(0.5f, 0.5f, 0.5f, 1f) : new Color(0.1f, 0.1f, 0.1f, 1f));
		sp.rect(resumeButton.x, resumeButton.y, resumeButton.width, resumeButton.height);
		sp.setColor(new Color(1f, 1f, 1f, 1f));
		sp.rectLine(resumeButton.x, resumeButton.y, resumeButton.x, resumeButton.y + resumeButton.height, 3);
		sp.rectLine(resumeButton.x, resumeButton.y + resumeButton.height, resumeButton.x + resumeButton.width,
				resumeButton.y + resumeButton.height, 3);
		sp.rectLine(resumeButton.x + resumeButton.width, resumeButton.y + resumeButton.height,
				resumeButton.x + resumeButton.width, resumeButton.y, 3);
		sp.rectLine(resumeButton.x, resumeButton.y, resumeButton.x + resumeButton.width, resumeButton.y, 3);
		sp.setColor(sel == 1 ? new Color(0.5f, 0.5f, 0.5f, 1f) : new Color(0.1f, 0.1f, 0.1f, 1f));
		sp.rect(settingsButton.x, settingsButton.y, settingsButton.width, settingsButton.height);
		sp.setColor(new Color(1f, 1f, 1f, 1f));
		sp.rectLine(settingsButton.x, settingsButton.y, settingsButton.x, settingsButton.y + settingsButton.height, 3);
		sp.rectLine(settingsButton.x, settingsButton.y + settingsButton.height, settingsButton.x + settingsButton.width,
				settingsButton.y + settingsButton.height, 3);
		sp.rectLine(settingsButton.x + settingsButton.width, settingsButton.y + settingsButton.height,
				settingsButton.x + settingsButton.width, settingsButton.y, 3);
		sp.rectLine(settingsButton.x, settingsButton.y, settingsButton.x + settingsButton.width, settingsButton.y, 3);
		sp.setColor(sel == 2 ? new Color(0.5f, 0.5f, 0.5f, 1f) : new Color(0.1f, 0.1f, 0.1f, 1f));
		sp.rect(quitButton.x, quitButton.y, quitButton.width, quitButton.height);
		sp.setColor(new Color(1f, 1f, 1f, 1f));
		sp.rectLine(quitButton.x, quitButton.y, quitButton.x, quitButton.y + quitButton.height, 3);
		sp.rectLine(quitButton.x, quitButton.y + quitButton.height, quitButton.x + quitButton.width,
				quitButton.y + quitButton.height, 3);
		sp.rectLine(quitButton.x + quitButton.width, quitButton.y + quitButton.height, quitButton.x + quitButton.width,
				quitButton.y, 3);
		sp.rectLine(quitButton.x, quitButton.y, quitButton.x + quitButton.width, quitButton.y, 3);
		sp.setColor(sel == 3 ? new Color(0.5f, 0.5f, 0.5f, 1f) : new Color(0.1f, 0.1f, 0.1f, 1f));
		sp.rect(moreHelpButton.x, moreHelpButton.y, moreHelpButton.width, moreHelpButton.height);
		sp.setColor(new Color(1f, 1f, 1f, 1f));
		sp.rectLine(moreHelpButton.x, moreHelpButton.y, moreHelpButton.x, moreHelpButton.y + moreHelpButton.height, 3);
		sp.rectLine(moreHelpButton.x, moreHelpButton.y + moreHelpButton.height, moreHelpButton.x + moreHelpButton.width,
				moreHelpButton.y + moreHelpButton.height, 3);
		sp.rectLine(moreHelpButton.x + moreHelpButton.width, moreHelpButton.y + moreHelpButton.height,
				moreHelpButton.x + moreHelpButton.width, moreHelpButton.y, 3);
		sp.rectLine(moreHelpButton.x, moreHelpButton.y, moreHelpButton.x + moreHelpButton.width, moreHelpButton.y, 3);
		if (sp.isDrawing())
			sp.end();

		if (!b.isDrawing())
			b.begin();
		font.draw(b, "Resume", resumeButton.x + (quitButton.width / 2) - (new GlyphLayout(font, "Resume").width / 2),
				resumeButton.y + font.getData().lineHeight);
		font.draw(b, "More Help",
				moreHelpButton.x + (quitButton.width / 2) - (new GlyphLayout(font, "More Help").width / 2),
				moreHelpButton.y + font.getData().lineHeight);
		font.draw(b, "Settings",
				settingsButton.x + (quitButton.width / 2) - (new GlyphLayout(font, "Settings").width / 2),
				settingsButton.y + font.getData().lineHeight);
		font.draw(b, "Save & Quit",
				quitButton.x + (quitButton.width / 2) - (new GlyphLayout(font, "Save & Quit").width / 2),
				quitButton.y + font.getData().lineHeight);

		if (moreHelp) {
			openMenu.draw(b);
			craftingSystem.draw(b);
			shopMenu.draw(b);
		}

		if (b.isDrawing())
			b.end();

		Gdx.gl20.glDisable(GL20.GL_BLEND_SRC_ALPHA);
		if (!b.isDrawing())
			b.begin();
	}
}