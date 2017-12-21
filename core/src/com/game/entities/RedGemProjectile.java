package com.game.entities;

import com.game.graphics.Animation;
import com.game.graphics.Sequence;
import com.game.graphics.Textures;
import com.game.level.Level;

public class RedGemProjectile extends Coin {

	public RedGemProjectile(Level world, double x, double y, double angle, double speed) {
		super(world, x, y, 5, "coin");
		animation = new Animation(Textures.instance.getTexture("gem_red"), Sequence.formatSequences(new Sequence(16, 14, 6, 5)));
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
