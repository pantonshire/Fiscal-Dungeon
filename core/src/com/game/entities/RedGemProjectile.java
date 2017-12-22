package com.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.game.graphics.Animation;
import com.game.graphics.Sequence;
import com.game.graphics.Textures;
import com.game.level.Level;
import com.game.light.LevelLightManager;
import com.game.light.LightSource;
import com.game.vector.Vector;

public class RedGemProjectile extends Coin implements LightSource {

	public RedGemProjectile(Level world, double x, double y, double angle, double speed) {
		super(world, x, y, 5, "coin");
		animation = new Animation(Textures.instance.getTexture("gem_red"), Sequence.formatSequences(new Sequence(16, 14, 6, 5)));
		velocity.setAngle(angle, speed);
		world.getLightManager().addDynamicLight(this);
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
		return 32;
	}
	
	public Color lightColor() {
		return new Color(0.9F, 0.1F, 0.1F, 0.25F);
	}
}
