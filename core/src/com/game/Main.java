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
//		batch = new SpriteBatch();
//		img = new Texture("textures/gem_red.png");
		SoundEffects.instance.loadSounds("blast", "boom", "coin", "good", "hurt", "select", "schut");
//		SoundEffects.instance.play("good", 1, 1, 0);
		
//		anmtest = new Animation(TextureManager.instance.getTexture("gem_red"), Sequence.formatSequences(new Sequence(16, 14, 6, 5)));
//		anmtest = new Animation(Textures.instance.getTexture("coin"), Sequence.formatSequences(new Sequence(14, 14, 6, 8)));
		
		gameRenderer = new LayerRenderer(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 0.5F);
		overlayRenderer = new LayerRenderer(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 0.5F);
		currentWorld = new World(gameRenderer, overlayRenderer);
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
		
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.draw(anmtest.getFrame(), 30, 30);
//		anmtest.updateTimer();
//		batch.end();
	}
	
	public void dispose() {
//		batch.dispose();
//		img.dispose();
		gameRenderer.dispose();
		Textures.instance.dispose();
		SoundEffects.instance.dispose();
	}
	
	private void clearScreen() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
}
