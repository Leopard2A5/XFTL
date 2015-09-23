package de.xftl.game.states;

import de.xftl.game.framework.BitmapFontSprite;
import de.xftl.game.framework.GameScreenName;
import de.xftl.game.framework.HorizontalTextAlign;
import de.xftl.game.framework.ScreenChangeInformation;
import de.xftl.game.framework.XftlGameRenderer;
import de.xftl.game.framework.ui.MenuItem;
import de.xftl.game.framework.ui.MenuItem.MenuItemClickListener;
import de.xftl.game.framework.ui.UiGameScreenBase;

public class MainMenuScreen extends UiGameScreenBase {
	
	private static final String GAME_HEADING = "XFTL";
	
	private ScreenChangeInformation _changeInformation;
	private BitmapFontSprite _headingSprite;
		
	public MainMenuScreen(XftlGameRenderer game) {
		super(game); 
		
		_headingSprite = new BitmapFontSprite(getResources().getBitmapFont("fnt/main.fnt"), GAME_HEADING);
		_headingSprite.setPosition(getGame().getScreenWidth() * 0.5f, 10);
		_headingSprite.setHorizontalAlign(HorizontalTextAlign.Center);
		
		addUiElement(new MenuItem(game, "Continue", 50, 300, false));
				
		MenuItem newGameMenuItem = addUiElement(new MenuItem(game, "New Game", 50, 350));
		newGameMenuItem.setMenuItemClickListener(new MenuItemClickListener() {
			
			@Override
			public void onClick() {
				_changeInformation = new ScreenChangeInformation(GameScreenName.CombatScreen, null);
			}
		});
				
		addUiElement(new MenuItem(game, "Load Game", 50, 400, false));
		addUiElement(new MenuItem(game, "Credits", 50, 450, false));
		addUiElement(new MenuItem(game, "Create Campaign", 50, 500, false));
		addUiElement(new MenuItem(game, "Options", 50, 550, false));
		
		MenuItem exitGameMenuItem = addUiElement(new MenuItem(game, "Exit", 50, 600));
		exitGameMenuItem.setMenuItemClickListener(new MenuItemClickListener() {
			
			@Override
			public void onClick() {
				_changeInformation = ScreenChangeInformation.quitGame;
			}
		});
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
		_headingSprite.draw(getSpriteBatch());
		
		super.onRender();
	}

	@Override
	public void onLeave() {	
	}

}
