package com.game.entities;

import com.game.graphics.Animation;
import com.game.graphics.LayerRenderer;
import com.game.graphics.Sequence;
import com.game.graphics.Textures;
import com.game.input.Input;
import com.game.world.World;

public class Player extends EntityLiving {

	private static final int MAX_COINS = 100; //You die when you reach 100 coins
	
	private Animation animation;
	private int coins; //Basically health in this game lol
	
	public Player(World world, double x, double y) {
		super(world, x, y, 8, 20, 2);
		animation = new Animation(Textures.instance.getTexture("player"),
				Sequence.formatSequences(
						new Sequence(8, 20, 4, 4),
						new Sequence(8, 20, 4, 4),
						new Sequence(8, 20, 4, 4),
						new Sequence(8, 20, 4, 4),
						new Sequence(8, 20, 4, 4),
						new Sequence(8, 20, 4, 4)));
	}
	
	public void collectCoins(int amount) {
		coins += amount;
		if(coins >= MAX_COINS) {
			die();
		}
	}
	
	private void die() {
		//DO STUFF HERE
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
	}
	
	public void render(LayerRenderer renderer) {
		renderer.getSpriteBatch().draw(animation.getFrame(), (float)(position.x - (animation.getFrameWidth() / 2)), (float)(position.y - (animation.getFrameHeight() / 2)));
		animation.updateTimer();
	}
}
