import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import de.xftl.fixture.Fixture;
import de.xftl.game.framework.XftlGameRenderer;
import de.xftl.spec.game.Game;

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
		Game gameModel = Fixture.buildGame();
		XftlGameRenderer gameRenderer = new XftlGameRenderer(gameModel);
		new LwjglApplication(gameRenderer, createConfiguration());
	}
}