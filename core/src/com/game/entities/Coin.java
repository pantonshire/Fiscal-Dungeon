package com.game.entities;

import com.game.audio.SoundEffects;
import com.game.currency.Currency;
import com.game.graphics.Animation;
import com.game.graphics.LayerRenderer;
import com.game.world.World;

public abstract class Coin extends Entity {
	
	public Hitbox hitbox;
	protected Animation animation;
	private String sound;
	private int value;
	protected boolean collided;
	
	//Pushing coins
	protected boolean pushed;
	protected double pushAngle;
	protected double pushSpeed;
	protected double finalSpeed;
	protected double pushDeceleration;
	
	public Coin(World world, double x, double y, int value, String sound) {
		super(world, x, y);
		hitbox = new Hitbox(this, 10, 10);
		this.value = value;
		this.sound = sound;
	}
	
	public void collect(Player player) {
		Currency.instance.collectCoins(world, value);
		SoundEffects.instance.play(sound, 1, 1, 0);
		destroy();
	}
	
	public void push(double angle, double speed, double finalSpeed, double deceleration) {
		this.pushAngle = angle;
		this.pushSpeed = speed;
		this.finalSpeed = finalSpeed;
		this.pushDeceleration = deceleration;
//		velocity.setAngle(angle, speed);
		pushed = true;
	}
	
	protected void updateTileCollisions() {
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
	
	private void updatePushVelocity() {
		pushSpeed -= pushDeceleration;
		if(pushSpeed <= finalSpeed) {
			pushSpeed = finalSpeed;
			pushed = false;
		}
		
		velocity.setAngle(pushAngle, pushSpeed);
	}
	
	protected void updateEntity() {
		if(pushed) { updatePushVelocity(); }
		updateTileCollisions();
	}
	
	public void render(LayerRenderer renderer) {
		renderer.getSpriteBatch().draw(animation.getFrame(), (float)(position.x - animation.getFrameWidth() / 2), (float)(position.y - animation.getFrameHeight() / 2));
		animation.updateTimer();
	}
}
