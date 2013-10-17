package de.xftl.game.framework;

import java.util.HashMap;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.xftl.game.states.MainMenuScreen;
import de.xftl.game.states.TestGameScreen;
import de.xftl.spec.game.Game;

public class XftlGameRenderer implements ApplicationListener {
	
		private OrthographicCamera _camera;
		private SpriteBatch _spriteBatch;
		private HashMap<GameScreenName, GameScreen> _gameScreensByGameScreenName;
		private GameScreen _currentGameScreen;
		private Game _gameModel;
		private Mouse _mouse;
		private ResourceManager _resourceManager;
		
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
		
		private void addGameScreen(GameScreenName gameScreenName, GameScreen gameScreen){
			_gameScreensByGameScreenName.put(gameScreenName, gameScreen);
		}
		
		public void setCurrentGameState(GameScreenName gameScreenName) {
			_currentGameScreen = _gameScreensByGameScreenName.get(gameScreenName);
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
        	
        	addGameScreen(GameScreenName.TestState, new TestGameScreen(this));
        	addGameScreen(GameScreenName.MainMenuState, new MainMenuScreen(this));
        	setCurrentGameState(GameScreenName.MainMenuState);
        }

        public void render () {
        	_mouse.update();
        	float elapsedTime = Gdx.graphics.getDeltaTime();
        	_currentGameScreen.onUpdate(elapsedTime);
        	
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
        	_resourceManager.dispose();
        	_spriteBatch.dispose();
        }
}