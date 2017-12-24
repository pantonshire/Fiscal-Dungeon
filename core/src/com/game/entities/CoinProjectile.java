package com.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.game.graphics.Animation;
import com.game.graphics.Sequence;
import com.game.graphics.Textures;
import com.game.level.Level;
import com.game.light.LevelLightManager;
import com.game.light.LightSource;
import com.game.vector.Vector;

public class CoinProjectile extends Coin implements LightSource {

	public CoinProjectile(Level level, double x, double y, double angle, double speed) {
		super(level, x, y, 1, "coin");
		animation = new Animation(Textures.instance.getTexture("coin"), Sequence.formatSequences(new Sequence(14, 14, 6, 8)));
		velocity.setAngle(angle, speed);
		level.getLightManager().addDynamicLight(this);
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		if(collided) {
			destroy();
		}
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
