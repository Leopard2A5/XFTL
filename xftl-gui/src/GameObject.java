
public class GameObject {
	
	private Game _game;
	
	protected GameObject(Game game){
		_game = game;
	}
	
	public Game getGame() {
		return _game;
	}
}
