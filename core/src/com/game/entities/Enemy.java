package com.game.entities;

import com.game.world.World;

public abstract class Enemy extends EntityLiving {

	protected int health;
	
	public Enemy(World world, double x, double y, int width, int height, double walkSpeed, int health) {
		super(world, x, y, width, height, walkSpeed);
		this.health = health;
	}

	public void damage(int amount) {
		health -= amount;
		if(health <= 0) {
			destroy();
			onDeath();
		}
	}
	
	protected abstract void onDeath();
}
