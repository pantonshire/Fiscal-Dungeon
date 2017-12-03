package com.game.entities;

import com.game.graphics.Animation;
import com.game.graphics.Sequence;
import com.game.graphics.Textures;
import com.game.world.World;

public class PurpleGemProjectile extends Coin {
	
	public PurpleGemProjectile(World world, double x, double y, double angle) {
		super(world, x, y, 3, "coin");
		animation = new Animation(Textures.instance.getTexture("gem_purple"), Sequence.formatSequences(new Sequence(16, 14, 6, 5)));
		velocity.setAngle(angle, getSpeed());
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		if(collided) {
			destroy();
		}
		
		else if(position.distSqBetween(world.getPlayer().position) < 4096) {
			velocity.setAngle(position.angleBetween(world.getPlayer().position), getSpeed());
		}
	}
	
	private double getSpeed() {
		return world.getPlayer().getWalkSpeed() - 0.6;
	}
}
