package de.xftl.game.framework;

import java.util.HashMap;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.xftl.game.states.MainMenuState;
import de.xftl.game.states.TestGameState;
import de.xftl.spec.game.Game;

public class XftlGameRenderer implements ApplicationListener {
	
		private OrthographicCamera _camera;
		private SpriteBatch _spriteBatch;
		private HashMap<GameStateName, GameState> _gameStatesByGameStateName;
		private HashMap<String, Texture> _texturesByPath;
		private HashMap<String, BitmapFont> _bitmapFontsByPath;
		private GameState _currentState;
		@SuppressWarnings("unused")
		private Game _gameModel;
		private Mouse _mouse;
		
		public XftlGameRenderer(Game game) {
			_gameModel = game;
		}
		
		public Texture getTexture(String path){
			Texture texture = _texturesByPath.get(path);
			
			if (texture == null) {
				texture = new Texture(Gdx.files.internal(path));
				_texturesByPath.put(path, texture);
			}
			
			return texture;
		}
		
		public BitmapFont getBitmapFont(String path){
			BitmapFont font = _bitmapFontsByPath.get(path);
			
			if (font == null) {
				font = new BitmapFont(Gdx.files.internal(path), true);
				_bitmapFontsByPath.put(path, font);
			}
			
			return font;
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
		
		private void addGameState(GameStateName gameStateName, GameState gameState){
			_gameStatesByGameStateName.put(gameStateName, gameState);
		}
		
		public void setCurrentGameState(GameStateName gameStateName) {
			_currentState = _gameStatesByGameStateName.get(gameStateName);
		}
	
		public void create () {
        	Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        	
        	_spriteBatch = new SpriteBatch();
        	_mouse = new Mouse();
        	_gameStatesByGameStateName = new HashMap<GameStateName, GameState>();
        	_texturesByPath = new HashMap<String, Texture>();
        	_bitmapFontsByPath = new HashMap<String, BitmapFont>();
        	_camera = new OrthographicCamera();
        	_camera.setToOrtho(true, 1024, 768);
        	_spriteBatch.setProjectionMatrix(_camera.combined);
        	
        	addGameState(GameStateName.TestState, new TestGameState(this));
        	addGameState(GameStateName.MainMenuState, new MainMenuState(this));
        	setCurrentGameState(GameStateName.MainMenuState);
        }

        public void render () {
        	_mouse.update();
        	float elapsedTime = Gdx.graphics.getDeltaTime();
        	_currentState.onUpdate(elapsedTime);
        	_currentState.onRender();
        }

        public void resize (int width, int height) {
        }

        public void pause () {
        }

        public void resume () {
        }

        public void dispose () {
        	_spriteBatch.dispose();
        }
}