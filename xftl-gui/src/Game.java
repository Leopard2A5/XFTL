
import java.util.HashMap;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game implements ApplicationListener {
	
		private SpriteBatch _spriteBatch;
		private HashMap<GameStateName, GameState> _gameStatesByGameStateName;
		private GameState _currentState;
		
		public SpriteBatch getSpriteBatch() {
			return _spriteBatch;
		}
		
		public int getScreenWidth(){
			return Gdx.graphics.getWidth();
		}
		
		public int getScreenHeight(){
			return Gdx.graphics.getHeight();
		}
		
		private void addGameState(GameStateName gameStateName, GameState gameState){
			_gameStatesByGameStateName.put(gameStateName, gameState);
		}
		
		private void setCurrentGameState(GameStateName gameStateName) {
			_currentState = _gameStatesByGameStateName.get(gameStateName);
		}
	
		public void create () {
        	Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        	
        	_spriteBatch = new SpriteBatch();
        	_gameStatesByGameStateName = new HashMap<GameStateName, GameState>();
        	
        	addGameState(GameStateName.TestState, new TestGameState(this));
        	setCurrentGameState(GameStateName.TestState);
        }

        public void render () {
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