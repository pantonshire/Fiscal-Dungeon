package com.game.entities;

import java.util.ArrayList;

import com.game.graphics.LayerRenderer;
import com.game.world.World;

public class Trapdoor extends Entity {

	private Hitbox hitbox;

	public Trapdoor(World world, double x, double y) {
		super(world, x, y);
		hitbox = new Hitbox(this, 8, 8);
	}

	protected void updateEntity() {
		ArrayList<Player> players = world.getPlayers();
		for(Player player : players) {
			if(player.getHitbox().intersectsHitbox(hitbox)) {
				world.nextRoom();
				break;
			}
		}
	}

	public void render(LayerRenderer renderer) {

	}
}
