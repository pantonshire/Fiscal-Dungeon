package com.game.spells;

import static com.game.spells.SpellUpgrade.*;

import com.game.entities.Player;
import com.game.entities.Portal;
import com.game.level.Level;
import com.game.vector.Vector;

public class PortalSpell extends Spell {

	public PortalSpell() {
		super(1000, 180, SPEED, HEAVY, HOMING, RATE_OF_FIRE, ACCURACY, NUM_SHOTS, PORTAL_RANGE);
	}

	protected void onUsed(Level level, Player player) {
		double armRotation = player.getArmRotation();
		Vector spawnPos = (new Vector()).setAngle(armRotation, 8).add(player.getPosition()).add(-3, 5);
		level.spawn(new Portal(level, spawnPos.x, spawnPos.y, getSpeed(), getRate(), getInnacuracy(), getUpgradeLevel(HOMING), getNumShots(), getRange()));
	}
	
	private int getRate() {
		return 15 - (3 * getUpgradeLevel(RATE_OF_FIRE));
	}
	
	private double getInnacuracy() {
		return Math.max(10 - (3 * getUpgradeLevel(ACCURACY)), 0);
	}
	
	private double getSpeed() {
		return Math.max(5 + (2 * getUpgradeLevel(SPEED)) - (2 * getUpgradeLevel(HEAVY)), 0.5);
	}
	
	private int getNumShots() {
		return 15 + (5 * getUpgradeLevel(NUM_SHOTS));
	}
	
	private double getRange() {
		return 240 + (96 * getUpgradeLevel(PORTAL_RANGE));
	}
}
