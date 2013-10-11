import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;

public class TestGameState extends GameStateBase {

	protected TestGameState(Game game) {
		super(game);
	}

	@Override
	public void onEnter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdate(float elapsedTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRender() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		getGame().getSpriteBatch().begin();
		getGame().getSpriteBatch().end();
	}

	@Override
	public void onLeave() {
		// TODO Auto-generated method stub
		
	}

}
