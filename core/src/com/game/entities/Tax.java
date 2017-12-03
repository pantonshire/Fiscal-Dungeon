package com.game.entities;

import com.game.graphics.Animation;
import com.game.graphics.Sequence;
import com.game.graphics.Textures;
import com.game.world.World;

public class Tax extends Coin {

	public Tax(World world, double x, double y) {
		super(world, x, y, -10, "good");
		animation = new Animation(Textures.instance.getTexture("tax"), Sequence.formatSequences(new Sequence(16, 18, 0, 1)));
	}
}
