package dk.sdu.mmmi.cbse.main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	
	public static void main(String[] args) {
		
		LwjglApplicationConfiguration cfg =
			new LwjglApplicationConfiguration();
		cfg.title = "Asteroids";
		cfg.width = 1600;
		cfg.height = 900;
		cfg.useGL30 = false;
		cfg.resizable = false;
                //cfg.fullscreen = true;
                cfg.vSyncEnabled = false;
                
		
		new LwjglApplication(new Game(), cfg);
		
	}
	
}
