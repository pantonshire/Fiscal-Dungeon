package com.game.entities;

import java.util.ArrayList;

import com.game.graphics.LayerRenderer;
import com.game.level.Level;

public class Trapdoor extends Entity {

	private Hitbox hitbox;

	public Trapdoor(Level level, double x, double y) {
		super(level, x, y);
		hitbox = new Hitbox(this, 8, 8);
	}

	protected void updateEntity() {
		ArrayList<Player> players = level.getPlayers();
		for(Player player : players) {
			if(player.getHitbox().intersectsHitbox(hitbox)) {
				level.nextRoom();
				break;
			}
		}
	}

	public void render(LayerRenderer renderer) {

	}
}
