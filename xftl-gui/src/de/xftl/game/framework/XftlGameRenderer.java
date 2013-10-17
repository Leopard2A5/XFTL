package de.xftl.game.framework;

import java.util.HashMap;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.xftl.game.states.MainMenuState;
import de.xftl.game.states.TestGameState;
import de.xftl.spec.game.Game;

public class XftlGameRenderer implements ApplicationListener {
	
		private OrthographicCamera _camera;
		private SpriteBatch _spriteBatch;
		private HashMap<GameStateName, GameState> _gameStatesByGameStateName;
		private GameState _currentState;
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
		
		private void addGameState(GameStateName gameStateName, GameState gameState){
			_gameStatesByGameStateName.put(gameStateName, gameState);
		}
		
		public void setCurrentGameState(GameStateName gameStateName) {
			_currentState = _gameStatesByGameStateName.get(gameStateName);
		}
	
		public void create () {
        	_spriteBatch = new SpriteBatch();
        	_mouse = new Mouse();
        	_gameStatesByGameStateName = new HashMap<GameStateName, GameState>();
        	_camera = new OrthographicCamera();
        	_resourceManager = new ResourceManager();
        	
        	_camera.setToOrtho(true, 1024, 768);
        	_spriteBatch.setProjectionMatrix(_camera.combined);
        	
        	Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        	
        	addGameState(GameStateName.TestState, new TestGameState(this));
        	addGameState(GameStateName.MainMenuState, new MainMenuState(this));
        	setCurrentGameState(GameStateName.MainMenuState);
        }

        public void render () {
        	_mouse.update();
        	float elapsedTime = Gdx.graphics.getDeltaTime();
        	_currentState.onUpdate(elapsedTime);
        	
        	Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        	
        	_spriteBatch.begin();
        	_currentState.onRender();
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