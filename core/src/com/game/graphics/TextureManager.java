package com.game.graphics;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

public class TextureManager implements Disposable {
	
	public static final TextureManager instance = new TextureManager();
	
	public HashMap<String, Texture> textures;
	
	public TextureManager() {
		textures = new HashMap<String, Texture>();
	}
	
	public void loadTexture(String textureName) {
		if(!textures.containsKey(textureName)) {
			textures.put(textureName, TextureFactory.loadTextureByName(textureName));
		}
	}
	
	public void unloadTexture(String textureName) {
		if(textures.containsKey(textureName)) {
			textures.get(textureName).dispose();
			textures.remove(textureName);
		}
	}
	
	public Texture getTexture(String textureName) {
		loadTexture(textureName);
		return textures.get(textureName);
	}
	
	public void dispose() {
		for(Texture texture : textures.values()) {
			texture.dispose();
		}
		
		textures.clear();
	}
}
