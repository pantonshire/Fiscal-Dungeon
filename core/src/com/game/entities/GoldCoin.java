package com.game.entities;

import com.game.graphics.Animation;
import com.game.graphics.Sequence;
import com.game.graphics.Textures;
import com.game.level.Level;

public class GoldCoin extends Coin {

	public GoldCoin(Level world, double x, double y) {
		super(world, x, y, 1, "coin");
		animation = new Animation(Textures.instance.getTexture("coin"), Sequence.formatSequences(new Sequence(14, 14, 6, 8)));
	}
}
