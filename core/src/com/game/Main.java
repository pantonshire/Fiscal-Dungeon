package com.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.audio.SoundEffects;

public class Main extends ApplicationAdapter {
	
	SpriteBatch batch;
	Texture img;
	
	public void create() {
		batch = new SpriteBatch();
		img = new Texture("textures/gem_red.png");
		SoundEffects.instance.loadSounds("blast", "boom", "coin", "good", "hurt", "select");
		SoundEffects.instance.play("coin", 1, 1, 0);
	}
	
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	public void dispose() {
		batch.dispose();
		img.dispose();
		SoundEffects.instance.dispose();
	}
}
