package com.game.entities;

import com.game.graphics.Animation;
import com.game.graphics.Sequence;
import com.game.graphics.Textures;
import com.game.world.World;

public class CoinProjectile extends Coin {

	public CoinProjectile(World world, double x, double y, double angle, double speed) {
		super(world, x, y, 1, "coin");
		animation = new Animation(Textures.instance.getTexture("coin"), Sequence.formatSequences(new Sequence(14, 14, 6, 8)));
		velocity.setAngle(angle, speed);
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		if(collided) {
			destroy();
		}
	}
}
