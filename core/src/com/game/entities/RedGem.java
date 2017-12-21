package com.game.entities;

import com.game.graphics.Animation;
import com.game.graphics.Sequence;
import com.game.graphics.Textures;
import com.game.level.Level;

public class RedGem extends Coin {

	public RedGem(Level world, double x, double y) {
		super(world, x, y, 5, "coin");
		animation = new Animation(Textures.instance.getTexture("gem_red"), Sequence.formatSequences(new Sequence(16, 14, 6, 5)));
	}
}
