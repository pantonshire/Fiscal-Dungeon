package com.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.game.graphics.Animation;
import com.game.graphics.Sequence;
import com.game.graphics.Textures;
import com.game.level.Level;
import com.game.light.LevelLightManager;
import com.game.light.LightSource;
import com.game.vector.Vector;

public class GoldCoin extends Coin implements LightSource {

	public GoldCoin(Level world, double x, double y) {
		super(world, x, y, 1, "coin");
		animation = new Animation(Textures.instance.getTexture("coin"), Sequence.formatSequences(new Sequence(14, 14, 6, 8)));
		world.getLightManager().addDynamicLight(this);
	}
	
	public Vector lightPosition() {
		return position;
	}
	
	public int numLightRays() {
		return LevelLightManager.DEFAULT_RADIAL_SOURCE;
	}
	
	public float lightStrength() {
		return 48;
	}
	
	public Color lightColor() {
		return new Color(0.6F, 0.6F, 0.05F, 0.05F);
	}
}
