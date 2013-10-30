import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

import de.xftl.game.framework.XftlGameRenderer;


public class ApplicationAdapter implements ApplicationListener {

	private boolean _cursorHidden;
	private org.lwjgl.input.Cursor _emptyCursor;
	private ApplicationListener _listener;
	
	private void setHWCursorVisible(boolean visible) throws LWJGLException {
		if (Gdx.app.getType() != ApplicationType.Desktop && Gdx.app instanceof LwjglApplication)
			return;
		if (_emptyCursor == null) {
			if (Mouse.isCreated()) {
				int min = org.lwjgl.input.Cursor.getMinCursorSize();
				IntBuffer tmp = BufferUtils.createIntBuffer(min * min);
				_emptyCursor = new org.lwjgl.input.Cursor(min, min, min / 2, min / 2, 1, tmp, null);
			} else {
				throw new LWJGLException(
						"Could not create empty cursor before Mouse object is created");
			}
		}
		if (Mouse.isInsideWindow())
			Mouse.setNativeCursor(visible ? null : _emptyCursor);
	}
	
	private void hideCursor() {
		if (!_cursorHidden) {
			try {
				setHWCursorVisible(false);
			} catch (LWJGLException e) {
				e.printStackTrace();
			}
			_cursorHidden = true;
		}
	}
	
	public ApplicationAdapter(ApplicationListener originalListener) {
		_cursorHidden = false;
		_listener = originalListener;
	}
	
	@Override
	public void create() {
		_listener.create();		
	}

	@Override
	public void resize(int width, int height) {
		_listener.resize(width, height);
	}

	@Override
	public void render() {
		hideCursor();
		_listener.render();
	}

	@Override
	public void pause() {
		_listener.pause();
		
	}

	@Override
	public void resume() {
		_listener.resume();
	}

	@Override
	public void dispose() {
		_listener.dispose();
	}
}
