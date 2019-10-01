package com.cognitivethought.level;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.cognitivethought.ui.HealthBar;

public class Select extends Level {
	
	public Select(String s) throws FileNotFoundException, URISyntaxException {
		super(s);
	}
	
	public Select(BufferedImage b) {
		super(b);
	}
	
	public void render(Batch b, HealthBar hb, OrthographicCamera c) {
		super.render(b, hb, c);
	}
}