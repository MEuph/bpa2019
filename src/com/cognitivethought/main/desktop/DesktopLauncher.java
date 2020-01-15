package com.cognitivethought.main.desktop;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.cognitivethought.main.Main;

/**
 * Main class. IE The class that is actually run
 */
public class DesktopLauncher {
	static File logFile;
	static PrintStream err;
	
	public static void main(String args[]) throws IOException {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Casha | The Chosen One";
		config.width = Toolkit.getDefaultToolkit().getScreenSize().width;
		config.height = Toolkit.getDefaultToolkit().getScreenSize().height;
		config.fullscreen = true;
		config.vSyncEnabled = true;

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
		File f = new File("assets/Logs/" + dtf.format(LocalDateTime.now()) + ".txt");
		
		f.createNewFile();			
		
		logFile = f;
		
		err = System.err;
		
		System.setOut(new PrintStream(new FileOutputStream(f)));
		System.setErr(new PrintStream(new FileOutputStream(f)));
		
		new LwjglApplication(new Main(), config);
	}
	
	public static void log() {
		err.println("Please email the following log file to bpamisd@gmail.com: " + logFile.getAbsolutePath());
	}
}