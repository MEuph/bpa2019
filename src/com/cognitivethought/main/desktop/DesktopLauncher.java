package com.cognitivethought.main.desktop;

import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
		
		// For logging
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		System.setOut(ps);
		
		new LwjglApplication(new Main(), config);
	}
	
	public static void log() {
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
			BufferedWriter writer = new BufferedWriter(new FileWriter("/Logs/" + dtf.format(LocalDateTime.now())));
		    writer.write(System.out.toString());
		    
		    writer.close();
		} catch (IOException e) {
			DesktopLauncher.log();
			e.printStackTrace();
		}
	}
}