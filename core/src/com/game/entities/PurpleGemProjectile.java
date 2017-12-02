package com.game.entities;

import com.game.graphics.Animation;
import com.game.graphics.Sequence;
import com.game.graphics.Textures;
import com.game.world.World;

public class PurpleGemProjectile extends Coin {

	private double speed;
	
	public PurpleGemProjectile(World world, double x, double y, double angle, double speed) {
		super(world, x, y, 3, "coin");
		this.speed = speed;
		animation = new Animation(Textures.instance.getTexture("gem_purple"), Sequence.formatSequences(new Sequence(16, 14, 6, 5)));
		velocity.setAngle(angle, speed);
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		if(collided) {
			destroy();
		}
		
		else if(position.distSqBetween(world.getPlayer().position) < 4096) {
			velocity.setAngle(position.angleBetween(world.getPlayer().position), speed);
		}
	}
}
