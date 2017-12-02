package com.game.entities;

import com.game.audio.SoundEffects;
import com.game.graphics.Animation;
import com.game.graphics.LayerRenderer;
import com.game.world.World;

public abstract class Coin extends Entity {
	
	protected Animation animation;
	private String sound;
	private int value;
	
	public Coin(World world, double x, double y, int value, String sound) {
		super(world, x, y);
		this.value = value;
		this.sound = sound;
	}
	
	public void collect(Player player) {
		player.collectCoins(value);
		SoundEffects.instance.play(sound, 1, 1, 0);
		destroy();
	}
	
	public void render(LayerRenderer renderer) {
		renderer.getSpriteBatch().draw(animation.getFrame(), (float)(position.x - animation.getFrameWidth() / 2), (float)(position.y - animation.getFrameHeight() / 2));
		animation.updateTimer();
	}
}
