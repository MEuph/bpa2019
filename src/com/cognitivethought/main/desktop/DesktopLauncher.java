package com.cognitivethought.main.desktop;

import java.awt.Toolkit;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.cognitivethought.main.Main;

public class DesktopLauncher {
	public static void main(String args[]) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Casha | The Chosen One";
		config.width = Toolkit.getDefaultToolkit().getScreenSize().width;
		config.height = Toolkit.getDefaultToolkit().getScreenSize().height;
		config.fullscreen = true;
		config.vSyncEnabled = true;
		new LwjglApplication(new Main(), config);
	}
}