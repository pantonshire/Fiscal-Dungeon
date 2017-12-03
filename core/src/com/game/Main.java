package com.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.game.audio.SoundEffects;
import com.game.graphics.LayerRenderer;
import com.game.graphics.Textures;
import com.game.world.World;

public class Main extends ApplicationAdapter {
	
	private LayerRenderer gameRenderer;
	private LayerRenderer overlayRenderer;
	private World currentWorld;
	
	public void create() {
		SoundEffects.instance.loadSounds("blast", "boom", "coin", "good", "hurt", "select", "schut");
		
		gameRenderer = new LayerRenderer(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 0.5F);
		overlayRenderer = new LayerRenderer(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 0.5F);
		currentWorld = new World(gameRenderer, overlayRenderer, 100, 100);
		//First 100 x 100
		//Second 180 x 180
		//Third 240 x 240
		//Fourth 320 x 320
	}
	
	public void render() {
		clearScreen();
		
		if(currentWorld != null) {
			currentWorld.update();
			gameRenderer.beginBatch();
			currentWorld.render(0);
			gameRenderer.endBatch();
			overlayRenderer.beginBatch();
			currentWorld.render(1);
			overlayRenderer.endBatch();
		}
	}
	
	public void dispose() {
		gameRenderer.dispose();
		Textures.instance.dispose();
		SoundEffects.instance.dispose();
	}
	
	private void clearScreen() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
}
