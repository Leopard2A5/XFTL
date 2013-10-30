package de.xftl.game.framework;

import java.util.HashMap;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.xftl.game.states.*;
import de.xftl.spec.game.Game;

public class XftlGameRenderer implements ApplicationListener {
	
		private PerformanceProfilerView _profiler; 
		private DebugConsoleView _console;
		private Texture _blank;
		private OrthographicCamera _camera;
		private SpriteBatch _spriteBatch;
		private HashMap<GameScreenName, GameScreen> _gameScreensByGameScreenName;
		private GameScreen _currentGameScreen;
		private Game _gameModel;
		private Mouse _mouse;
		private ResourceManager _resourceManager;
		
		public final float TileSize = 32.0f;
		
		public XftlGameRenderer(Game game) {
			_gameModel = game;
		}
		
		public Game getGameModel() {
			return _gameModel;
		}
		
		public SpriteBatch getSpriteBatch() {
			return _spriteBatch;
		}
		
		public int getScreenWidth(){
			return Gdx.graphics.getWidth();
		}
		
		public int getScreenHeight(){
			return Gdx.graphics.getHeight();
		}
		
		public Mouse getMouse() {
			return _mouse;
		}
		
		public ResourceManager getResources() {
			return _resourceManager;
		}
		
		public Texture getBlankTexture() {
			return _blank;
		}
				
		private void addGameScreen(GameScreenName gameScreenName, GameScreen gameScreen){
			_gameScreensByGameScreenName.put(gameScreenName, gameScreen);
		}
		
		private void setCurrentGameState(GameScreenName gameScreenName, Object enterInformation) {
			if (_currentGameScreen != null) _currentGameScreen.onLeave();
			_currentGameScreen = _gameScreensByGameScreenName.get(gameScreenName);
			_currentGameScreen.onEnter(enterInformation);
		}
		
		public void clearScreen(float r, float g, float b) {
			Gdx.gl.glClearColor(r, g, b, 1.0f);
			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		}
	
		public void create () {
        	_spriteBatch = new SpriteBatch();
        	_mouse = new Mouse();
        	_gameScreensByGameScreenName = new HashMap<GameScreenName, GameScreen>();
        	_camera = new OrthographicCamera();
        	_resourceManager = new ResourceManager();
        	
        	_camera.setToOrtho(true, 1024, 768);
        	_spriteBatch.setProjectionMatrix(_camera.combined);
        	
        	Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        	
        	Pixmap map = new Pixmap(1, 1, Format.RGBA8888);
        	map.drawPixel(0, 0, 0xffffffff);
        	_blank = new Texture(map);
        	
        	_profiler = new PerformanceProfilerView(this);
        	_console = new DebugConsoleView(this);
        	
        	addGameScreen(GameScreenName.CombatScreen, new CombatScreen(this));
        	addGameScreen(GameScreenName.MainMenuState, new MainMenuScreen(this));
        	setCurrentGameState(GameScreenName.MainMenuState, null);
        }
		
		public void setIsProfilerEnabled(boolean isTrue) {
			_profiler.setIsEnabled(isTrue);
		}

        public void render () {
        	float elapsedTime = Gdx.graphics.getDeltaTime();
        	if (Gdx.input.isKeyPressed(Keys.Q)) _profiler.toggleIsEnabled();

        	updateCurrentState(elapsedTime);
        	renderCurrentState();
        	
        	_profiler.update(elapsedTime);
        	
        	_profiler.draw();
        	_console.draw();
        }
        
        private void updateCurrentState(float elapsedTime) {
        	_mouse.update();
        	_gameModel.update(elapsedTime);
        	ScreenChangeInformation changeInformation = _currentGameScreen.onUpdate(elapsedTime);
        	
        	if (changeInformation != ScreenChangeInformation.emtpy) {
        		
        		if (changeInformation.getQuitGame()) {
        			Gdx.app.exit();
        		} 
        		else {
        			setCurrentGameState(changeInformation.getGameScreenName(), changeInformation.getEnterInformation());	
        		}
        	}
        }
        
        private void renderCurrentState() {
    		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        	_spriteBatch.begin();
        	_currentGameScreen.onRender();
        	_spriteBatch.end();
        }

        public void resize (int width, int height) {
        }

        public void pause () {
        }

        public void resume () {
        }

        public void dispose () {
        	_blank.dispose();
        	_resourceManager.dispose();
        	_spriteBatch.dispose();
        }
}