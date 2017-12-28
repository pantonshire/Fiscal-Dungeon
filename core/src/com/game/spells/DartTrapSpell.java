package com.game.spells;

import com.game.audio.SoundEffects;
import com.game.entities.Player;
import com.game.entities.projectiles.DartTrap;
import com.game.level.Level;
import com.game.vector.Vector;
import static com.game.spells.SpellUpgrade.*;

public class DartTrapSpell extends Spell {

	public DartTrapSpell() {
		super(750, 180, SPEED, HEAVY, NUM_TRAPS, TRAP_RANGE);
	}

	protected void onUsed(Level level, Player player) {
		double armRotation = player.getArmRotation();
		Vector spawnPos = (new Vector()).setAngle(armRotation, 8).add(player.getPosition()).add(-3, 5);
		int numDarts = getNumDarts();
		for(int i = 0; i < numDarts; i++) {
			level.spawn(new DartTrap(level, spawnPos.x, spawnPos.y, Math.PI * 2 / numDarts * i, 1.25, getSpeed(), 0.0125, 3600, getRange()));
		}
		
		SoundEffects.instance.play("bow", 1, 1, 0);
	}
	
	private double getSpeed() {
		return Math.max(5 + (3 * getUpgradeLevel(SPEED)) - (4 * getUpgradeLevel(HEAVY)), 0.5);
	}
	
	private double getRange() {
		return 160 + (32 * getUpgradeLevel(TRAP_RANGE));
	}
	
	private int getNumDarts() {
		return 4 + getUpgradeLevel(NUM_TRAPS);
	}
}
