package com.game.spells;

import com.game.entities.Player;
import com.game.entities.projectiles.Warp;
import com.game.level.Level;
import com.game.vector.Vector;
import static com.game.spells.SpellUpgrade.*;

public class WarpSpell extends Spell {

	public WarpSpell() {
		super(500, 120, SPEED, HEAVY, CONTROL, CONTACT_DAMAGE);
	}

	protected void onUsed(Level level, Player player) {
		double armRotation = player.getArmRotation();
		Vector spawnPos = (new Vector()).setAngle(armRotation, 8).add(player.getPosition()).add(-3, 5);
		level.spawn(new Warp(level, spawnPos.x, spawnPos.y, armRotation, 16, getUpgradeLevel(CONTROL), player));
	}
}
