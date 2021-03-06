package de.xftl.game.framework;

import java.util.HashMap;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.xftl.game.framework.ui.Cursor;
import de.xftl.game.states.*;
import de.xftl.spec.game.Game;

public class XftlGameRenderer implements ApplicationListener {
	
		private Texture _blank;
		private OrthographicCamera _camera;
		private SpriteBatch _spriteBatch;
		private HashMap<GameScreenName, GameScreen> _gameScreensByGameScreenName;
		private GameScreen _currentGameScreen;
		private Game _gameModel;
		private Mouse _mouse;
		private ResourceManager _resourceManager;
		private Cursor _cursor;
		
		public XftlGameRenderer(Game game) {
			_gameModel = game;
		}
		
		public Game getGameModel() { return _gameModel; }
		public SpriteBatch getSpriteBatch() { return _spriteBatch; }
		public int getScreenWidth() { return Gdx.graphics.getWidth(); }
		public int getScreenHeight() { return Gdx.graphics.getHeight(); }
		public Mouse getMouse() { return _mouse; }
		public ResourceManager getResources() {	return _resourceManager; }
		public Texture getBlankTexture() { return _blank; }
				
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
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
        	
        	_cursor = new Cursor(this);
        	
        	addGameScreen(GameScreenName.CombatScreen, new CombatScreen(this));
        	setCurrentGameState(GameScreenName.CombatScreen, null);
        }
		
		public void render () {
        	float elapsedTime = Gdx.graphics.getDeltaTime();

        	updateCurrentState(elapsedTime);
        	renderCurrentState();
        }
                
        private void updateCurrentState(float elapsedTime) {
        	_mouse.update();
        	_cursor.update();
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
        	Gdx.gl.glClearColor(1.0f,0.0f,0.0f, 1.0f);
    		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    		_currentGameScreen.onRender();
        	_spriteBatch.begin();
        	_cursor.draw();
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