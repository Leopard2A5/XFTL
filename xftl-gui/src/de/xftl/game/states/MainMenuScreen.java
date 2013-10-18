package de.xftl.game.states;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

import de.xftl.game.framework.GameScreenName;
import de.xftl.game.framework.ScreenChangeInformation;
import de.xftl.game.framework.XftlGameRenderer;
import de.xftl.game.framework.ui.MenuItem;
import de.xftl.game.framework.ui.MenuItem.MenuItemClickListener;
import de.xftl.game.framework.ui.UiGameScreenBase;

public class MainMenuScreen extends UiGameScreenBase {
	
	private static final String GAME_HEADING = "XFTL";
	
	private ScreenChangeInformation _changeInformation;
	private BitmapFont _mainFont;
	private float _headingWidth;
		
	public MainMenuScreen(XftlGameRenderer game) {
		super(game); 
		
		_mainFont = getResources().getBitmapFont("res/fnt/main.fnt");
		_headingWidth = _mainFont.getBounds(GAME_HEADING).width;
		
		addUiElement(new MenuItem(game, "Continue", 50, 300));
				
		MenuItem newGameMenuItem = addUiElement(new MenuItem(game, "New Game", 50, 350));
		newGameMenuItem.setMenuItemClickListener(new MenuItemClickListener() {
			
			@Override
			public void onClick() {
				_changeInformation = new ScreenChangeInformation(GameScreenName.CombatScreen, null);
			}
		});
				
		addUiElement(new MenuItem(game, "Load Game", 50, 400));
		addUiElement(new MenuItem(game, "Credits", 50, 450));
		addUiElement(new MenuItem(game, "Create Campaign", 50, 500));
		addUiElement(new MenuItem(game, "Options", 50, 550));
		addUiElement(new MenuItem(game, "Exit", 50, 600));
	}

	@Override
	public void onEnter(Object enterInformation) {
	}
	
	@Override
	public ScreenChangeInformation onUpdate(float elapsedTime) {
		_changeInformation = ScreenChangeInformation.emtpy;
		super.onUpdate(elapsedTime);
		return _changeInformation;
	}

	@Override
	public void onRender() {
		float headingPositionX = (float)Math.floor(getGame().getScreenWidth() * 0.5f - _headingWidth * 0.5f);
		_mainFont.draw(getSpriteBatch(), GAME_HEADING, headingPositionX , 10);
		
		super.onRender();
	}

	@Override
	public void onLeave() {	
	}

}
