
public interface GameState {
	void onEnter();
	void onUpdate(float elapsedTime);
	void onRender();
	void onLeave();
}
