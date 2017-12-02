package com.game.entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.game.audio.SoundEffects;
import com.game.graphics.Animation;
import com.game.graphics.LayerRenderer;
import com.game.graphics.Sequence;
import com.game.graphics.Textures;
import com.game.input.Input;
import com.game.utils.RandomUtils;
import com.game.world.World;

public class Player extends EntityLiving {

	private static final int MAX_COINS = 100; //You die when you reach 100 coins

	private Animation animation;
	private Texture sword;
	private int facing;
	private int coins; //Basically health in this game lol

	public Player(World world, double x, double y) {
		super(world, x, y, 8, 20, 1.5);
		animation = new Animation(Textures.instance.getTexture("player"),
				Sequence.formatSequences(
						new Sequence(16, 35, 5, 4),
						new Sequence(16, 35, 5, 4),
						new Sequence(16, 35, 5, 4),
						new Sequence(16, 35, 5, 4),
						new Sequence(16, 35, 5, 4),
						new Sequence(16, 35, 5, 4)));
		sword = Textures.instance.getTexture("basic_sword");
	}
	
	public int getCoins() {
		return coins;
	}

	public void collectCoins(int amount) {
		coins += amount;
		if(coins >= MAX_COINS) {
			die();
		}
	}

	private void die() {
		destroy();
		world.startGameOverTimer();
		SoundEffects.instance.play("blast", 1, 1, 0);
		for(int i = 0; i < 200; i++) {
			Coin coin1 = RandomUtils.randDouble() < 0.05 ? new RedGem(world, position.x, position.y) : new GoldCoin(world, position.x, position.y);
			coin1.getVelocity().setAngle(RandomUtils.randAngle(), RandomUtils.randDouble(1.0, 5.0));
			world.spawn(coin1);
			Coin coin2 = RandomUtils.randDouble() < 0.05 ? new RedGem(world, position.x, position.y) : new GoldCoin(world, position.x, position.y);
			coin2.getVelocity().setAngle(RandomUtils.randAngle(), RandomUtils.randDouble(5.0, 15.0));
			world.spawn(coin2);
		}
	}

	private double getCoinSlowdown() {
		return coins * 0.01;
	}

	@Override
	public double getWalkSpeed() {
		return walkSpeed - getCoinSlowdown();
	}

	protected void updateEntity() {
		up = Input.instance.up();
		down = Input.instance.down();
		left = Input.instance.left();
		right = Input.instance.right();

		if(isWalking()) {
			if(down) {
				if(left && !right) { facing = 0; }
				else if(right && !left) { facing = 1; }
				else {
					facing = (facing == 0 || facing == 2 || facing == 4) ? 0 : 1;
				}
			}
			
			else if(up) {
				if(left && !right) { facing = 4; }
				else if(right && !left) { facing = 5; }
				else {
					facing = (facing == 0 || facing == 2 || facing == 4) ? 4 : 5;
				}
			}
			
			else {
				if(left && !right) { facing = 2; }
				else if(right && !left) { facing = 3; }
			}
		}
		
		ArrayList<Coin> coins = world.getCoins();
		for(Coin coin : coins) {
			if(position.distSqBetween(coin.getPosition()) < 25) {
				coin.collect(this);
				break;
			}
		}
	}

	public void render(LayerRenderer renderer) {
		boolean walking = isWalking();
		if(!animation.isPaused() && !walking) { animation.resetFrame(); }
		animation.setPaused(!walking);
		if(!animation.isPaused()) { animation.setSequence(facing, false); }

		renderer.getSpriteBatch().draw(animation.getFrame(), (float)(position.x - (animation.getFrameWidth() / 2)), (float)(position.y - (animation.getFrameHeight() / 2)));
		animation.updateTimer();
		renderer.getSpriteBatch().draw(sword, (float)(position.x + 2), (float)(position.y - 6));
	}
}
