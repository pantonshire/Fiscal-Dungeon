package com.game.entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.game.graphics.Animation;
import com.game.graphics.Sequence;
import com.game.graphics.Textures;
import com.game.level.Level;
import com.game.light.LevelLightManager;
import com.game.light.LightSource;
import com.game.vector.Vector;

public class PurpleGemProjectile extends Coin implements LightSource {

	public PurpleGemProjectile(Level level, double x, double y, double angle) {
		super(level, x, y, 3, "coin");
		animation = new Animation(Textures.instance.getTexture("gem_purple"), Sequence.formatSequences(new Sequence(16, 14, 6, 5)));
		velocity.setAngle(angle, getSpeed());
		level.getLightManager().addDynamicLight(this);
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		if(collided) {
			destroy();
		}

		else if(!pushed) {
			ArrayList<Player> players = level.getPlayers();
			for(Player player : players) {
				if(position.distSqBetween(player.position) < 4096) {
					velocity.setAngle(position.angleBetween(player.position), getSpeed());
					break;
				}
			}
		}
	}

	private double getSpeed() {
		return 1.0;
	}
	
	public Vector lightPosition() {
		return position;
	}
	
	public int numLightRays() {
		return LevelLightManager.DEFAULT_RADIAL_SOURCE;
	}
	
	public float lightStrength() {
		return 70;
	}
	
	public Color lightColor() {
		return new Color(0.6F, 0.1F, 0.95F, 0.25F);
	}
}
