package com.game.entities;

import com.game.graphics.Animation;
import com.game.graphics.Sequence;
import com.game.graphics.Textures;
import com.game.world.World;

public class RedGem extends Coin {

	public RedGem(World world, double x, double y) {
		super(world, x, y, 5, "coin");
		animation = new Animation(Textures.instance.getTexture("gem_red"), Sequence.formatSequences(new Sequence(16, 14, 6, 5)));
	}

	@Override
	protected void updateEntity() {
		// TODO Auto-generated method stub
	}

}
