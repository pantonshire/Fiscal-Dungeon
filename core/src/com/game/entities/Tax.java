package com.game.entities;

import com.game.graphics.Animation;
import com.game.graphics.Sequence;
import com.game.graphics.Textures;
import com.game.level.Level;

public class Tax extends Coin {

	public Tax(Level level, double x, double y) {
		super(level, x, y, -10, "good");
		animation = new Animation(Textures.instance.getTexture("tax"), Sequence.formatSequences(new Sequence(16, 18, 0, 1)));
	}
}
