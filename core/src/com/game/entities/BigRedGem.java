package com.game.entities;

import com.game.audio.SoundEffects;
import com.game.graphics.Animation;
import com.game.graphics.LayerRenderer;
import com.game.graphics.Sequence;
import com.game.graphics.Textures;
import com.game.utils.RandomUtils;
import com.game.world.World;

public class BigRedGem extends Enemy {
	
	private int ATTACK_RATE = 5;
	
	private Animation animation;
	private int attackTimer;
	private double angle;
	
	public BigRedGem(World world, double x, double y) {
		super(world, x, y, 30, 30, 0.25, 15);
		animation = new Animation(Textures.instance.getTexture("big_red_gem"), Sequence.formatSequences(new Sequence(32, 32, 6, 5)));
	}
	
	protected void updateEntity() {
		boolean canSee = canSee(world.getPlayer());
		
		if(attackTimer > 0) { --attackTimer; }
		if(canSee && attackTimer == 0) {
			attackTimer = ATTACK_RATE;
			world.spawn(new CoinProjectile(world, position.x, position.y, angle, 1.5));
			world.spawn(new CoinProjectile(world, position.x, position.y, angle + Math.PI, 1.5));
			angle += Math.toRadians(16);
			
			if(angle > Math.PI) {
				angle -= Math.PI * 2;
			}
		}
	}
	
	public void render(LayerRenderer renderer) {
		renderer.getSpriteBatch().draw(animation.getFrame(), (float)(position.x - (animation.getFrameWidth() / 2)), (float)(position.y - (animation.getFrameHeight() / 2)));
		animation.updateTimer();
	}
	
	protected void onDeath() {
		SoundEffects.instance.play("boom", 1, 1, 0);
		for(int i = 0; i < 10; i++) {
			Coin coin = new RedGemProjectile(world, position.x, position.y, RandomUtils.randAngle(), RandomUtils.randDouble(0.5, 2.0));
			world.spawn(coin);
		}
		
		for(int i = 0; i < 5; i++) {
			Coin projectile = new CoinProjectile(world, position.x, position.y, 2 * Math.PI / 5 * i, 4);
			world.spawn(projectile);
		}
		
		if(RandomUtils.randDouble() < 0.1) {
			world.spawn(new Tax(world, position.x, position.y));
		}
	}
}
