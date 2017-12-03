package com.game.entities;

import com.game.graphics.LayerRenderer;
import com.game.world.World;

public class Trapdoor extends Entity {
	
	private Hitbox hitbox;

	public Trapdoor(World world, double x, double y) {
		super(world, x, y);
		hitbox = new Hitbox(this, 8, 8);
	}

	protected void updateEntity() {
		if(world.getPlayer().getHitbox().intersectsHitbox(hitbox)) {
			world.getPlayer().destroy();
		}
	}

	public void render(LayerRenderer renderer) {
		
	}
}
