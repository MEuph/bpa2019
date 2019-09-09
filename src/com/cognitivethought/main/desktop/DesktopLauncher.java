package com.cognitivethought.main.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.cognitivethought.main.Main;

public class DesktopLauncher {
	public static void main(String args[]) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Tree Game";
		config.width = 1280;
		config.height = 720;
		new LwjglApplication(new Main(), config);
	}
}
