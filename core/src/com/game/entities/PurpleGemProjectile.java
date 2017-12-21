package com.game.entities;

import java.util.ArrayList;

import com.game.graphics.Animation;
import com.game.graphics.Sequence;
import com.game.graphics.Textures;
import com.game.level.Level;

public class PurpleGemProjectile extends Coin {

	public PurpleGemProjectile(Level world, double x, double y, double angle) {
		super(world, x, y, 3, "coin");
		animation = new Animation(Textures.instance.getTexture("gem_purple"), Sequence.formatSequences(new Sequence(16, 14, 6, 5)));
		velocity.setAngle(angle, getSpeed());
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		if(collided) {
			destroy();
		}

		else if(!pushed) {
			ArrayList<Player> players = world.getPlayers();
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
}
