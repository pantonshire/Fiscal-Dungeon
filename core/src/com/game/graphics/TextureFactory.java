package com.game.graphics;

import com.badlogic.gdx.graphics.Texture;

public class TextureFactory {

	public static final String TEXTURE_PATH = "textures";
	public static final String EXTENSION_PNG = "png";
	
	public static Texture loadTexture(String path) {
		return new Texture(path);
	}
	
	public static Texture loadTexture(String folder, String fileName, String extension) {
		return loadTexture(folder + "/" + fileName + "." + extension);
	}
	
	public static Texture loadTextureByName(String fileName) {
		return loadTexture(TEXTURE_PATH, fileName, EXTENSION_PNG);
	}
}
