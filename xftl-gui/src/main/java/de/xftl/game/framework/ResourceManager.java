package de.xftl.game.framework;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

public class ResourceManager implements Disposable {
	
	private HashMap<String, TextureAtlas> _textureAtlasByPath;
	private HashMap<String, Texture> _texturesByPath;
	private HashMap<String, BitmapFont> _bitmapFontsByPath;
	
	public ResourceManager() {
		_texturesByPath = new HashMap<String, Texture>();
    	_bitmapFontsByPath = new HashMap<String, BitmapFont>();
    	_textureAtlasByPath = new HashMap<String, TextureAtlas>();
	}
	
	public Texture getTexture(String path){
		Texture texture = _texturesByPath.get(path);
		
		if (texture == null) {
			texture = new Texture(Gdx.files.internal(path));
			_texturesByPath.put(path, texture);
		}
		
		return texture;
	}
	
	public TextureAtlas getAtlas(String path) {
		TextureAtlas atlas = _textureAtlasByPath.get(path);
		
		if (atlas == null) {
			atlas = new TextureAtlas(Gdx.files.internal(path));
			_textureAtlasByPath.put(path, atlas);
		}
		
		return atlas;
	}
	
	public BitmapFont getBitmapFont(String path){
		BitmapFont font = _bitmapFontsByPath.get(path);
		
		if (font == null) {
			font = new BitmapFont(Gdx.files.internal(path), true);
			_bitmapFontsByPath.put(path, font);
		}
		
		return font;
	}
	
	@Override
	public void dispose() {
		for(Disposable object : _texturesByPath.values()) {
			object.dispose();
		}
		for(Disposable object : _bitmapFontsByPath.values()) {
			object.dispose();
		}
	}
}
