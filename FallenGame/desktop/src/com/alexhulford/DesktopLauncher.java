package com.alexhulford;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.alexhulford.FallenGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle(FallenGame.TITLE);
		config.setWindowedMode(FallenGame.FALLEN_GAME_WIDTH, FallenGame.FALLEN_GAME_HEIGHT);
		new Lwjgl3Application(new FallenGame(), config);
	}
}
