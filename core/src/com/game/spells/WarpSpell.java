package com.game.spells;

import com.game.entities.Player;
import com.game.entities.Warp;
import com.game.level.Level;
import com.game.vector.Vector;

public class WarpSpell extends Spell {

	public WarpSpell() {
		super(750, 120);
	}

	protected void onUsed(Level world, Player player) {
		double armRotation = player.getArmRotation();
		Vector spawnPos = (new Vector()).setAngle(armRotation, 8).add(player.getPosition()).add(-3, 5);
		world.spawn(new Warp(world, spawnPos.x, spawnPos.y, armRotation, 20, player));
	}
}
