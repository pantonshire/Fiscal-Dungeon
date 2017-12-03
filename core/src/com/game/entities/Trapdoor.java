package com.game.entities;

import com.game.graphics.LayerRenderer;
import com.game.world.World;
import com.game.world.WorldFactory;

public class Trapdoor extends Entity {
	
	private Hitbox hitbox;

	public Trapdoor(World world, double x, double y) {
		super(world, x, y);
		hitbox = new Hitbox(this, 8, 8);
	}

	protected void updateEntity() {
		if(world.getPlayer().getHitbox().intersectsHitbox(hitbox)) {
			WorldFactory.nextFloor(world);
		}
	}

	public void render(LayerRenderer renderer) {
		
	}
}
