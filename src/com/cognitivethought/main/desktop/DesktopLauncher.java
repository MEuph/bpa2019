package com.cognitivethought.main.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.cognitivethought.main.Main;

public class DesktopLauncher {
	public static void main(String args[]) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Casha | The Chosen One";
		config.width = 1920;
		config.height = 1080;
		config.fullscreen = true;
		config.vSyncEnabled = true;
		new LwjglApplication(new Main(), config);
	}
}