import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopGame {
	
	private static LwjglApplicationConfiguration createConfiguration(){
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.fullscreen = false;
		config.height = 768;
		config.resizable = false;
		config.title = "xftl";
		config.useGL20 = false;
		config.vSyncEnabled = true;
		config.width = 1024;
		
		return config;
	}
	
	public static void main (String[] args) {
		new LwjglApplication(new Game(), createConfiguration());
	}
}