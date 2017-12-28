package com.game.graphics;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.Disposable;

public class Fonts implements Disposable {

	public static final Fonts instance = new Fonts();
	
	private HashMap<String, BitmapFont> fontMap;
	
	public Fonts() {
		fontMap = new HashMap<String, BitmapFont>();
	}
	
	public void loadFont(String ttf, int size) {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/" + ttf + ".ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = size;
		parameter.mono = true;
		parameter.kerning = true;
		BitmapFont newFont = generator.generateFont(parameter);
		String referenceName = ttf + "-" + size;
		fontMap.put(referenceName, newFont);
	}
	
	public BitmapFont getFont(String name) {
		return fontMap.get(name);
	}
	
	public void dispose() {
		for(BitmapFont font : fontMap.values()) {
			font.dispose();
		}
		
		fontMap.clear();
	}
}
