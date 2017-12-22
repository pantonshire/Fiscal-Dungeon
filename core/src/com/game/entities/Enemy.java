package com.game.entities;

import java.util.ArrayList;

import com.game.audio.SoundEffects;
import com.game.level.Level;

public abstract class Enemy extends EntityLiving {

	protected int health;

	public Enemy(Level world, double x, double y, int width, int height, double walkSpeed, int health) {
		super(world, x, y, width, height, walkSpeed);
		this.health = health;
	}

	public boolean damage(int amount) {
		if(invulnerable() || shouldRemove()) {
			return false;
		}
		
		health -= amount;
		if(health <= 0) {
			destroy();
			onDeath();
		}

		else {
			SoundEffects.instance.play("hurt", 1, 1, 0);
		}

		return true;
	}
	
	public boolean invulnerable() {
		return false;
	}

	protected abstract void onDeath();
	
	protected Player getNearestPlayer() {
		ArrayList<Player> players = world.getPlayers();
		Player nearestPlayer = null;
		double shortestDist = 0;
		
		for(Player player : players) {
			double dist = position.distBetween(player.position);
			if((nearestPlayer == null || dist < shortestDist) && canSee(player)) {
				shortestDist = dist;
				nearestPlayer = player;
			}
		}
		
		return nearestPlayer;
	}
}
