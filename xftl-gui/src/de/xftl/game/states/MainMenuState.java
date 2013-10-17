package de.xftl.game.states;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

import de.xftl.game.framework.GameStateName;
import de.xftl.game.framework.XftlGameRenderer;
import de.xftl.game.framework.ui.MenuItem;
import de.xftl.game.framework.ui.MenuItem.MenuItemClickListener;
import de.xftl.game.framework.ui.UiGameStateBase;

public class MainMenuState extends UiGameStateBase {
	
	private static final String GAME_HEADING = "XFTL";
	
	private BitmapFont _mainFont;
	private float _headingWidth;
		
	public MainMenuState(XftlGameRenderer game) {
		super(game); 
		
		_mainFont = getResources().getBitmapFont("res/fnt/main.fnt");
		_headingWidth = _mainFont.getBounds(GAME_HEADING).width;
		
		MenuItem newGameMenuItem = new MenuItem(game, "New Game", 50, 350);
		newGameMenuItem.setMenuItemClickListener(new MenuItemClickListener() {
			
			@Override
			public void onClick() {
				getGame().setCurrentGameState(GameStateName.TestState);
			}
		});
		
		addUiElement(new MenuItem(game, "Continue", 50, 300));
		addUiElement(newGameMenuItem);
		addUiElement(new MenuItem(game, "Load Game", 50, 400));
		addUiElement(new MenuItem(game, "Credits", 50, 450));
		addUiElement(new MenuItem(game, "Create Campaign", 50, 500));
		addUiElement(new MenuItem(game, "Options", 50, 550));
		addUiElement(new MenuItem(game, "Exit", 50, 600));
	}

	@Override
	public void onEnter() {
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
