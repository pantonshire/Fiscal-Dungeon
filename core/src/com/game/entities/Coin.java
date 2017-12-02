package com.game.entities;

import com.game.audio.SoundEffects;
import com.game.graphics.Animation;
import com.game.graphics.LayerRenderer;
import com.game.world.World;

public abstract class Coin extends Entity {
	
	public Hitbox hitbox;
	protected Animation animation;
	private String sound;
	private int value;
	protected boolean collided;
	
	public Coin(World world, double x, double y, int value, String sound) {
		super(world, x, y);
		hitbox = new Hitbox(this, 10, 10);
		this.value = value;
		this.sound = sound;
	}
	
	public void collect(Player player) {
		player.collectCoins(value);
		SoundEffects.instance.play(sound, 1, 1, 0);
		destroy();
	}
	
	public void updateTileCollisions() {
		boolean touchedCollidable = false;

		if(velocity.x != 0) {
			if(hitbox.collidedHorizontal(world.getTileMap())) {
				velocity.x = velocity.y = 0;
				touchedCollidable = true;
			}
		}

		if(velocity.y != 0) {
			if(hitbox.collidedVertical(world.getTileMap())) {
				velocity.x = velocity.y = 0;
				touchedCollidable = true;
			}
		}

		collided = touchedCollidable;
	}
	
	protected void updateEntity() {
		updateTileCollisions();
	}
	
	public void render(LayerRenderer renderer) {
		renderer.getSpriteBatch().draw(animation.getFrame(), (float)(position.x - animation.getFrameWidth() / 2), (float)(position.y - animation.getFrameHeight() / 2));
		animation.updateTimer();
	}
}
