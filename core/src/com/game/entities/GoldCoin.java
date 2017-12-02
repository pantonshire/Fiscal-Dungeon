package com.game.entities;

import com.game.graphics.Animation;
import com.game.graphics.Sequence;
import com.game.graphics.Textures;
import com.game.world.World;

public class GoldCoin extends Coin {

	public GoldCoin(World world, double x, double y) {
		super(world, x, y, 1, "coin");
		animation = new Animation(Textures.instance.getTexture("coin"), Sequence.formatSequences(new Sequence(14, 14, 6, 8)));
	}

	@Override
	protected void updateEntity() {
		// TODO Auto-generated method stub
	}

}
